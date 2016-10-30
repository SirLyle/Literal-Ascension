package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import com.jamieswhiteshirt.literalascension.common.features.stepladders.Stepladder
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockStepladder(val feature: Stepladder) : Block(feature.material), ISpecialLadderBlock {
    companion object {
        val FACING: PropertyDirection = BlockHorizontal.FACING
        val SEGMENT: PropertyInteger = PropertyInteger.create("segment", 0, 2)

        private val collisionBoxesX = arrayOf(
                arrayOf(
                        AxisAlignedBB( 0.0 / 16.0, 0.0, 0.0,  3.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(13.0 / 16.0, 0.0, 0.0, 16.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 1.0 / 16.0, 0.5, 0.0,  4.0 / 16.0, 1.0, 1.0),
                        AxisAlignedBB(12.0 / 16.0, 0.5, 0.0, 15.0 / 16.0, 1.0, 1.0)
                ),
                arrayOf(
                        AxisAlignedBB( 2.0 / 16.0, 0.0, 0.0,  5.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(11.0 / 16.0, 0.0, 0.0, 14.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 3.0 / 16.0, 0.5, 0.0,  6.0 / 16.0, 1.0, 1.0),
                        AxisAlignedBB(10.0 / 16.0, 0.5, 0.0, 13.0 / 16.0, 1.0, 1.0)
                ),
                arrayOf(
                        AxisAlignedBB( 4.0 / 16.0, 0.0, 0.0,  7.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 9.0 / 16.0, 0.0, 0.0, 12.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 5.0 / 16.0, 0.5, 0.0, 11.0 / 16.0, 1.0, 1.0)
                )
        )
        private val collisionBoxesZ = arrayOf(
                arrayOf(
                        AxisAlignedBB(0.0, 0.0,  0.0 / 16.0, 1.0, 0.5,  3.0 / 16.0),
                        AxisAlignedBB(0.0, 0.0, 13.0 / 16.0, 1.0, 0.5, 16.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5,  1.0 / 16.0, 1.0, 1.0,  4.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 12.0 / 16.0, 1.0, 1.0, 15.0 / 16.0)
                ),
                arrayOf(
                        AxisAlignedBB(0.0, 0.0,  2.0 / 16.0, 1.0, 0.5,  5.0 / 16.0),
                        AxisAlignedBB(0.0, 0.0, 11.0 / 16.0, 1.0, 0.5, 14.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5,  3.0 / 16.0, 1.0, 1.0,  6.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 10.0 / 16.0, 1.0, 1.0, 13.0 / 16.0)
                ),
                arrayOf(
                        AxisAlignedBB(0.0, 0.0,  4.0 / 16.0, 1.0, 0.5,  7.0 / 16.0),
                        AxisAlignedBB(0.0, 0.0,  9.0 / 16.0, 1.0, 0.5, 12.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5,  5.0 / 16.0, 1.0, 1.0, 11.0 / 16.0)
                )
        )
        private val selectionBoxesX = arrayOf(
                AxisAlignedBB(0.0 / 8.0, 0.0, 0.0, 8.0 / 8.0, 1.0, 1.0),
                AxisAlignedBB(1.0 / 8.0, 0.0, 0.0, 7.0 / 8.0, 1.0, 1.0),
                AxisAlignedBB(2.0 / 8.0, 0.0, 0.0, 6.0 / 8.0, 1.0, 1.0)
        )
        private val selectionBoxesZ = arrayOf(
                AxisAlignedBB(0.0, 0.0, 0.0 / 8.0, 1.0, 1.0, 8.0 / 8.0),
                AxisAlignedBB(0.0, 0.0, 1.0 / 8.0, 1.0, 1.0, 7.0 / 8.0),
                AxisAlignedBB(0.0, 0.0, 2.0 / 8.0, 1.0, 1.0, 6.0 / 8.0)
        )
    }

    init {
        soundType = SoundType.LADDER
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(SEGMENT, 0)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun addCollisionBoxToList(state: IBlockState, world: World, pos: BlockPos, entityBox: AxisAlignedBB, collisionBoxes: MutableList<AxisAlignedBB>, entity: Entity?) {
        for (box in getCollisionBoxes(state)) {
            Block.addCollisionBoxToList(pos, entityBox, collisionBoxes, box)
        }
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        @Suppress("DEPRECATION")
        return when (state.getValue(FACING).axis) {
            EnumFacing.Axis.X -> selectionBoxesX[state.getValue(SEGMENT)]
            EnumFacing.Axis.Z -> selectionBoxesZ[state.getValue(SEGMENT)]
            else -> super.getBoundingBox(state, source, pos)
        }
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = feature.item

    override fun damageDropped(state: IBlockState): Int = 0

    override fun canPlaceBlockAt(world: World, pos: BlockPos): Boolean {
        if (super.canPlaceBlockAt(world, pos)) {
            if (!world.isSideSolid(pos.down(), EnumFacing.UP)) {
                return false
            }

            for (i in 1..2) {
                if (!world.isAirBlock(pos.up(i))) {
                    return false
                }
            }

            return true
        } else {
            return false
        }
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: IBlockState): Boolean = false

    @Suppress("OverridingDeprecatedMember")
    override fun collisionRayTrace(state: IBlockState, world: World, pos: BlockPos, start: Vec3d, end: Vec3d): RayTraceResult? {
        val results = getCollisionBoxes(state).map { rayTrace(pos, start, end, it) }.filterNotNull()

        var bestResult: RayTraceResult? = null
        var bestResultDistance = 0.0

        for (result in results) {
            val resultDistance = result.hitVec.squareDistanceTo(end)
            if (resultDistance > bestResultDistance) {
                bestResult = result
                bestResultDistance = resultDistance
            }
        }

        return bestResult
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getHorizontal(meta and 3)).withProperty(SEGMENT, (meta and 15) shr 2)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).horizontalIndex or (state.getValue(SEGMENT) shl 2)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING, SEGMENT)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block) {
        @Suppress("DEPRECATION")
        super.neighborChanged(state, world, pos, block)
        if (!world.isRemote) {
            checkAndDropBlock(world, pos, state)
        }
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        return ItemStack(feature.item)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, heldItem: ItemStack?, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (!world.isRemote) {
            val activatedSegment = state.getValue(SEGMENT)
            if (!player.isCreative) {
                for (drop in getDrops(world, pos, state, 0)) {
                    if (!player.inventory.addItemStackToInventory(drop) && drop.stackSize > 0) {
                        spawnAsEntity(world, pos, drop)
                    }
                }
            }

            for (segment in 0..2) {
                val otherPos = pos.up(segment - activatedSegment)
                val otherState = world.getBlockState(otherPos)
                if (otherState.block == this && otherState.getValue(SEGMENT) == segment) {
                    world.setBlockToAir(pos)
                }
            }

            feature.parent.playLadderPickupSound(world, pos)
        }

        return true
    }

    override fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        val facing = state.getValue(FACING)
        for (i in -1..1) {
            if (isIntersectingDefault(pos.offset(facing, i), entity)) {
                return true
            }
        }
        return false
    }

    fun getCollisionBoxes(state: IBlockState): Array<AxisAlignedBB> {
        return when (state.getValue(FACING).axis) {
            EnumFacing.Axis.X -> collisionBoxesX[state.getValue(SEGMENT)]
            EnumFacing.Axis.Z -> collisionBoxesZ[state.getValue(SEGMENT)]
            else -> emptyArray()
        }
    }

    fun checkAndDropBlock(world: World, pos: BlockPos, state: IBlockState) {
        if (!canBlockStay(world, pos, state)) {
            world.setBlockToAir(pos)
        }
    }

    fun canBlockStay(world: World, pos: BlockPos, state: IBlockState): Boolean {
        val thisSegment = state.getValue(SEGMENT)

        if (thisSegment == 0) {
            if (!world.isSideSolid(pos.down(), EnumFacing.UP)) {
                return false
            }
        }

        for (segment in 0..2) {
            val otherState = world.getBlockState(pos.up(segment - thisSegment))
            if (otherState.block != this || otherState.getValue(SEGMENT) != segment) {
                return false
            }
        }

        return true
    }

    fun tryPlaceStepladder(world: World, pos: BlockPos, horizontalFacing: EnumFacing): Boolean {
        if (canPlaceBlockAt(world, pos)) {
            val baseState = defaultState.withProperty(BlockStepladder.FACING, horizontalFacing)
            for (i in 0..2) {
                world.setBlockState(pos.up(i), baseState.withProperty(BlockStepladder.SEGMENT, i))
            }
            return true
        }
        return false
    }
}
