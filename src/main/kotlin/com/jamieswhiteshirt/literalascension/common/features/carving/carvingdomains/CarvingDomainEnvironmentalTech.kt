package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains

import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.*
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvingDomainEnvironmentalTech(config: Configuration, parent: CarvingDomains) : CarvingDomain("environmentaltech", parent) {
    val HARDENED_STONE = optionalOn(config, Block.getBlockFromName("environmentaltech:hardened_stone")?.let { CarvableBlockEnvironmentalTechStone(it, "hardened_stone", config, this) })
    val BASALT         = optionalOn(config, Block.getBlockFromName("environmentaltech:basalt")?.let { CarvableBlockEnvironmentalTechStone(it, "basalt", config, this) })
    val ALABASTER      = optionalOn(config, Block.getBlockFromName("environmentaltech:alabaster")?.let { CarvableBlockEnvironmentalTechStone(it, "alabaster", config, this) })
}