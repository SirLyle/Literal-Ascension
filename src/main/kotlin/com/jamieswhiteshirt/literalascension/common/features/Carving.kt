package com.jamieswhiteshirt.literalascension.common.features

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.common.Features
import com.jamieswhiteshirt.literalascension.common.ISubFeature
import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingTools
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class Carving(config: Configuration, override val parent: Features) : SubFeatureCollection<ISubFeature>("carving", parent) {
    val CARVING_MATERIALS = required(CarvingMaterials(config, this))
    val CARVING_TOOLS     = required(CarvingTools(config, this))

    val carvingBehaviourShims = mutableMapOf<Block, ICarvingBehaviour>()

    init {
        for (carvingMaterial in CARVING_MATERIALS.subFeatures) {
            carvingBehaviourShims[carvingMaterial.block] = carvingMaterial.getCarvingBehaviourShim()
        }
    }
}
