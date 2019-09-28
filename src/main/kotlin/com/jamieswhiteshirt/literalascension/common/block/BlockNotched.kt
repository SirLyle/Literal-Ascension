package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockType
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.MathHelper.clamp


class BlockNotched(feature: CarvableBlockType) : BlockCarvedBase(feature), ICarvingBehaviour, ISpecialLadderBlock {
    companion object {
        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val NORTH: PropertyBool = PropertyBool.create("north")
        val EAST: PropertyBool = PropertyBool.create("east")

        val PROPERTIES = arrayOf(SOUTH, WEST, NORTH, EAST)

        fun propertyOf(facing: EnumFacing): PropertyBool {
            return PROPERTIES[facing.horizontalIndex]
        }

        private val COLLISION_AABB = AxisAlignedBB(0.03125, 0.0, 0.03125, 0.96875, 1.0, 0.96875)
    }

    override val carvingMaterial: ICarvingMaterial = feature.material

    init {
        defaultState = PROPERTIES.fold(blockState.baseState, { state, property ->
            state.withProperty(property, false)
        })
    }

    override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (facing.axis != EnumFacing.Axis.Y) {
            val property = propertyOf(facing)
            if (!state.getValue(property)) {
                if (!world.isRemote) {
                    world.setBlockState(pos, state.withProperty(property, true))
                    feature.parent.parent.parent.parent.spawnCarveParticles(world, pos, facing)
                }
                return true
            }
        } else {
            if (!world.isRemote) {
                world.setBlockState(pos, feature.chute.defaultState)
                feature.parent.parent.parent.parent.spawnCarveParticles(world, pos, facing)
            }
            return true
        }

        return false
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        var state = defaultState
        for (i in PROPERTIES.indices) {
            state = state.withProperty(PROPERTIES[i], ((meta shr i) and 1) == 1)
        }
        return state
    }

    override fun getMetaFromState(state: IBlockState): Int {
        var meta = 0
        for (i in PROPERTIES.indices) {
            if (state.getValue(PROPERTIES[i])) {
                meta = meta or (1 shl i)
            }
        }
        return meta
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, *PROPERTIES)
    }

    override fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        for (facing in EnumFacing.HORIZONTALS) {
            if (state.getValue(propertyOf(facing))) {
                if (isIntersectingDefault(pos.offset(facing), entity)) {
                    return true
                }
            }
        }
        return false
    }

    override fun getCollisionBoundingBox(state: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB? {
        val minZ = if(state.getValue(NORTH)) 0.03125 else 0.0
        val maxZ = if(state.getValue(SOUTH)) 0.96875 else 1.0
        val minX = if(state.getValue(WEST)) 0.03125 else 0.0
        val maxX = if(state.getValue(EAST)) 0.96875 else 1.0
        return AxisAlignedBB(minX, 0.0, minZ, maxX, 1.0, maxZ)
    }

    override fun onEntityCollidedWithBlock(world: World, pos: BlockPos, state: IBlockState, entity: Entity) {
        if (entity !is EntityLivingBase) {
            return
        }
        entity.fallDistance = 0.0f
        val limit = 0.15
        entity.motionX = clamp(entity.motionX, -limit, limit)
        entity.motionZ = clamp(entity.motionZ, -limit, limit)

        if (entity.isSneaking && entity is EntityPlayer) {
            if (entity.isInWater()) {
                entity.motionY = 0.02
            } else {
                entity.motionY = 0.08
            }
        } else if (entity.collidedHorizontally) {
            entity.motionY = .2
        } else {
            entity.motionY = Math.max(entity.motionY, -0.07)
        }
    }

    override fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        return true
    }
}