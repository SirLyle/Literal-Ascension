package com.jamieswhiteshirt.literalascension.util

import net.minecraft.util.math.Vec3d

class Mat3d(
        val m00: Double, val m01: Double, val m02: Double,
        val m10: Double, val m11: Double, val m12: Double,
        val m20: Double, val m21: Double, val m22: Double
) {
    companion object {
        fun rotate(rads: Double, x: Double, y: Double, z: Double): Mat3d {
            val c = Math.cos(rads)
            val ci = 1 - c
            val s = Math.sin(rads)
            return Mat3d(
                    c + x * x * ci, x * y * ci - z * s, x * z * ci + y * s,
                    y * x * ci + z * s, c + y * y * ci, y * z * ci - x * s,
                    z * x * ci - y * s, z * y * ci + x * s, c + z * z * ci
            )
        }

        fun rotate(rads: Double, axis: Vec3d): Mat3d = rotate(rads, axis.x, axis.y, axis.z)

        fun scale(x: Double, y: Double, z: Double): Mat3d {
            return Mat3d(
                    x, 0.0, 0.0,
                    0.0, y, 0.0,
                    0.0, 0.0, z
            )
        }
    }

    operator fun times(that: Mat3d): Mat3d {
        return Mat3d(
                this.m00 * that.m00 + this.m01 * that.m10 + this.m02 * that.m20,
                this.m00 * that.m01 + this.m01 * that.m11 + this.m02 * that.m21,
                this.m00 * that.m02 + this.m01 * that.m12 + this.m02 * that.m22,
                this.m10 * that.m00 + this.m11 * that.m10 + this.m12 * that.m20,
                this.m10 * that.m01 + this.m11 * that.m11 + this.m12 * that.m21,
                this.m10 * that.m02 + this.m11 * that.m12 + this.m12 * that.m22,
                this.m20 * that.m00 + this.m21 * that.m10 + this.m22 * that.m20,
                this.m20 * that.m01 + this.m21 * that.m11 + this.m22 * that.m21,
                this.m20 * that.m02 + this.m21 * that.m12 + this.m22 * that.m22
        )
    }

    operator fun times(that: Vec3d): Vec3d {
        return Vec3d(
                this.m00 * that.x + this.m01 * that.y + this.m02 * that.z,
                this.m10 * that.x + this.m11 * that.y + this.m12 * that.z,
                this.m20 * that.x + this.m21 * that.y + this.m22 * that.z
        )
    }
}