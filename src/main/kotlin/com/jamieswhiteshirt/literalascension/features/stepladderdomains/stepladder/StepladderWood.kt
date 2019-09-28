package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

open class StepladderWood(logModelState: IBlockState, plankModelState: IBlockState, name: String, parent: StepladderDomain) : StepladderWoodBase(name, parent) {
    override val feetIngredient = ItemStack(logModelState.block, 1, logModelState.block.damageDropped(logModelState))
    override val stepsIngredient = ItemStack(plankModelState.block, 1, plankModelState.block.damageDropped(plankModelState))
}
