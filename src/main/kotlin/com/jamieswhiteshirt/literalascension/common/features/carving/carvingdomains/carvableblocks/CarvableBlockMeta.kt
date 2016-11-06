package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.CarvingDomain
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockType
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

abstract class CarvableBlockMeta(block: Block, toolLevel: Int, name: String, config: Configuration, parent: CarvingDomain) : CarvableBlock(block, toolLevel, name, config, parent) {
    abstract val fromMeta: Map<Int, CarvableBlockType?>

    override fun registerCarvingBehaviourShims(carving: Carving) {
        carving.registerCarvingBehaviour(block, object : ICarvingBehaviour {
            override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
                return fromMeta[state.block.getMetaFromState(state)]?.tryCarveModelBlock(state, world, pos, facing) ?: false
            }

            override val carvingMaterial: ICarvingMaterial = this@CarvableBlockMeta
        })
    }
}
