package com.jamieswhiteshirt.literalascension.core.patcher

import net.ilexiconn.llibrary.server.asm.RuntimePatcher
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.ForgeHooks
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.VarInsnNode

class LiteralAscensionRuntimePatcher : RuntimePatcher() {
    override fun onInit() {
        patchClass(ForgeHooks::class.java)
                .patchMethod("isLivingOnLadder", IBlockState::class.java, World::class.java, BlockPos::class.java, EntityLivingBase::class.java, Boolean::class.java)
                .apply(Patch.REPLACE, {
                    it.node(VarInsnNode(Opcodes.ALOAD, 0))
                    it.node(VarInsnNode(Opcodes.ALOAD, 1))
                    it.node(VarInsnNode(Opcodes.ALOAD, 2))
                    it.node(VarInsnNode(Opcodes.ALOAD, 3))
                    it.method(Opcodes.INVOKESTATIC, LiteralAscensionHooks::class.java, "isLivingOnLadder", IBlockState::class.java, World::class.java, BlockPos::class.java, EntityLivingBase::class.java, Boolean::class.java)
                    it.node(Opcodes.IRETURN)
                })
    }
}
