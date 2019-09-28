package com.jamieswhiteshirt.literalascension.util

import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d

var Entity.pos: Vec3d
    get() = Vec3d(posX, posY, posZ)
    set(value) = setPosition(value.x, value.y, value.z)

val Entity.prevPos: Vec3d get() = Vec3d(prevPosX, prevPosY, prevPosZ)

var Entity.vel: Vec3d
    get() = Vec3d(motionX, motionY, motionZ)
    set(value) {
        motionX = value.x
        motionY = value.y
        motionZ = value.z
    }

fun Entity.getInterpolatedPos(partialTicks: Float): Vec3d {
    val forward = partialTicks.toDouble()
    val backward = 1.0 - forward
    return pos * forward + prevPos * backward
}

fun Entity.getInterpolatedYaw(partialTicks: Float): Float {
    return rotationYaw * partialTicks + prevRotationYaw * (1 - partialTicks)
}

fun Entity.getInterpolatedPitch(partialTicks: Float): Float {
    return rotationPitch * partialTicks + prevRotationPitch * (1 - partialTicks)
}
