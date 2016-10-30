package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlock
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

abstract class CarvingMaterial(val block: Block, val hardness: Float, toolLevel: Int, name: String, config: Configuration, override val parent: CarvingMaterials) : SubFeatureCollection<CarvedBlock>(name, parent), ICarvingMaterial {
    override val requiredCarvingToolLevel: Int = config.getInt("requiredCarvingToolLevel", featureName, toolLevel, 0, 5, "Required tool level for carving of this material")
    override val suitableCarvingToolLevel: Int = config.getInt("suitableCarvingToolLevel", featureName, toolLevel, 0, 5, "Suitable tool level for carving of this material")
    override val suitableToolDamageMultiplier: Int = config.getInt("suitableCarvingToolDamageMultiplier", featureName, 0, 0, 20, "Tool damage multiplier for suitable carving of this material")
    override val unsuitableToolDamageMultiplier: Int = config.getInt("suitableCarvingToolDamageMultiplier", featureName, 10, 0, 20, "Tool damage multiplier for unsuitable carving of this material")

    abstract fun getCarvingBehaviourShim(): ICarvingBehaviour
}
