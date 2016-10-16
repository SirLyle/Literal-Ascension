package com.jamieswhiteshirt.literalascension.common.item

import com.google.common.collect.Multimap
import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.api.ICarvableBlock
import com.jamieswhiteshirt.literalascension.api.ICarveMaterial
import com.jamieswhiteshirt.literalascension.common.carvedblock.CarvedBlock
import com.jamieswhiteshirt.literalascension.common.init.CarvedBlocks
import com.jamieswhiteshirt.literalascension.common.network.message.MessageBlockCarved
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockStone
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

class ItemCarvingTool(val toolMaterial: ToolMaterial) : Item() {
    private val speed = toolMaterial.damageVsEntity + 1.0F

    init {
        maxStackSize = 1
        maxDamage = toolMaterial.maxUses
    }

    override fun onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL
        } else {
            val state = world.getBlockState(pos)
            val carvableBlock = getCarvableBlock(state)
            if (carvableBlock != null) {
                var carvedBlockType = carvableBlock.getCarveMaterial(state, world, pos)
                if (carvedBlockType.requiredCarvingToolLevel <= toolMaterial.harvestLevel) {
                    if (carvableBlock.tryCarve(state, world, pos, facing, hitX, hitY, hitZ)) {
                        if (!world.isRemote) {
                            if (toolMaterial.harvestLevel < carvedBlockType.suitableCarvingToolLevel) {
                                stack.damageItem(carvedBlockType.unsuitableToolDamageMultiplier, player)
                            } else {
                                stack.damageItem(1, player)
                            }

                            val targetPoint = NetworkRegistry.TargetPoint(world.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 64.0)
                            LiteralAscension.packetHandler.sendToAllAround(MessageBlockCarved(pos), targetPoint)
                        }

                        return EnumActionResult.SUCCESS
                    }
                }
            }

            return EnumActionResult.PASS
        }
    }

    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {
        stack.damageItem(1, attacker)
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean {
        return true
    }

    override fun getIsRepairable(toRepair: ItemStack, repair: ItemStack): Boolean {
        val requiredItem = this.toolMaterial.repairItemStack
        if (requiredItem != null) {
            return OreDictionary.itemMatches(requiredItem, repair, false)
        } else {
            return super.getIsRepairable(toRepair, repair)
        }
    }

    override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
        val multimap = super.getAttributeModifiers(slot, stack)

        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.attributeUnlocalizedName, AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0, 0))
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.attributeUnlocalizedName, AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", speed - 4.0, 0))
        }

        return multimap
    }

    private fun getCarvableBlock(state: IBlockState): ICarvableBlock? {
        val block = state.block
        return when (block) {
            is ICarvableBlock -> block
            is BlockOldLog -> object : CarvableBlockShim() {
                override fun canCarve(state: IBlockState): Boolean {
                    return state.getValue(BlockOldLog.LOG_AXIS) == BlockLog.EnumAxis.Y
                }

                override fun getCarvedBlock(state: IBlockState): CarvedBlock {
                    return CarvedBlocks.fromOldLogType[state.getValue(BlockOldLog.VARIANT)]!!
                }
            }
            is BlockNewLog -> object : CarvableBlockShim() {
                override fun canCarve(state: IBlockState): Boolean {
                    return state.getValue(BlockNewLog.LOG_AXIS) == BlockLog.EnumAxis.Y
                }

                override fun getCarvedBlock(state: IBlockState): CarvedBlock {
                    return CarvedBlocks.fromNewLogType[state.getValue(BlockNewLog.VARIANT)]!!
                }
            }
            is BlockStone -> object : CarvableBlockShim() {
                override fun canCarve(state: IBlockState): Boolean {
                    return true
                }

                override fun getCarvedBlock(state: IBlockState): CarvedBlock {
                    return CarvedBlocks.fromStoneType[state.getValue(BlockStone.VARIANT)]!!
                }
            }
            else -> null
        }
    }

    private abstract class CarvableBlockShim : ICarvableBlock {
        override fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
            if (canCarve(state)) {
                val carvedBlocks = this.getCarvedBlock(state)
                if (!world.isRemote) {
                    if (facing.axis == EnumFacing.Axis.Y) {
                        world.setBlockState(pos, carvedBlocks.chute.defaultState)
                    } else {
                        val notchedBlock = carvedBlocks.notched
                        notchedBlock.tryCarve(notchedBlock.defaultState, world, pos, facing, hitX, hitY, hitZ)
                    }
                }
                return true
            }

            return false
        }

        override fun getCarveMaterial(state: IBlockState, world: World, pos: BlockPos): ICarveMaterial {
            return getCarvedBlock(state).material
        }

        abstract fun canCarve(state: IBlockState): Boolean

        abstract fun getCarvedBlock(state: IBlockState): CarvedBlock
    }
}
