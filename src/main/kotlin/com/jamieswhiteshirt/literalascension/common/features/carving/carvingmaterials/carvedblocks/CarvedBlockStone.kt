package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterialStone
import net.minecraft.block.BlockStone
import net.minecraft.init.Blocks

class CarvedBlockStone(type: BlockStone.EnumType, parent: CarvingMaterialStone) : CarvedBlock(
        Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, type),
        type.getName(),
        type.unlocalizedName,
        parent
)
