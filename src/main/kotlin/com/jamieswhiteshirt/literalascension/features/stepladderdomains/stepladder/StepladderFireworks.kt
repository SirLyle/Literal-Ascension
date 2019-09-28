package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.client.renderer.entity.RenderFlyingStepladder
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.entity.EntityFlyingStepladder
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.registry.EntityRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class StepladderFireworks(name: String, parent: StepladderDomain) : StepladderMaterial(Items.FIREWORKS, name, parent) {
    override fun register() {
        super.register()
        EntityRegistry.registerModEntity(
                ResourceLocation("literalascension", "flying_stepladder"),
                EntityFlyingStepladder::class.java,
                "flying_stepladder",
                0,
                LiteralAscension,
                64,
                10,
                true)
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        super.registerRenderers()
        RenderingRegistry.registerEntityRenderingHandler(EntityFlyingStepladder::class.java, { RenderFlyingStepladder(this, it) })
    }

    override fun onPickUp(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, drops: List<ItemStack>) {
        if (!world.isRemote) {
            val entity = EntityFlyingStepladder(world, pos.down(state.getValue(BlockStepladder.SEGMENT)), state.getValue(BlockStepladder.FACING))
            world.spawnEntity(entity)
            player.startRiding(entity)
        }
    }
}
