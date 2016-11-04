package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials

import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlockSimple
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvingMaterialChiselStoneExtra(block: Block, name: String, config: Configuration, parent: CarvingMaterials) : CarvingMaterialMeta(block, 2, "${name}extra", config, parent) {
    @Suppress("DEPRECATION")
    val RAW = optionalOn(config, CarvedBlockSimple(block.getStateFromMeta(7), "${name}_raw", this))

    override val fromMeta = mapOf(
            7 to RAW
    )
}
