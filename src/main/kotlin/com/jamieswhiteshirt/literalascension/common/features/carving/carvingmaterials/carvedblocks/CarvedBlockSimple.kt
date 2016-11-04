package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterial
import net.minecraft.block.state.IBlockState

open class CarvedBlockSimple(blockState: IBlockState, name: String, parent: CarvingMaterial) : CarvedBlock(
        blockState,
        name,
        parent
)
