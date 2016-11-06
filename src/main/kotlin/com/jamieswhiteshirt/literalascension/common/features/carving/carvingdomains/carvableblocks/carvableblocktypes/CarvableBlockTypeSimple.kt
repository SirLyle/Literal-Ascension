package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.carvableblocktypes

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.CarvableBlock
import net.minecraft.block.state.IBlockState

open class CarvableBlockTypeSimple(blockState: IBlockState, name: String, parent: CarvableBlock) : CarvableBlockType(
        blockState,
        name,
        parent
)
