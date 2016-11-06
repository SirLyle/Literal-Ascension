package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains

import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.*
import net.minecraftforge.common.config.Configuration

class CarvingDomainMinecraft(config: Configuration, parent: CarvingDomains) : CarvingDomain("minecraft", parent) {
    val LOG  = optionalOn(config, CarvableBlockOldWood(config, this))
    val LOG2 = optionalOn(config, CarvableBlockNewWood(config, this))
    val STONE = optionalOn(config, CarvableBlockStone(config, this))
}