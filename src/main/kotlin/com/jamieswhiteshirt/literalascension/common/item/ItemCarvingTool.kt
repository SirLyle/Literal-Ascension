package com.jamieswhiteshirt.literalascension.common.item

import com.google.common.collect.Multimap
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingtools.CarvingTool
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
    val carvingInteraction = object : CarvingInteraction(feature.parent.parent) {
        override fun getHarvestLevel(stack: ItemStack): Int = toolMaterial.harvestLevel

        override fun damageItem(stack: ItemStack, amount: Int, player: EntityPlayer) = stack.damageItem(amount, player)
    }

    init {
        maxStackSize = 1
        maxDamage = toolMaterial.maxUses
    }

    override fun onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        return carvingInteraction(stack, player, world, pos, facing, hitX, hitY, hitZ)
    }

    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {
        stack.damageItem(1, attacker)
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean = false

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
}
