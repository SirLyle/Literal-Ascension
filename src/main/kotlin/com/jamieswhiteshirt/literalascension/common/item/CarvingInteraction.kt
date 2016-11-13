package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.features.Carving
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class CarvingInteraction(val carving: Carving) {
    abstract fun getHarvestLevel(stack: ItemStack): Int

    abstract fun damageItem(stack: ItemStack, amount: Int, player: EntityPlayer)

    fun playCarveSound(world: World, pos: BlockPos, player: EntityPlayer) {
        val state = world.getBlockState(pos)
        val soundType = state.block.getSoundType(state, world, pos, player)
        world.playSound(player, pos, soundType.breakSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)
    }

    operator fun invoke(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL
        } else {
            val state = world.getBlockState(pos)
            val carvingBehaviour = carving.getCarvingBehaviour(state)
            if (carvingBehaviour != null) {
                val carvingMaterial = carvingBehaviour.carvingMaterial
                val harvestLevel = getHarvestLevel(stack)
                if (carvingMaterial.requiredCarvingToolLevel <= harvestLevel) {
                    if (carvingBehaviour.tryCarve(state, world, pos, facing, hitX, hitY, hitZ)) {
                        if (!world.isRemote) {
                            if (harvestLevel < carvingMaterial.suitableCarvingToolLevel) {
                                damageItem(stack, carvingMaterial.unsuitableToolDamageMultiplier, player)
                            } else {
                                damageItem(stack, carvingMaterial.suitableToolDamageMultiplier, player)
                            }
                        }

                        playCarveSound(world, pos, player)
                        return EnumActionResult.SUCCESS
                    }
                }
            }

            return EnumActionResult.PASS
        }
    }
}
