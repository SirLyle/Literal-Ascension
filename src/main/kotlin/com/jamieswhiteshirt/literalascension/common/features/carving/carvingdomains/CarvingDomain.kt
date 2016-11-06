package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains

import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.CarvableBlock

abstract class CarvingDomain(val domainName: String, override val parent: CarvingDomains) : SubFeatureCollection<CarvableBlock>(domainName, parent) {
    fun registerCarvingBehaviourShims(carving: Carving) {
        for (carvableBlock in subFeatures) {
            carvableBlock.registerCarvingBehaviourShims(carving)
        }
    }
}
