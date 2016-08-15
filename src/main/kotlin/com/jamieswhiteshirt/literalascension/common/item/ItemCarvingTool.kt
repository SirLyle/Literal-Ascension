package com.jamieswhiteshirt.literalascension.common.item

import com.google.common.collect.Multimap
import com.jamieswhiteshirt.literalascension.api.ICarvableBlock
import com.jamieswhiteshirt.literalascension.common.EnumCarvedBlockType
import net.minecraft.block.*
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
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
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
        }
        else {
            val state = world.getBlockState(pos)
            val carvableBlock = getCarvableBlock(state)
            if (carvableBlock != null && carvableBlock.carve(state, world, pos, facing, hitX, hitY, hitZ, toolMaterial.harvestLevel)) {
                if (!world.isRemote) {
                    stack.damageItem(1, player)
                }

                //val soundType = state.block.soundType
                //world.playSound(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5, soundType.breakSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F, false)

                return EnumActionResult.SUCCESS
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
        }
        else {
            return super.getIsRepairable(toRepair, repair)
        }
    }

    override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
        val multimap = super.getAttributeModifiers(slot, stack)

        if (slot == EntityEquipmentSlot.MAINHAND)
        {
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
                override fun getCarvedBlockType(state: IBlockState): EnumCarvedBlockType? {
                    if (state.getValue(BlockOldLog.LOG_AXIS) == BlockLog.EnumAxis.Y) {
                        return EnumCarvedBlockType.fromOldLogType[state.getValue(BlockOldLog.VARIANT)]
                    }
                    else {
                        return null
                    }
                }
            }
            is BlockNewLog -> object : CarvableBlockShim() {
                override fun getCarvedBlockType(state: IBlockState): EnumCarvedBlockType? {
                    if (state.getValue(BlockNewLog.LOG_AXIS) == BlockLog.EnumAxis.Y) {
                        return EnumCarvedBlockType.fromNewLogType[state.getValue(BlockNewLog.VARIANT)]
                    }
                    else {
                        return null
                    }
                }
            }
            is BlockStone -> object : CarvableBlockShim() {
                override fun getCarvedBlockType(state: IBlockState): EnumCarvedBlockType? {
                    return EnumCarvedBlockType.fromStoneType[state.getValue(BlockStone.VARIANT)]
                }
            }
            else -> null
        }
    }

    private abstract class CarvableBlockShim : ICarvableBlock {
        override fun carve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, toolLevel: Int): Boolean {
            val carvedBlockType = getCarvedBlockType(state)
            if (carvedBlockType != null && toolLevel >= carvedBlockType.material.toolLevel) {
                if (!world.isRemote) {
                    if (facing.axis == EnumFacing.Axis.Y) {
                        world.setBlockState(pos, carvedBlockType.chuteBlock().defaultState)
                    }
                    else {
                        val notchedBlock = carvedBlockType.notchedBlock()
                        notchedBlock.carve(notchedBlock.defaultState, world, pos, facing, hitX, hitY, hitZ, toolLevel)
                    }
                }
                return true
            }

            return false
        }

        abstract fun getCarvedBlockType(state: IBlockState): EnumCarvedBlockType?
    }
}
