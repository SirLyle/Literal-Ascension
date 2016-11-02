package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import com.jamieswhiteshirt.literalascension.common.features.ClimbingRope
import net.minecraft.block.Block
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
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockClimbingRope(val feature: ClimbingRope) : Block(Material.CIRCUITS), ISpecialLadderBlock {
    companion object {
        val FACING: PropertyDirection = PropertyDirection.create("facing", { it != EnumFacing.DOWN })

        private val boundingBoxes = arrayOf(
                AxisAlignedBB(6.0 / 16.0, 0.0, 6.0 / 16.0, 10.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB(6.0 / 16.0, 0.0, 0.0 / 16.0, 10.0 / 16.0, 1.0, 4.0 / 16.0),
                AxisAlignedBB(6.0 / 16.0, 0.0, 12.0 / 16.0, 10.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, 6.0 / 16.0, 4.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB(12.0 / 16.0, 0.0, 6.0 / 16.0, 16.0 / 16.0, 1.0, 10.0 / 16.0)
        )

        private val collisionBoxes = arrayOf(
                AxisAlignedBB(8.0 / 16.0, 0.0, 8.0 / 16.0, 8.0 / 16.0, 1.0, 8.0 / 16.0),
                AxisAlignedBB(8.0 / 16.0, 0.0, 2.0 / 16.0, 8.0 / 16.0, 1.0, 2.0 / 16.0),
                AxisAlignedBB(8.0 / 16.0, 0.0, 14.0 / 16.0, 8.0 / 16.0, 1.0, 14.0 / 16.0),
                AxisAlignedBB(2.0 / 16.0, 0.0, 8.0 / 16.0, 2.0 / 16.0, 1.0, 8.0 / 16.0),
                AxisAlignedBB(14.0 / 16.0, 0.0, 8.0 / 16.0, 14.0 / 16.0, 1.0, 8.0 / 16.0)
        )

        private val ladderCollisionBoxes = arrayOf(
                AxisAlignedBB(0.0 / 16.0, 0.0, 0.0 / 16.0, 16.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, -6.0 / 16.0, 18.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, 6.0 / 16.0, 16.0 / 16.0, 1.0, 22.0 / 16.0),
                AxisAlignedBB(-6.0 / 16.0, 0.0, 0.0 / 16.0, 10.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(6.0 / 16.0, 0.0, 0.0 / 16.0, 22.0 / 16.0, 1.0, 16.0 / 16.0)
        )
    }

    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.UP)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return boundingBoxes[state.getValue(FACING).index - 1]
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getCollisionBoundingBox(state: IBlockState, worldIn: World, pos: BlockPos): AxisAlignedBB {
        return collisionBoxes[state.getValue(FACING).index - 1]
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = feature.item

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    override fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        return ladderCollisionBoxes[state.getValue(FACING).index - 1].offset(pos).intersectsWith(entity.entityBoundingBox)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getFront(meta + 1))
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index - 1
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    @Suppress("OverridingDeprecatedMember")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block) {
        @Suppress("DEPRECATION")
        super.neighborChanged(state, world, pos, block)
        checkAndDropBlock(world, pos, state)
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        return ItemStack(feature.item)
    }

    fun checkAndDropBlock(world: World, pos: BlockPos, state: IBlockState) {
        if (!canBlockStay(state, world, pos)) {
            world.destroyBlock(pos, true)
        }
    }

    fun canBlockStay(state: IBlockState, world: World, pos: BlockPos): Boolean {
        val aboveState = world.getBlockState(pos.up())
        val facing = state.getValue(FACING)
        if (aboveState.block == this && aboveState.getValue(FACING) == facing) {
            return true
        } else {
            val attachPos = pos.offset(facing)
            val attachState = world.getBlockState(attachPos)
            if (attachState.block.isSideSolid(attachState, world, attachPos, facing.opposite)) {
                return true
            }
        }

        return false
    }
}
