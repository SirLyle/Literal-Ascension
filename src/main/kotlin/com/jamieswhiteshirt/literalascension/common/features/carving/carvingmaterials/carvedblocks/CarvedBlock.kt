package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks

import com.jamieswhiteshirt.literalascension.common.SubFeature
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterial
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class CarvedBlock(val modelState: IBlockState, val name: String, val unlocalizedName: String, override val parent: CarvingMaterial) : SubFeature(name, parent) {
    val material = parent
    val chute = BlockChute(this)
    val notched = BlockNotched(this)

    constructor(modelState: IBlockState, name: String, parent: CarvingMaterial) : this(modelState, name, name, parent)

    open fun tryCarveModelBlock(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            if (facing.axis == EnumFacing.Axis.Y) {
                world.setBlockState(pos, chute.defaultState)
            } else {
                world.setBlockState(pos, notched.defaultState.withProperty(BlockNotched.propertyOf(facing), true))
            }
            parent.parent.parent.spawnCarveParticles(world, pos, facing)
        }
        return true
    }

    override fun register() {
        GameRegistry.register(chute.setRegistryName("literalascension", "${name}_chute"))
        GameRegistry.register(notched.setRegistryName("literalascension", "${name}_notched"))
    }

    override fun registerRecipes() { }
}
