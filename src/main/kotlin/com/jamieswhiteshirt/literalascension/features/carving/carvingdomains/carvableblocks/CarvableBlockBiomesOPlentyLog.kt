package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.CarvingDomainBiomesOPlenty
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockTypeSimple
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvableBlockBiomesOPlentyLog(block: Block, name: String, types: Array<String>, config: Configuration, parent: CarvingDomainBiomesOPlenty) : CarvableBlockMeta(block, 0, name, config, parent) {
    override val fromMeta = types.indices.associate {
        it + 4 to optionalOn(config, CarvableBlockTypeSimple(block.getStateFromMeta(it + 4), types[it], this))
    }
}
