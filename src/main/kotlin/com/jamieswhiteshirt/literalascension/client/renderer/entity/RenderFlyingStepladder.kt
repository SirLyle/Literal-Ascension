package com.jamieswhiteshirt.literalascension.client.renderer.entity

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.entity.EntityFlyingStepladder
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.StepladderFireworks
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos

class RenderFlyingStepladder(val feature: StepladderFireworks, renderManager: RenderManager) : Render<EntityFlyingStepladder>(renderManager) {
    override fun getEntityTexture(entity: EntityFlyingStepladder): ResourceLocation = TextureMap.LOCATION_BLOCKS_TEXTURE

    override fun doRender(entity: EntityFlyingStepladder, x: Double, y: Double, z: Double, entityYaw: Float, partialTicks: Float) {
        val world = entity.world
        val basePos = BlockPos(entity.posX, entity.posY, entity.posZ)
        val baseState = feature.block.defaultState.withProperty(BlockStepladder.FACING, entity.facing)

        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer
        val blockRendererDispatcher = Minecraft.getMinecraft().blockRendererDispatcher

        GlStateManager.pushMatrix()
        GlStateManager.disableLighting()
        GlStateManager.translate(x, y + 1.5, z)
        //GlStateManager.translate(x - basePos.x - 0.5, y - basePos.y, z - basePos.z - 0.5)
        GlStateManager.rotate(-entityYaw, 0.0F, 1.0F, 0.0F)
        GlStateManager.rotate(entity.rotationPitch * partialTicks + entity.prevRotationPitch * (1.0F - partialTicks), 1.0F, 0.0F, 0.0F)
        GlStateManager.rotate(entityYaw, 0.0F, 1.0F, 0.0F)
        GlStateManager.translate(-(basePos.x + 0.5), -1.5 - basePos.y.toDouble(), -(basePos.z + 0.5))
        if (renderOutlines) {
            GlStateManager.enableColorMaterial()
            GlStateManager.enableOutlineMode(getTeamColor(entity))
        }

        buffer.begin(7, DefaultVertexFormats.BLOCK)
        for (i in BlockStepladder.SEGMENTS) {
            val state = baseState.withProperty(BlockStepladder.SEGMENT, i)
            val pos = basePos.up(i)
            blockRendererDispatcher.blockModelRenderer.renderModel(world, blockRendererDispatcher.getModelForState(state), state, pos, buffer, false, 0)
        }
        tessellator.draw()

        if (renderOutlines) {
            GlStateManager.disableOutlineMode()
            GlStateManager.disableColorMaterial()
        }
        GlStateManager.enableLighting()
        GlStateManager.popMatrix()

        super.doRender(entity, x, y, z, entityYaw, partialTicks)
    }
}