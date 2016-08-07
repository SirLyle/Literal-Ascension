package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ILadderBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockClimbingRope : Block(Material.WEB), ILadderBlock {
    companion object {
        val FACING: PropertyDirection = BlockHorizontal.FACING

        private val boundingBoxes = arrayOf(
                AxisAlignedBB( 6.0 / 16.0, 0.0, 12.0 / 16.0, 10.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB( 0.0 / 16.0, 0.0,  6.0 / 16.0,  4.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB( 6.0 / 16.0, 0.0,  0.0 / 16.0, 10.0 / 16.0, 1.0,  4.0 / 16.0),
                AxisAlignedBB(12.0 / 16.0, 0.0,  6.0 / 16.0, 16.0 / 16.0, 1.0, 10.0 / 16.0)
        )

        private val collisionBoxes = arrayOf(
                AxisAlignedBB( 8.0 / 16.0, 0.0, 14.0 / 16.0,  8.0 / 16.0, 1.0, 14.0 / 16.0),
                AxisAlignedBB( 2.0 / 16.0, 0.0,  8.0 / 16.0,  2.0 / 16.0, 1.0,  8.0 / 16.0),
                AxisAlignedBB( 8.0 / 16.0, 0.0,  2.0 / 16.0, 10.0 / 16.0, 1.0,  2.0 / 16.0),
                AxisAlignedBB(14.0 / 16.0, 0.0,  8.0 / 16.0, 14.0 / 16.0, 1.0,  8.0 / 16.0)
        )

        private val ladderCollisionBoxes = arrayOf(
                AxisAlignedBB( 0.0 / 16.0, 0.0,  6.0 / 16.0, 16.0 / 16.0, 1.0, 22.0 / 16.0),
                AxisAlignedBB(-6.0 / 16.0, 0.0,  0.0 / 16.0, 10.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB( 0.0 / 16.0, 0.0, -6.0 / 16.0, 18.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB( 6.0 / 16.0, 0.0,  0.0 / 16.0, 22.0 / 16.0, 1.0, 16.0 / 16.0)
        )
    }

    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return boundingBoxes[state.getValue(FACING).horizontalIndex]
    }

    override fun getCollisionBoundingBox(state: IBlockState, worldIn: World, pos: BlockPos): AxisAlignedBB {
        return collisionBoxes[state.getValue(FACING).horizontalIndex]
    }

    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        return ladderCollisionBoxes[state.getValue(FACING).horizontalIndex].offset(pos).intersectsWith(entity.entityBoundingBox)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getHorizontal(meta))
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).horizontalIndex
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun canPlaceBlockOnSide(world: World, pos: BlockPos, side: EnumFacing): Boolean {
        if (super.canPlaceBlockOnSide(world, pos, side)) {
            if (side.axis != EnumFacing.Axis.Y) {
                return canBlockStay(defaultState.withProperty(FACING, side.opposite), world, pos)
            }
        }

        return false
    }

    override fun onBlockPlaced(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState {
        if (facing.axis != EnumFacing.Axis.Y) {
            return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, facing.opposite)
        }
        else {
            return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
        }
    }

    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block) {
        super.neighborChanged(state, world, pos, block)
        checkAndDropBlock(world, pos, state)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, heldItem: ItemStack?, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (heldItem != null && heldItem.item == Item.getItemFromBlock(this)) {
            if (tryExtend(state, world, pos)) {
                //world.playSound(player, pos, soundType.placeSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)
                if (!world.isRemote && !player.capabilities.isCreativeMode) {
                    --heldItem.stackSize
                }
                return true
            }
        }
        return false
    }

    fun checkAndDropBlock(world: World, pos: BlockPos, state: IBlockState) {
        if (!canBlockStay(state, world, pos)) {
            dropBlockAsItem(world, pos, state, 0)
            world.setBlockToAir(pos)
        }
    }

    fun canBlockStay(state: IBlockState, world: World, pos: BlockPos): Boolean {
        if (world.getBlockState(pos.up()).block == this) {
            return true
        }
        else {
            val facing = state.getValue(FACING)
            val sidePos = pos.offset(facing)
            val sideState = world.getBlockState(sidePos)
            if (sideState.block.isSideSolid(sideState, world, sidePos, facing.opposite)) {
                return true
            }
        }

        return false
    }

    fun tryExtend(state: IBlockState, world: World, pos: BlockPos): Boolean {
        val belowPos = pos.down()
        val belowState = world.getBlockState(belowPos)
        val belowBlock = belowState.block
        if (belowBlock == this) {
            if (state.getValue(FACING) == belowState.getValue(FACING)) {
                return tryExtend(belowState, world, belowPos)
            }
        }
        else if(belowBlock.isReplaceable(world, belowPos)) {
            if (!world.isRemote) {
                world.setBlockState(belowPos, state)
            }
            return true
        }

        return false
    }
}
