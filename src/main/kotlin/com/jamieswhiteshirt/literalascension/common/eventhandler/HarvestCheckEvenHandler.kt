package com.jamieswhiteshirt.literalascension.common.eventhandler

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object HarvestCheckEvenHandler {
    @SubscribeEvent
    fun onHarvestCheck(event: PlayerEvent.HarvestCheck) {
        val block = event.targetBlock.block
        if (block is BlockStepladder) {
            event.setCanHarvest(true)
        }
    }
}
