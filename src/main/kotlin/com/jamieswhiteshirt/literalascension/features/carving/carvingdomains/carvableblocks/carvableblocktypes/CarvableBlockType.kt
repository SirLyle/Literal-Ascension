package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes

import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.CarvableBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.ForgeRegistries

abstract class CarvableBlockType(val modelState: IBlockState, val typeName: String, override val parent: CarvableBlock) : SubFeature(typeName, parent) {
    val material = parent
    val chute = BlockChute(this)
    val notched = BlockNotched(this)

    open fun tryCarveModelBlock(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            if (facing.axis == EnumFacing.Axis.Y) {
                world.setBlockState(pos, chute.defaultState)
            } else {
                world.setBlockState(pos, notched.defaultState.withProperty(BlockNotched.propertyOf(facing), true))
            }
            parent.parent.parent.parent.spawnCarveParticles(world, pos, facing)
        }
        return true
    }

    override fun register() {
        val baseRegistryName = "${parent.parent.domainName}/${parent.blockName}/$typeName"
        ForgeRegistries.BLOCKS.register(chute.setRegistryName("literalascension", "$baseRegistryName/chute"))
        ForgeRegistries.BLOCKS.register(notched.setRegistryName("literalascension", "$baseRegistryName/notched"))
    }
}
