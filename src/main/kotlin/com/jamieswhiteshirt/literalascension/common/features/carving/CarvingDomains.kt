package com.jamieswhiteshirt.literalascension.common.features.carving

import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.CarvingDomain
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.CarvingDomainChisel
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.CarvingDomainMinecraft
import net.minecraftforge.common.config.Configuration

class CarvingDomains(config: Configuration, override val parent: Carving) : SubFeatureCollection<CarvingDomain>("domains", parent) {
    val MINECRAFT = optionalOn(config, CarvingDomainMinecraft(config, this))
    val CHISEL = optionalOn(config, CarvingDomainChisel(config, this))

    init {
        for (carvingDomain in subFeatures) {
            carvingDomain.registerCarvingBehaviourShims(parent)
        }
    }
}
