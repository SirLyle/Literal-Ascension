package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import com.jamieswhiteshirt.literalascension.features.ClimbingRope
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.stats.StatList
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockClimbingRope(val feature: ClimbingRope) : Block(Material.CARPET), ISpecialLadderBlock {
    companion object {
        val FACING: PropertyDirection = PropertyDirection.create("facing", { it != EnumFacing.DOWN })

        private val boundingBoxes = arrayOf(
                AxisAlignedBB(6.0 / 16.0, 0.0, 6.0 / 16.0, 10.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB(6.0 / 16.0, 0.0, 0.0 / 16.0, 10.0 / 16.0, 1.0, 4.0 / 16.0),
                AxisAlignedBB(6.0 / 16.0, 0.0, 12.0 / 16.0, 10.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, 6.0 / 16.0, 4.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB(12.0 / 16.0, 0.0, 6.0 / 16.0, 16.0 / 16.0, 1.0, 10.0 / 16.0)
        )

        private val collisionBoxes = arrayOf(
                AxisAlignedBB(8.0 / 16.0, 0.0, 8.0 / 16.0, 8.0 / 16.0, 1.0, 8.0 / 16.0),
                AxisAlignedBB(8.0 / 16.0, 0.0, 2.0 / 16.0, 8.0 / 16.0, 1.0, 2.0 / 16.0),
                AxisAlignedBB(8.0 / 16.0, 0.0, 14.0 / 16.0, 8.0 / 16.0, 1.0, 14.0 / 16.0),
                AxisAlignedBB(2.0 / 16.0, 0.0, 8.0 / 16.0, 2.0 / 16.0, 1.0, 8.0 / 16.0),
                AxisAlignedBB(14.0 / 16.0, 0.0, 8.0 / 16.0, 14.0 / 16.0, 1.0, 8.0 / 16.0)
        )

        private val ladderCollisionBoxes = arrayOf(
                AxisAlignedBB(0.0 / 16.0, 0.0, 0.0 / 16.0, 16.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, -6.0 / 16.0, 18.0 / 16.0, 1.0, 10.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, 6.0 / 16.0, 16.0 / 16.0, 1.0, 22.0 / 16.0),
                AxisAlignedBB(-6.0 / 16.0, 0.0, 0.0 / 16.0, 10.0 / 16.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(6.0 / 16.0, 0.0, 0.0 / 16.0, 22.0 / 16.0, 1.0, 16.0 / 16.0)
        )
    }

    init {
        soundType = SoundType.CLOTH
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.UP)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return boundingBoxes[state.getValue(FACING).index - 1]
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getCollisionBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return collisionBoxes[state.getValue(FACING).index - 1]
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = feature.item

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    override fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        return ladderCollisionBoxes[state.getValue(FACING).index - 1].offset(pos).intersectsWith(entity.entityBoundingBox)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getFront(meta + 1))
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index - 1
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    @Suppress("OverridingDeprecatedMember")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block, changePos: BlockPos) {
        @Suppress("DEPRECATION")
        super.neighborChanged(state, world, pos, block, changePos)
        checkAndDropBlock(world, pos, state)
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        return ItemStack(feature.item)
    }

    override fun removedByPlayer(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer, willHarvest: Boolean): Boolean {
        onBlockHarvested(world, pos, state, player)
        val facing = state.getValue(FACING)
        val breakPos = BlockPos.MutableBlockPos(pos)
        while (feature.isMatchingBlock(world, breakPos.down(), facing)) {
            breakPos.y--
        }
        return world.setBlockState(breakPos, net.minecraft.init.Blocks.AIR.defaultState, if (world.isRemote) 11 else 3)
    }

    override fun harvestBlock(world: World, player: EntityPlayer, pos: BlockPos, state: IBlockState, te: TileEntity?, stack: ItemStack?) {
        player.addStat(StatList.getBlockStats(this))
        player.addExhaustion(0.025F)
        harvesters.set(player)
        val fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack)
        if (shouldLaunchDrop(world, pos, state, player)) {
            launchDropBlockAsItem(world, pos, state, fortune)
        } else {
            dropBlockAsItem(world, pos, state, fortune)
        }
        harvesters.set(null)
    }

    fun shouldLaunchDrop(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer): Boolean {
        if (player.posY > pos.y + 0.5 && isAttachedToBlock(state, world, pos)) {
            fun isNonSolid(pos: BlockPos): Boolean = !world.getBlockState(pos).material.isSolid

            return isNonSolid(pos.up()) && isNonSolid(pos.up().offset(state.getValue(FACING)))
        } else {
            return false
        }
    }

    fun launchDropBlockAsItem(world: World, pos: BlockPos, state: IBlockState, fortune: Int) {
        launchDropBlockAsItemWithChance(world, pos, state, 1.0F, fortune)
    }

    fun launchDropBlockAsItemWithChance(world: World, pos: BlockPos, state: IBlockState, chance: Float, fortune: Int) {
        if (!world.isRemote && !world.restoringBlockSnapshots && world.getGameRules().getBoolean("doTileDrops")) { // do not drop items while restoring blockstates, prevents item dupe
            val facing = state.getValue(FACING)
            val drops = getDrops(world, pos, state, fortune)
            val actualChance = net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, fortune, chance, false, harvesters.get())

            for (drop in drops){
                if (world.rand.nextFloat() <= actualChance) {
                    if (captureDrops.get()) {
                        capturedDrops.get().add(drop)
                        return
                    }
                    launchDropAsEntity(world, pos, drop, facing)
                }
            }
        }
    }

    fun launchDropAsEntity(world: World, pos: BlockPos, stack: ItemStack, facing: EnumFacing) {
        val posX = pos.x + world.rand.nextFloat() * 0.5 + 0.25
        val posY = pos.y + world.rand.nextFloat() * 0.25 + 0.75
        val posZ = pos.z + world.rand.nextFloat() * 0.5 + 0.25
        val entityitem = EntityItem(world, posX, posY, posZ, stack)
        entityitem.motionX = facing.frontOffsetX * 0.1
        entityitem.motionY = 0.3
        entityitem.motionZ = facing.frontOffsetZ * 0.1
        entityitem.setDefaultPickupDelay()
        world.spawnEntityInWorld(entityitem)
    }

    fun checkAndDropBlock(world: World, pos: BlockPos, state: IBlockState) {
        if (!isAttached(state, world, pos)) {
            world.destroyBlock(pos, true)
        }
    }

    fun isAttachedToRope(state: IBlockState, world: World, pos: BlockPos): Boolean {
        return feature.isMatchingBlock(world, pos.up(), state.getValue(FACING))
    }

    fun isAttachedToBlock(state: IBlockState, world: World, pos: BlockPos): Boolean {
        val facing = state.getValue(FACING)
        val attachPos = pos.offset(facing)
        return world.isSideSolid(attachPos, facing.opposite)
    }

    fun isAttached(state: IBlockState, world: World, pos: BlockPos): Boolean {
        return isAttachedToRope(state, world, pos) || isAttachedToBlock(state, world, pos)
    }
}
