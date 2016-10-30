package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlock
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockNotched(feature: CarvedBlock) : BlockCarvedBase(feature), ICarvingBehaviour, ISpecialLadderBlock {
    companion object {
        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val NORTH: PropertyBool = PropertyBool.create("north")
        val EAST: PropertyBool = PropertyBool.create("east")

        val PROPERTIES = arrayOf(SOUTH, WEST, NORTH, EAST)

        fun propertyOf(facing: EnumFacing): PropertyBool {
            return PROPERTIES[facing.horizontalIndex]
        }
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
                    feature.parent.parent.parent.spawnCarveParticles(world, pos, facing)
                }
                return true
            }
        } else {
            if (!world.isRemote) {
                world.setBlockState(pos, feature.chute.defaultState)
                feature.parent.parent.parent.spawnCarveParticles(world, pos, facing)
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
}