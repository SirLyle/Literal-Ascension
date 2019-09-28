package com.jamieswhiteshirt.literalascension.common.item

import com.google.common.collect.Multimap
import com.jamieswhiteshirt.literalascension.features.carving.carvingtools.CarvingTool
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
    private val speed = toolMaterial.attackDamage + 1.0F

    init {
        maxStackSize = 1
        maxDamage = toolMaterial.maxUses
    }

    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val stack = player.getHeldItem(hand)
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL
        } else {
            val state = world.getBlockState(pos)
            val carvingBehaviour = feature.parent.parent.getCarvingBehaviour(state)
            if (carvingBehaviour != null) {
                val carvingMaterial = carvingBehaviour.carvingMaterial
                if (carvingMaterial.requiredCarvingToolLevel <= toolMaterial.harvestLevel) {
                    if (carvingBehaviour.tryCarve(state, world, pos, facing, hitX, hitY, hitZ)) {
                        if (!world.isRemote) {
                            if (toolMaterial.harvestLevel < carvingMaterial.suitableCarvingToolLevel) {
                                stack.damageItem(carvingMaterial.unsuitableToolDamageMultiplier, player)
                            } else {
                                stack.damageItem(carvingMaterial.suitableToolDamageMultiplier, player)
                            }
                        }

                        feature.parent.parent.playCarveSound(world, pos, player)
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
    override fun isFull3D(): Boolean = false

    override fun getIsRepairable(toRepair: ItemStack, repair: ItemStack): Boolean {
        val requiredItem = this.toolMaterial.repairItemStack
        if (!requiredItem.isEmpty) {
            return OreDictionary.itemMatches(requiredItem, repair, false)
        } else {
            return super.getIsRepairable(toRepair, repair)
        }
    }

    override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
        val multimap = super.getAttributeModifiers(slot, stack)

        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.name, AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0, 0))
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.name, AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", speed - 4.0, 0))
        }

        return multimap
    }
}
