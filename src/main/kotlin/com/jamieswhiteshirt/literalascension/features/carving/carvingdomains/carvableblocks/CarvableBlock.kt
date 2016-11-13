package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.features.Carving
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.CarvingDomain
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockType
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

abstract class CarvableBlock(val block: Block, toolLevel: Int, val blockName: String, config: Configuration, override val parent: CarvingDomain) : SubFeatureCollection<CarvableBlockType>(blockName, parent), ICarvingMaterial {
    override val requiredCarvingToolLevel: Int = config.getInt("requiredCarvingToolLevel", featureName, toolLevel, 0, 5, "Required tool level for carving of this block")
    override val suitableCarvingToolLevel: Int = config.getInt("suitableCarvingToolLevel", featureName, toolLevel, 0, 5, "Suitable tool level for carving of this block")
    override val suitableToolDamageMultiplier: Int = config.getInt("suitableCarvingToolDamageMultiplier", featureName, 1, 0, 20, "Tool damage multiplier for suitable carving of this block")
    override val unsuitableToolDamageMultiplier: Int = config.getInt("unsuitableCarvingToolDamageMultiplier", featureName, 10, 0, 20, "Tool damage multiplier for unsuitable carving of this block")

    abstract fun registerCarvingBehaviourShims(carving: Carving)
}
