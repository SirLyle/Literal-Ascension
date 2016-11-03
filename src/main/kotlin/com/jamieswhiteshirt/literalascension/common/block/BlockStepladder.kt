package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import com.jamieswhiteshirt.literalascension.common.features.stepladders.Stepladder
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockStepladder(val feature: Stepladder) : Block(feature.modelState.material), ISpecialLadderBlock {
    companion object {
        val SEGMENTS: IntRange = 0..2
        val FACING: PropertyDirection = BlockHorizontal.FACING
        val SEGMENT: PropertyInteger = PropertyInteger.create("segment", SEGMENTS.first, SEGMENTS.last)

        private val collisionBoxesX = arrayOf(
                arrayOf(
                        AxisAlignedBB( 0.0 / 16.0, 0.0, 0.0,  3.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(13.0 / 16.0, 0.0, 0.0, 16.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 1.0 / 16.0, 0.5, 0.0,  4.0 / 16.0, 1.0, 1.0),
                        AxisAlignedBB(12.0 / 16.0, 0.5, 0.0, 15.0 / 16.0, 1.0, 1.0)
                ),
                arrayOf(
                        AxisAlignedBB( 2.0 / 16.0, 0.0, 0.0,  5.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB(11.0 / 16.0, 0.0, 0.0, 14.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 3.0 / 16.0, 0.5, 0.0,  6.0 / 16.0, 1.0, 1.0),
                        AxisAlignedBB(10.0 / 16.0, 0.5, 0.0, 13.0 / 16.0, 1.0, 1.0)
                ),
                arrayOf(
                        AxisAlignedBB( 4.0 / 16.0, 0.0, 0.0,  7.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 9.0 / 16.0, 0.0, 0.0, 12.0 / 16.0, 0.5, 1.0),
                        AxisAlignedBB( 5.0 / 16.0, 0.5, 0.0, 11.0 / 16.0, 1.0, 1.0)
                )
        )
        private val collisionBoxesZ = arrayOf(
                arrayOf(
                        AxisAlignedBB(0.0, 0.0,  0.0 / 16.0, 1.0, 0.5,  3.0 / 16.0),
                        AxisAlignedBB(0.0, 0.0, 13.0 / 16.0, 1.0, 0.5, 16.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5,  1.0 / 16.0, 1.0, 1.0,  4.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 12.0 / 16.0, 1.0, 1.0, 15.0 / 16.0)
                ),
                arrayOf(
                        AxisAlignedBB(0.0, 0.0,  2.0 / 16.0, 1.0, 0.5,  5.0 / 16.0),
                        AxisAlignedBB(0.0, 0.0, 11.0 / 16.0, 1.0, 0.5, 14.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5,  3.0 / 16.0, 1.0, 1.0,  6.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5, 10.0 / 16.0, 1.0, 1.0, 13.0 / 16.0)
                ),
                arrayOf(
                        AxisAlignedBB(0.0, 0.0,  4.0 / 16.0, 1.0, 0.5,  7.0 / 16.0),
                        AxisAlignedBB(0.0, 0.0,  9.0 / 16.0, 1.0, 0.5, 12.0 / 16.0),
                        AxisAlignedBB(0.0, 0.5,  5.0 / 16.0, 1.0, 1.0, 11.0 / 16.0)
                )
        )
        private val selectionBoxesX = arrayOf(
                AxisAlignedBB(0.0 / 8.0, 0.0, 0.0, 8.0 / 8.0, 1.0, 1.0),
                AxisAlignedBB(1.0 / 8.0, 0.0, 0.0, 7.0 / 8.0, 1.0, 1.0),
                AxisAlignedBB(2.0 / 8.0, 0.0, 0.0, 6.0 / 8.0, 1.0, 1.0)
        )
        private val selectionBoxesZ = arrayOf(
                AxisAlignedBB(0.0, 0.0, 0.0 / 8.0, 1.0, 1.0, 8.0 / 8.0),
                AxisAlignedBB(0.0, 0.0, 1.0 / 8.0, 1.0, 1.0, 7.0 / 8.0),
                AxisAlignedBB(0.0, 0.0, 2.0 / 8.0, 1.0, 1.0, 6.0 / 8.0)
        )
    }

    init {
        soundType = SoundType.LADDER
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(SEGMENT, 0)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun addCollisionBoxToList(state: IBlockState, world: World, pos: BlockPos, entityBox: AxisAlignedBB, collisionBoxes: MutableList<AxisAlignedBB>, entity: Entity?) {
        for (box in getCollisionBoxes(state)) {
            Block.addCollisionBoxToList(pos, entityBox, collisionBoxes, box)
        }
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        @Suppress("DEPRECATION")
        return when (state.getValue(FACING).axis) {
            EnumFacing.Axis.X -> selectionBoxesX[state.getValue(SEGMENT)]
            EnumFacing.Axis.Z -> selectionBoxesZ[state.getValue(SEGMENT)]
            else -> super.getBoundingBox(state, source, pos)
        }
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = feature.item

    override fun damageDropped(state: IBlockState): Int = 0

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: IBlockState): Boolean = false

    @Suppress("OverridingDeprecatedMember")
    override fun collisionRayTrace(state: IBlockState, world: World, pos: BlockPos, start: Vec3d, end: Vec3d): RayTraceResult? {
        val results = getCollisionBoxes(state).map { rayTrace(pos, start, end, it) }.filterNotNull()

        var bestResult: RayTraceResult? = null
        var bestResultDistance = 0.0

        for (result in results) {
            val resultDistance = result.hitVec.squareDistanceTo(end)
            if (resultDistance > bestResultDistance) {
                bestResult = result
                bestResultDistance = resultDistance
            }
        }

        return bestResult
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getHorizontal(meta and 3)).withProperty(SEGMENT, (meta and 15) shr 2)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).horizontalIndex or (state.getValue(SEGMENT) shl 2)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING, SEGMENT)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block) {
        @Suppress("DEPRECATION")
        super.neighborChanged(state, world, pos, block)
        if (!world.isRemote) {
            checkAndDropBlock(world, pos, state)
        }
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        return ItemStack(feature.item)
    }

    override fun getFlammability(world: IBlockAccess, pos: BlockPos, face: EnumFacing): Int {
        return feature.modelState.block.getFlammability(world, pos, face)
    }

    override fun getFireSpreadSpeed(world: IBlockAccess, pos: BlockPos, face: EnumFacing): Int {
        return feature.modelState.block.getFireSpreadSpeed(world, pos, face)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, heldItem: ItemStack?, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (!world.isRemote) {
            if (!player.isCreative) {
                for (drop in getDrops(world, pos, state, 0)) {
                    if (!player.inventory.addItemStackToInventory(drop) && drop.stackSize > 0) {
                        spawnAsEntity(world, pos, drop)
                    }
                }
            }

            removeStepladderSafely(world, pos, state)
        }

        feature.parent.playLadderPickupSound(world, pos, player)
        return true
    }

    override fun removedByPlayer(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer, willHarvest: Boolean): Boolean {
        removeStepladderSafely(world, pos, state)
        return true
    }

    override fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        val facing = state.getValue(FACING)
        for (i in -1..1) {
            if (isIntersectingDefault(pos.offset(facing, i), entity)) {
                return true
            }
        }
        return false
    }

    fun getCollisionBoxes(state: IBlockState): Array<AxisAlignedBB> {
        return when (state.getValue(FACING).axis) {
            EnumFacing.Axis.X -> collisionBoxesX[state.getValue(SEGMENT)]
            EnumFacing.Axis.Z -> collisionBoxesZ[state.getValue(SEGMENT)]
            else -> emptyArray()
        }
    }

    fun removeStepladderSafely(world: World, pos: BlockPos, state: IBlockState) {
        val thisSegment = state.getValue(SEGMENT)
        val segmentPositions = SEGMENTS.map { pos.up(it - thisSegment) }
        val oldStates = segmentPositions.map { world.getBlockState(it) }

        val removeSegments = SEGMENTS.filter {
            oldStates[it].block == this && oldStates[it].getValue(SEGMENT) == it
        }

        for (segment in removeSegments) {
            world.setBlockState(segmentPositions[segment], Blocks.AIR.defaultState, 0)
        }

        val chunk = world.getChunkFromBlockCoords(pos)
        for (segment in removeSegments) {
            world.markAndNotifyBlock(segmentPositions[segment], chunk, oldStates[segment], Blocks.AIR.defaultState, 3)
        }
    }

    fun checkAndDropBlock(world: World, pos: BlockPos, state: IBlockState) {
        if (!canBlockStay(world, pos, state)) {
            dropBlockAsItem(world, pos, state, 0)
            removeStepladderSafely(world, pos, state)
        }
    }

    fun canBlockStay(world: World, pos: BlockPos, state: IBlockState): Boolean {
        val thisSegment = state.getValue(SEGMENT)

        if (thisSegment == 0) {
            if (!world.isSideSolid(pos.down(), EnumFacing.UP)) {
                return false
            }
        }

        for (segment in SEGMENTS) {
            val otherState = world.getBlockState(pos.up(segment - thisSegment))
            if (otherState.block != this || otherState.getValue(SEGMENT) != segment) {
                return false
            }
        }

        return true
    }

    fun placeStepladder(world: World, pos: BlockPos, horizontalFacing: EnumFacing) {
        val baseState = defaultState.withProperty(BlockStepladder.FACING, horizontalFacing)
        for (segment in SEGMENTS) {
            world.setBlockState(pos.up(segment), baseState.withProperty(BlockStepladder.SEGMENT, segment))
        }
    }
}
