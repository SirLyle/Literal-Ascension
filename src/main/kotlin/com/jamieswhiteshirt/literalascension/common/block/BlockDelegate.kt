package com.jamieswhiteshirt.literalascension.common.block

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.Explosion
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

abstract class BlockDelegate(val modelState: IBlockState) : Block(modelState.material) {
    val modelBlock: Block = modelState.block

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(stateIn: IBlockState, world: World, pos: BlockPos, rand: Random) {
        modelBlock.randomDisplayTick(modelState, world, pos, rand)
    }

    override fun onBlockClicked(world: World, pos: BlockPos, player: EntityPlayer) {
        modelBlock.onBlockClicked(world, pos, player)
    }

    override fun onBlockDestroyedByPlayer(world: World, pos: BlockPos, state: IBlockState) {
        modelBlock.onBlockDestroyedByPlayer(world, pos, modelState)
    }

    @SideOnly(Side.CLIENT)
    @Suppress("OverridingDeprecatedMember")
    override fun getPackedLightmapCoords(state: IBlockState, source: IBlockAccess, pos: BlockPos): Int {
        @Suppress("DEPRECATION")
        return modelBlock.getPackedLightmapCoords(modelState, source, pos)
    }

    override fun getExplosionResistance(exploder: Entity?): Float {
        return modelBlock.getExplosionResistance(exploder)
    }

    override fun getExplosionResistance(world: World, pos: BlockPos, exploder: Entity?, explosion: Explosion): Float {
        return modelBlock.getExplosionResistance(world, pos, exploder, explosion)
    }

    override fun tickRate(world: World): Int {
        return modelBlock.tickRate(world)
    }

    override fun modifyAcceleration(world: World, pos: BlockPos, entity: Entity, motion: Vec3d): Vec3d {
        return modelBlock.modifyAcceleration(world, pos, entity, motion)
    }

    override fun getBlockLayer(): BlockRenderLayer {
        return modelBlock.blockLayer
    }

    override fun isCollidable(): Boolean {
        return modelBlock.isCollidable
    }

    override fun canCollideCheck(state: IBlockState, hitIfLiquid: Boolean): Boolean {
        return modelBlock.canCollideCheck(modelState, hitIfLiquid)
    }

    override fun canPlaceBlockAt(world: World, pos: BlockPos): Boolean {
        return modelBlock.canPlaceBlockAt(world, pos)
    }

    override fun onBlockAdded(world: World, pos: BlockPos, state: IBlockState) {
        world.notifyNeighborsOfStateChange(pos, modelBlock, true)
        modelBlock.onBlockAdded(world, pos, modelState)
    }

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        modelBlock.breakBlock(world, pos, modelState)
    }

    override fun onEntityWalk(world: World, pos: BlockPos, entity: Entity) {
        modelBlock.onEntityWalk(world, pos, entity)
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        modelBlock.updateTick(world, pos, modelState, rand)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        return modelBlock.onBlockActivated(world, pos, modelState, player, hand, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F)
    }

    override fun onBlockDestroyedByExplosion(world: World, pos: BlockPos, explosion: Explosion) {
        modelBlock.onBlockDestroyedByExplosion(world, pos, explosion)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: IBlockState, world: IBlockAccess, pos: BlockPos): MapColor {
        @Suppress("DEPRECATION")
        return modelBlock.getMapColor(modelState, world, pos)
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item? {
        return modelBlock.getItemDropped(modelState, rand, fortune)
    }

    override fun damageDropped(state: IBlockState): Int {
        return modelBlock.damageDropped(modelState)
    }

    override fun getItem(world: World, pos: BlockPos, state: IBlockState): ItemStack? {
        val item = Item.getItemFromBlock(modelBlock)
        return ItemStack(item, 1, if (item.hasSubtypes) modelBlock.getMetaFromState(modelState) else 0)
    }

    override fun canSilkHarvest(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer): Boolean {
        return modelBlock.canSilkHarvest(world, pos, state, player)
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        return modelBlock.getPickBlock(modelState, target, world, pos, player)
    }

    override fun getUnlocalizedName(): String {
        return modelBlock.unlocalizedName
    }

    override fun getFlammability(world: IBlockAccess, pos: BlockPos, face: EnumFacing): Int {
        return modelBlock.getFlammability(world, pos, face)
    }

    override fun getFireSpreadSpeed(world: IBlockAccess, pos: BlockPos, face: EnumFacing): Int {
        return modelBlock.getFlammability(world, pos, face)
    }

    override fun getSoundType(state: IBlockState, world: World, pos: BlockPos, entity: Entity?): SoundType {
        return modelBlock.getSoundType(modelState, world, pos, entity)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getBlockHardness(blockState: IBlockState, world: World, pos: BlockPos): Float {
        @Suppress("DEPRECATION")
        return modelBlock.getBlockHardness(modelState, world, pos)
    }

    override fun canSustainLeaves(state: IBlockState, world: IBlockAccess, pos: BlockPos): Boolean {
        return modelBlock.canSustainLeaves(modelState, world, pos)
    }
}
