package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes

import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.CarvableBlockStone
import net.minecraft.block.BlockStone
import net.minecraft.init.Blocks

class CarvableBlockTypeStone(type: BlockStone.EnumType, parent: CarvableBlockStone) : CarvableBlockTypeSimple(
        Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, type),
        type.getName(),
        parent
)
