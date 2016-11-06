package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.CarvingDomainMinecraft
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockTypeOldWood
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class CarvableBlockOldWood(config: Configuration, parent: CarvingDomainMinecraft) : CarvableBlock(Blocks.LOG, 0, "log", config, parent) {
    val LOG_OAK      = optionalOn(config, CarvableBlockTypeOldWood(BlockPlanks.EnumType.OAK, this))
    val LOG_SPRUCE   = optionalOn(config, CarvableBlockTypeOldWood(BlockPlanks.EnumType.SPRUCE, this))
    val LOG_BIRCH    = optionalOn(config, CarvableBlockTypeOldWood(BlockPlanks.EnumType.BIRCH, this))
    val LOG_JUNGLE   = optionalOn(config, CarvableBlockTypeOldWood(BlockPlanks.EnumType.JUNGLE, this))

    val fromType = mapOf(
            BlockPlanks.EnumType.OAK to LOG_OAK,
            BlockPlanks.EnumType.SPRUCE to LOG_SPRUCE,
            BlockPlanks.EnumType.BIRCH to LOG_BIRCH,
            BlockPlanks.EnumType.JUNGLE to LOG_JUNGLE
    )

    override fun registerCarvingBehaviourShims(carving: Carving) {
        carving.registerCarvingBehaviour(block, object : ICarvingBehaviour {
            override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
                return fromType[state.getValue(BlockOldLog.VARIANT)]?.tryCarveModelBlock(state, world, pos, facing) ?: false
            }

            override val carvingMaterial: ICarvingMaterial = this@CarvableBlockOldWood
        })
    }
}
