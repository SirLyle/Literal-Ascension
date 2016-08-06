package com.jamieswhiteshirt.literalascension.common.block

import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.Explosion
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

abstract class BlockDelegate(val modelState: IBlockState, hardness: Float) : Block(modelState.material) {
    val modelBlock = modelState.block

    init {
        setHardness(hardness)
        soundType = modelBlock.soundType
    }

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(stateIn: IBlockState, worldIn: World, pos: BlockPos, rand: Random) {
        modelBlock.randomDisplayTick(modelState, worldIn, pos, rand)
    }

    override fun onBlockClicked(worldIn: World, pos: BlockPos, playerIn: EntityPlayer) {
        modelBlock.onBlockClicked(worldIn, pos, playerIn)
    }

    override fun onBlockDestroyedByPlayer(worldIn: World, pos: BlockPos, state: IBlockState) {
        modelBlock.onBlockDestroyedByPlayer(worldIn, pos, modelState)
    }

    @SideOnly(Side.CLIENT)
    override fun getPackedLightmapCoords(state: IBlockState, source: IBlockAccess, pos: BlockPos): Int {
        return modelBlock.getPackedLightmapCoords(modelState, source, pos)
    }

    override fun getExplosionResistance(exploder: Entity): Float {
        return modelBlock.getExplosionResistance(exploder)
    }

    override fun tickRate(worldIn: World): Int {
        return modelBlock.tickRate(worldIn)
    }

    override fun modifyAcceleration(worldIn: World, pos: BlockPos, entityIn: Entity, motion: Vec3d): Vec3d {
        return modelBlock.modifyAcceleration(worldIn, pos, entityIn, motion)
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

    override fun canPlaceBlockAt(worldIn: World, pos: BlockPos): Boolean {
        return modelBlock.canPlaceBlockAt(worldIn, pos)
    }

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        modelState.neighborChanged(worldIn, pos, Blocks.AIR)
        modelBlock.onBlockAdded(worldIn, pos, modelState)
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState?) {
        modelBlock.breakBlock(worldIn, pos, modelState)
    }

    override fun onEntityWalk(worldIn: World, pos: BlockPos, entityIn: Entity) {
        modelBlock.onEntityWalk(worldIn, pos, entityIn)
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        modelBlock.updateTick(worldIn, pos, modelState, rand)
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, heldItem: ItemStack?, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        return modelBlock.onBlockActivated(worldIn, pos, modelState, playerIn, hand, heldItem, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F)
    }

    override fun onBlockDestroyedByExplosion(worldIn: World, pos: BlockPos, explosionIn: Explosion) {
        modelBlock.onBlockDestroyedByExplosion(worldIn, pos, explosionIn)
    }

    override fun getMapColor(state: IBlockState?): MapColor {
        return modelBlock.getMapColor(modelState)
    }
}
