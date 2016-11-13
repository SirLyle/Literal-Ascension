package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.CarvingDomainEnvironmentalTech
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockTypeSimple
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvableBlockEnvironmentalTechStone(block: Block, name: String, config: Configuration, parent: CarvingDomainEnvironmentalTech) : CarvableBlockMeta(block, 2, name, config, parent) {
    @Suppress("DEPRECATION")
    val NORMAL = optionalOn(config, CarvableBlockTypeSimple(block.getStateFromMeta(0), "normal", this))

    override val fromMeta = mapOf(
            0 to NORMAL
    )
}
