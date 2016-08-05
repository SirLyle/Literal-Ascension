package com.jamieswhiteshirt.literalascension.common.item

import com.jamieswhiteshirt.literalascension.common.ChuteType
import com.jamieswhiteshirt.literalascension.common.block.BlockChuteExit
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockStone
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemChuteCarver(material: ToolMaterial) : ItemTool(1.0F, -2.8F, material, emptySet()) {
    override fun onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val state = world.getBlockState(pos)
        val chuteType = getChuteTypeForBlock(state)
        if (chuteType != null) {
            world.setBlockState(pos, if (facing.axis == EnumFacing.Axis.Y) {
                chuteType.chute.defaultState
            }
            else {
                chuteType.chuteExit.defaultState.withProperty(BlockChuteExit.FACING, facing)
            })
            stack.damageItem(1, player)

            val soundType = state.block.soundType
            world.playSound(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), soundType.breakSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F, false)

            return EnumActionResult.SUCCESS
        }

        return EnumActionResult.FAIL
    }

    fun getChuteTypeForBlock(state: IBlockState): ChuteType? {
        val block = state.block
        when (block) {
            is BlockLog -> {
                if (state.getValue(BlockLog.LOG_AXIS) == BlockLog.EnumAxis.Y) {
                    val variantProperty = when (block) {
                        is BlockOldLog -> BlockOldLog.VARIANT
                        is BlockNewLog -> BlockNewLog.VARIANT
                        else -> null
                    }
                    if (variantProperty != null) {
                        return ChuteType.fromPlankType[state.getValue(variantProperty)]
                    }
                }
            }
            is BlockStone -> {
                if (toolMaterial.harvestLevel >= 3) {
                    return ChuteType.fromStoneType[state.getValue(BlockStone.VARIANT)]
                }
            }
        }
        return null
    }
}
