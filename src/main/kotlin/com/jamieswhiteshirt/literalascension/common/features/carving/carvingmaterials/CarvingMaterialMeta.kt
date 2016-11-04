package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlock
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

abstract class CarvingMaterialMeta(block: Block, toolLevel: Int, name: String, config: Configuration, parent: CarvingMaterials) : CarvingMaterial(block, toolLevel, name, config, parent) {
    abstract val fromMeta: Map<Int, CarvedBlock?>

    override fun registerCarvingBehaviourShims() {
        parent.parent.registerCarvingBehaviour(block, object : ICarvingBehaviour {
            override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
                return fromMeta[state.block.getMetaFromState(state)]?.tryCarveModelBlock(state, world, pos, facing) ?: false
            }

            override val carvingMaterial: ICarvingMaterial = this@CarvingMaterialMeta
        })
    }
}
