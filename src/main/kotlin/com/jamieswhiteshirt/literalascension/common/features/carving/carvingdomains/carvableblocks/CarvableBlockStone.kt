package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.CarvingDomainMinecraft
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockTypeStone
import net.minecraft.block.BlockStone
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class CarvableBlockStone(config: Configuration, parent: CarvingDomainMinecraft) : CarvableBlock(Blocks.STONE, 2, "stone", config, parent) {
    val STONE    = optionalOn(config, CarvableBlockTypeStone(BlockStone.EnumType.STONE, this))
    val GRANITE  = optionalOn(config, CarvableBlockTypeStone(BlockStone.EnumType.GRANITE, this))
    val DIORITE  = optionalOn(config, CarvableBlockTypeStone(BlockStone.EnumType.DIORITE, this))
    val ANDESITE = optionalOn(config, CarvableBlockTypeStone(BlockStone.EnumType.ANDESITE, this))

    val fromType = mapOf(
            BlockStone.EnumType.STONE to STONE,
            BlockStone.EnumType.GRANITE to GRANITE,
            BlockStone.EnumType.DIORITE to DIORITE,
            BlockStone.EnumType.ANDESITE to ANDESITE
    )

    override fun registerCarvingBehaviourShims(carving: Carving) {
        carving.registerCarvingBehaviour(block, object : ICarvingBehaviour {
            override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
                return fromType[state.getValue(BlockStone.VARIANT)]?.tryCarveModelBlock(state, world, pos, facing) ?: false
            }

            override val carvingMaterial: ICarvingMaterial = this@CarvableBlockStone
        })
    }
}
