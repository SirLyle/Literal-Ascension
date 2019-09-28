package com.jamieswhiteshirt.literalascension.util

import net.minecraft.util.math.Vec3d

operator fun Vec3d.plus(that: Vec3d): Vec3d = this.add(that)

operator fun Vec3d.minus(that: Vec3d): Vec3d = this.subtract(that)

operator fun Vec3d.times(that: Double): Vec3d = this.scale(that)

operator fun Vec3d.div(that: Double): Vec3d = this.scale(1.0 / that)

operator fun Vec3d.unaryMinus(): Vec3d = this.scale(-1.0)

val Vec3d.length: Double get() = lengthVector()
val Vec3d.squaredLength: Double get() = x * x + y * y + z * z

infix fun Vec3d.onLine(that: Vec3d): Vec3d = that * (this dot that) / (that dot that)

infix fun Vec3d.onPlane(that: Vec3d): Vec3d = this - (this onLine that)

infix fun Vec3d.dot(that: Vec3d): Double = this.dotProduct(that)

infix fun Vec3d.cross(that: Vec3d): Vec3d = this.crossProduct(that)
