package com.jamieswhiteshirt.literalascension.features.carving

import com.jamieswhiteshirt.literalascension.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.features.Carving
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.*
import net.minecraftforge.common.config.Configuration

class CarvingDomains(config: Configuration, override val parent: Carving) : SubFeatureCollection<CarvingDomain>("domains", parent) {
    val MINECRAFT = optionalOn(config, CarvingDomainMinecraft(config, this))
    val CHISEL = optionalOn(config, CarvingDomainChisel(config, this))
    val ENVIRONMENTALTECH = optionalOn(config, CarvingDomainEnvironmentalTech(config, this))
    val BIOMESOPLENTY = optionalOn(config, CarvingDomainBiomesOPlenty(config, this))

    init {
        for (carvingDomain in subFeatures) {
            carvingDomain.registerCarvingBehaviourShims(parent)
        }
    }
}
