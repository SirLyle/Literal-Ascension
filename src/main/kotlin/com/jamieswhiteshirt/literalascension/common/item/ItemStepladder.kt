package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.init.EnumStepladder
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemStepladder(val type: EnumStepladder) : Item() {
    override fun onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        if (facing == EnumFacing.UP) {
            val blockState = world.getBlockState(pos)

            val placePos = if (!blockState.block.isReplaceable(world, pos)) {
                pos.offset(facing)
            } else {
                pos
            }

            val horizontalFacing = EnumFacing.fromAngle(player.rotationYaw.toDouble())
            if (player.canPlayerEdit(placePos, facing, stack) && type.block.tryPlaceStepladder(world, placePos, horizontalFacing)) {
                val soundType = type.block.soundType
                world.playSound(player, placePos, soundType.placeSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)

                --stack.stackSize

                return EnumActionResult.SUCCESS
            }
        }

        return EnumActionResult.FAIL
    }
}
