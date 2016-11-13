package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains

import com.jamieswhiteshirt.literalascension.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.features.Carving
import com.jamieswhiteshirt.literalascension.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.CarvableBlock

abstract class CarvingDomain(val domainName: String, override val parent: CarvingDomains) : SubFeatureCollection<CarvableBlock>(domainName, parent) {
    fun registerCarvingBehaviourShims(carving: Carving) {
        for (carvableBlock in subFeatures) {
            carvableBlock.registerCarvingBehaviourShims(carving)
        }
    }
}
