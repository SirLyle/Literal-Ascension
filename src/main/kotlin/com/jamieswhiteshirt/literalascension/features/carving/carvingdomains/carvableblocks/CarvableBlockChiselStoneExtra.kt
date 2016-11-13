package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.CarvingDomainChisel
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockTypeSimple
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvableBlockChiselStoneExtra(block: Block, name: String, config: Configuration, parent: CarvingDomainChisel) : CarvableBlockMeta(block, 2, name, config, parent) {
    @Suppress("DEPRECATION")
    val RAW = optionalOn(config, CarvableBlockTypeSimple(block.getStateFromMeta(7), "raw", this))

    override val fromMeta = mapOf(
            7 to RAW
    )
}
