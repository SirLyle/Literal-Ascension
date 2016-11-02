package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.features.ClimbingRope
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemClimbingRope(val feature: ClimbingRope) : Item() {
    override fun onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        if (stack.stackSize > 0) {
            val state = world.getBlockState(pos)
            if (state.block == feature.block) {
                return tryPlaceBlock(stack, player, world, pos, state.getValue(BlockClimbingRope.FACING))
            }
            else if (facing != EnumFacing.UP) {
                return tryPlaceBlock(stack, player, world, pos.offset(facing), facing.opposite)
            }
        }

        return EnumActionResult.FAIL
    }

    fun isMatchingBlock(world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        val state = world.getBlockState(pos)
        return state.block == feature.block && state.getValue(BlockClimbingRope.FACING) == facing
    }

    fun tryPlaceBlock(stack: ItemStack, player: EntityPlayer, world: World, fromPos: BlockPos, facing: EnumFacing) : EnumActionResult {
        val placePos = BlockPos.MutableBlockPos(fromPos)
        while (isMatchingBlock(world, placePos, facing)) {
            placePos.y--
        }

        if (feature.block.canPlaceBlockAt(world, placePos)) {
            val placeState = feature.block.defaultState.withProperty(BlockClimbingRope.FACING, facing)
            if (player.canPlayerEdit(placePos, facing, stack) && feature.block.canBlockStay(placeState, world, placePos) && world.setBlockState(placePos, placeState)) {
                val soundType = feature.block.getSoundType(placeState, world, placePos, player)
                world.playSound(player, fromPos, soundType.placeSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)

                --stack.stackSize
                return EnumActionResult.SUCCESS
            }
        }

        return EnumActionResult.FAIL
    }
}
