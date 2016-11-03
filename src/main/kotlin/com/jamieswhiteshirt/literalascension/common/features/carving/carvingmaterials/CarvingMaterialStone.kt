package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlockStone
import net.minecraft.block.BlockStone
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class CarvingMaterialStone(config: Configuration, parent: CarvingMaterials) : CarvingMaterial(Blocks.STONE, 2, "stone", config, parent) {
    val STONE    = optionalOn(config, CarvedBlockStone(BlockStone.EnumType.STONE, this))
    val GRANITE  = optionalOn(config, CarvedBlockStone(BlockStone.EnumType.GRANITE, this))
    val DIORITE  = optionalOn(config, CarvedBlockStone(BlockStone.EnumType.DIORITE, this))
    val ANDESITE = optionalOn(config, CarvedBlockStone(BlockStone.EnumType.ANDESITE, this))

    val fromType = mapOf(
            BlockStone.EnumType.STONE to STONE,
            BlockStone.EnumType.GRANITE to GRANITE,
            BlockStone.EnumType.DIORITE to DIORITE,
            BlockStone.EnumType.ANDESITE to ANDESITE
    )

    override fun registerCarvingBehaviourShims() {
        parent.parent.registerCarvingBehaviour(block, object : ICarvingBehaviour {
            override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
                return fromType[state.getValue(BlockStone.VARIANT)]?.tryCarveModelBlock(state, world, pos, facing) ?: false
            }

            override val carvingMaterial: ICarvingMaterial = this@CarvingMaterialStone
        })
    }
}
