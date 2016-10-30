package com.jamieswhiteshirt.literalascension.common.item

import com.google.common.collect.Multimap
import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingtools.CarvingTool
import com.jamieswhiteshirt.literalascension.common.playCarveSound
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
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

class ItemCarvingTool(val feature: CarvingTool, val toolMaterial: ToolMaterial) : Item() {
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
            val carvingBehaviour = getCarvingBehaviour(state)
            if (carvingBehaviour != null) {
                val carvingMaterial = carvingBehaviour.carvingMaterial
                if (carvingMaterial.requiredCarvingToolLevel <= toolMaterial.harvestLevel) {
                    if (carvingBehaviour.tryCarve(state, world, pos, facing, hitX, hitY, hitZ)) {
                        if (!world.isRemote) {
                            if (toolMaterial.harvestLevel < carvingMaterial.suitableCarvingToolLevel) {
                                stack.damageItem(carvingMaterial.unsuitableToolDamageMultiplier, player)
                            } else {
                                stack.damageItem(1, player)
                            }

                            world.playCarveSound(pos, player)
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

    private fun getCarvingBehaviour(state: IBlockState): ICarvingBehaviour? {
        val block = state.block
        return when (block) {
            is ICarvingBehaviour -> block
            else -> feature.parent.parent.carvingBehaviourShims[block]
        }
    }
}
