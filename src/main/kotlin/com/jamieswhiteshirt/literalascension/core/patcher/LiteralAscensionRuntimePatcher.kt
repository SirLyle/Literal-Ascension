package com.jamieswhiteshirt.literalascension.core.patcher

import net.ilexiconn.llibrary.server.asm.RuntimePatcher
import net.minecraft.entity.EntityLivingBase
import org.objectweb.asm.Opcodes

class LiteralAscensionRuntimePatcher : RuntimePatcher() {
    override fun onInit() {
        patchClass(EntityLivingBase::class.java)
                .patchMethod("isOnLadder", Boolean::class.java)
                .apply(Patch.REPLACE, {
                    it.`var`(Opcodes.ALOAD, 0)
                    it.method(Opcodes.INVOKESTATIC, LiteralAscensionHooks::class.java, "isLivingOnLadder", EntityLivingBase::class.java, Boolean::class.java)
                    it.node(Opcodes.IRETURN)
                }).pop()
    }
}
