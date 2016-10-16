package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ILadderBlock
import com.jamieswhiteshirt.literalascension.common.stepladder.Stepladder
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
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
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockStepladder(val type: Stepladder) : Block(type.material), ILadderBlock {
    companion object {
        val FACING: PropertyDirection = BlockHorizontal.FACING
        val SEGMENT: PropertyInteger = PropertyInteger.create("segment", 0, 2)

        private val collisionBoxesX = arrayOf(
                arrayOf(
                        AxisAlignedBB(1.0 / 16.0, 0.0, 0.0, 15.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(2.0 / 16.0, 0.5, 0.0, 14.0 / 16.0, 1.0, 1.0)
                ),
                arrayOf(
                        AxisAlignedBB(3.0 / 16.0, 0.0, 0.0, 13.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(4.0 / 16.0, 0.5, 0.0, 12.0 / 16.0, 1.0, 1.0)
                ),
                arrayOf(
                        AxisAlignedBB(5.0 / 16.0, 0.0, 0.0, 11.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(6.0 / 16.0, 0.5, 0.0, 10.0 / 16.0, 1.0, 1.0)
                )
        )
        private val collisionBoxesZ = arrayOf(
                arrayOf(
                        AxisAlignedBB(0.0, 0.0, 1.0 / 16.0, 1.0, 0.5, 15.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 2.0 / 16.0, 1.0, 1.0, 14.0 / 16.0)
                ),
                arrayOf(
                        AxisAlignedBB(0.0, 0.0, 3.0 / 16.0, 1.0, 0.5, 13.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 4.0 / 16.0, 1.0, 1.0, 12.0 / 16.0)
                ),
                arrayOf(
                        AxisAlignedBB(0.0, 0.0, 5.0 / 16.0, 1.0, 0.5, 11.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 6.0 / 16.0, 1.0, 1.0, 10.0 / 16.0)
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
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(SEGMENT, 0)
    }

    override fun addCollisionBoxToList(state: IBlockState, world: World, pos: BlockPos, entityBox: AxisAlignedBB, collisionBoxes: MutableList<AxisAlignedBB>, entity: Entity?) {
        val array = when (state.getValue(FACING).axis) {
            EnumFacing.Axis.X -> collisionBoxesX[state.getValue(SEGMENT)]
            EnumFacing.Axis.Z -> collisionBoxesZ[state.getValue(SEGMENT)]
            else -> emptyArray()
        }
        for (box in array) {
            Block.addCollisionBoxToList(pos, entityBox, collisionBoxes, box)
        }
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return when (state.getValue(FACING).axis) {
            EnumFacing.Axis.X -> selectionBoxesX[state.getValue(SEGMENT)]
            EnumFacing.Axis.Z -> selectionBoxesZ[state.getValue(SEGMENT)]
            else -> super.getBoundingBox(state, source, pos)
        }
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = type.item

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

    override fun isOpaqueCube(state: IBlockState?): Boolean = false

    override fun isFullCube(state: IBlockState?): Boolean = false

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getHorizontal(meta and 3)).withProperty(SEGMENT, (meta and 15) shr 2)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).horizontalIndex or (state.getValue(SEGMENT) shl 2)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING, SEGMENT)
    }

    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block) {
        super.neighborChanged(state, world, pos, block)
        if (!world.isRemote) {
            checkAndDropBlock(world, pos, state)
        }
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        return ItemStack(type.item)
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
        }

        return true
    }

    override fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        val facing = state.getValue(FACING)
        for (i in -1..1) {
            if (isLadderIntersectingDefault(pos.offset(facing, i), entity)) {
                return true
            }
        }
        return false
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
