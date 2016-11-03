package com.jamieswhiteshirt.literalascension.common.features.carving

import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterialNewWood
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterialOldWood
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterialStone
import net.minecraftforge.common.config.Configuration

class CarvingMaterials(config: Configuration, override val parent: Carving) : SubFeatureCollection<CarvingMaterial>("materials", parent) {
    val WOOD = optionalOn(config, CarvingMaterialOldWood(config, this))
    val WOOD2 = optionalOn(config, CarvingMaterialNewWood(config, this))
    val STONE = optionalOn(config, CarvingMaterialStone(config, this))

    init {
        for (carvingMaterial in subFeatures) {
            carvingMaterial.registerCarvingBehaviourShims()
        }
    }
}