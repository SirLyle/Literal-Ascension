package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials

import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.api.ICarvingMaterial
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlockNewWood
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class CarvingMaterialNewWood(config: Configuration, parent: CarvingMaterials) : CarvingMaterial(Blocks.LOG2, 0, "wood2", config, parent) {
    val LOG_ACACIA   = optionalOn(config, CarvedBlockNewWood(BlockPlanks.EnumType.ACACIA, this))
    val LOG_DARK_OAK = optionalOn(config, CarvedBlockNewWood(BlockPlanks.EnumType.DARK_OAK, this))

    val fromType = mapOf(
            BlockPlanks.EnumType.ACACIA to LOG_ACACIA,
            BlockPlanks.EnumType.DARK_OAK to LOG_DARK_OAK
    )

    override fun registerCarvingBehaviourShims() {
        parent.parent.registerCarvingBehaviour(block, object : ICarvingBehaviour {
            override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
                return fromType[state.getValue(BlockNewLog.VARIANT)]?.tryCarveModelBlock(state, world, pos, facing) ?: false
            }

            override val carvingMaterial: ICarvingMaterial = this@CarvingMaterialNewWood
        })
    }
}
