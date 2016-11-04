package com.jamieswhiteshirt.literalascension.common.features.carving

import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.*
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvingMaterials(config: Configuration, override val parent: Carving) : SubFeatureCollection<CarvingMaterial>("materials", parent) {
    val WOOD           = optionalOn(config, CarvingMaterialOldWood(config, this))
    val WOOD2          = optionalOn(config, CarvingMaterialNewWood(config, this))
    val STONE          = optionalOn(config, CarvingMaterialStone(config, this))
    val BASALTEXTRA    = optionalOn(config, Block.getBlockFromName("chisel:basaltextra")?.let { CarvingMaterialChiselStoneExtra(it, "basalt", config, this) })
    val LIMESTONEEXTRA = optionalOn(config, Block.getBlockFromName("chisel:limestoneextra")?.let { CarvingMaterialChiselStoneExtra(it, "limestone", config, this) })
    val MARBLEEXTRA    = optionalOn(config, Block.getBlockFromName("chisel:marbleextra")?.let { CarvingMaterialChiselStoneExtra(it, "marble", config, this) })

    init {
        for (carvingMaterial in subFeatures) {
            carvingMaterial.registerCarvingBehaviourShims()
        }
    }
}