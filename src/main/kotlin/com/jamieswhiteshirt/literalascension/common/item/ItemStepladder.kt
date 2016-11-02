package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.features.stepladders.Stepladder
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemStepladder(val feature: Stepladder) : Item() {
    override fun onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val blockState = world.getBlockState(pos)

        val placePos = if (!blockState.block.isReplaceable(world, pos)) {
            pos.offset(facing)
        } else {
            pos
        }

        if (player.canPlayerEdit(placePos, facing, stack) && canPlaceAt(stack, world, placePos, facing)) {
            val horizontalFacing = EnumFacing.fromAngle(player.rotationYaw.toDouble())
            feature.block.placeStepladder(world, placePos, horizontalFacing)

            val soundType = feature.block.getSoundType(feature.block.defaultState, world, pos, player)
            world.playSound(player, placePos, soundType.placeSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)

            --stack.stackSize

            return EnumActionResult.SUCCESS
        }

        return EnumActionResult.FAIL
    }

    fun canPlaceAt(stack: ItemStack, world: World, placePos: BlockPos, facing: EnumFacing): Boolean {
        for (pos in BlockStepladder.SEGMENTS.map { placePos.up(it) }) {
            if (!world.canBlockBePlaced(feature.block, pos, false, facing, null, stack) || !feature.block.canPlaceBlockAt(world, pos)) {
                return false
            }
        }

        return world.isSideSolid(placePos.down(), EnumFacing.UP)
    }
}
