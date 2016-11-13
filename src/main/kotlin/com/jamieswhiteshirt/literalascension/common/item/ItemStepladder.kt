package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.Stepladder
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

        val initialPlacePos = if (!blockState.block.isReplaceable(world, pos)) {
            pos.offset(facing)
        } else {
            pos
        }

        for (segment in BlockStepladder.SEGMENTS) {
            val placePos = initialPlacePos.down(segment)

            if (player.canPlayerEdit(placePos, facing, stack) && canPlaceAt(stack, world, placePos, facing)) {
                val horizontalFacing = EnumFacing.fromAngle(player.rotationYaw.toDouble())
                feature.block.placeStepladder(world, placePos, horizontalFacing)

                val soundType = feature.block.getSoundType(feature.block.defaultState, world, pos, player)
                world.playSound(player, placePos, soundType.placeSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)

                --stack.stackSize

                return EnumActionResult.SUCCESS
            }
        }

        return EnumActionResult.FAIL
    }

    fun canPlaceAt(stack: ItemStack, world: World, placePos: BlockPos, facing: EnumFacing): Boolean {
        for (pos in BlockStepladder.SEGMENTS.map { placePos.up(it) }) {
            if (!pos.isValid || !world.canBlockBePlaced(feature.block, pos, false, facing, null, stack) || !feature.block.canPlaceBlockAt(world, pos)) {
                return false
            }
        }

        return world.isSideSolid(placePos.down(), EnumFacing.UP)
    }

    val BlockPos.isValid: Boolean get () = this.x >= -30000000 && this.y >= 0 && this.z >= -30000000 && this.x < 30000000 && this.y < 256 && this.z < 30000000
}
