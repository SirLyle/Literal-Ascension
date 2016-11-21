package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.features.ClimbingRope
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
    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val stack = player.getHeldItem(hand)
        if (!stack.func_190926_b()) { //Is not empty
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

    fun tryPlaceBlock(stack: ItemStack, player: EntityPlayer, world: World, fromPos: BlockPos, facing: EnumFacing) : EnumActionResult {
        val placePos = BlockPos.MutableBlockPos(fromPos)
        while (feature.isMatchingBlock(world, placePos, facing)) {
            placePos.y--
        }

        if (feature.block.canPlaceBlockAt(world, placePos)) {
            val placeState = feature.block.defaultState.withProperty(BlockClimbingRope.FACING, facing)
            if (player.canPlayerEdit(placePos, facing, stack) && feature.block.isAttached(placeState, world, placePos) && world.setBlockState(placePos, placeState)) {
                val soundType = feature.block.getSoundType(placeState, world, placePos, player)
                world.playSound(player, fromPos, soundType.placeSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)

                stack.func_190918_g(1) //Decrement by one
                return EnumActionResult.SUCCESS
            }
        }

        return EnumActionResult.FAIL
    }
}
