package com.jamieswhiteshirt.literalascension.interop.tconstruct.impl

import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.item.CarvingInteraction
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import slimeknights.tconstruct.library.TinkerRegistry
import slimeknights.tconstruct.library.tools.TinkerToolCore
import slimeknights.tconstruct.library.utils.TagUtil
import slimeknights.tconstruct.library.utils.ToolHelper

class CarvingEvents(val carvingTrait: ModCarving, val carving: Carving) {
    val carvingInteraction = object : CarvingInteraction(carving) {
        override fun getHarvestLevel(stack: ItemStack): Int = ToolHelper.getHarvestLevelStat(stack)

        override fun damageItem(stack: ItemStack, amount: Int, player: EntityPlayer) {
            if (!player.capabilities.isCreativeMode) {
                ToolHelper.damageTool(stack, amount, player)
            }
        }
    }

    @SubscribeEvent
    fun onInteract(event: PlayerInteractEvent.RightClickBlock) {
        val facing = event.face
        if (facing != null) {
            val stack = event.itemStack
            if (stack != null) {
                val item = stack.item
                if (item is TinkerToolCore) {
                    val traitIdentifiers = TagUtil.getTraitsTagList(stack)
                    for (i in 0..traitIdentifiers.tagCount() - 1) {
                        val traitIdentifier = traitIdentifiers.getStringTagAt(i)
                        val trait = TinkerRegistry.getTrait(traitIdentifier)
                        if (trait == carvingTrait) {
                            val pos = event.pos
                            val hit = event.hitVec
                            val hitX = hit.xCoord.toFloat() - pos.x
                            val hitY = hit.yCoord.toFloat() - pos.y
                            val hitZ = hit.zCoord.toFloat() - pos.z
                            val result = carvingInteraction(stack, event.entityPlayer, event.world, pos, facing, hitX, hitY, hitZ)
                            if (result == EnumActionResult.SUCCESS) {
                                event.useItem = Event.Result.DENY
                                event.useBlock = Event.Result.DENY
                                event.entityPlayer.swingArm(event.hand)
                                return
                            }
                        }
                    }
                }
            }
        }
    }
}
