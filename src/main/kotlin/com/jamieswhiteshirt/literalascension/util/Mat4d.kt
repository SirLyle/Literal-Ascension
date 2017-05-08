package com.jamieswhiteshirt.literalascension.util

import net.minecraft.util.math.Vec3d

class Mat4d(
        val m00: Double, val m01: Double, val m02: Double, val m03: Double,
        val m10: Double, val m11: Double, val m12: Double, val m13: Double,
        val m20: Double, val m21: Double, val m22: Double, val m23: Double,
        val m30: Double, val m31: Double, val m32: Double, val m33: Double
) {
    companion object {
        fun rotate(x: Double, y: Double, z: Double, degs: Double): Mat4d {
            val rads = Math.toRadians(degs)
            val c = Math.cos(rads)
            val s = Math.sin(rads)
            return Mat4d(
                    x + (y + z) * c,   -z * s, y * s,                  0.0,
                              z * s, y + (x + z) * c,          -x * s, 0.0,
                             -y * s,           x * s, z + (x + y) * c, 0.0,
                                0.0,             0.0,             0.0, 1.0
            )
        }

        fun scale(x: Double, y: Double, z: Double): Mat4d {
            return Mat4d(
                      x, 0.0, 0.0, 0.0,
                    0.0,   y, 0.0, 0.0,
                    0.0, 0.0,   z, 0.0,
                    0.0, 0.0, 0.0, 1.0
            )
        }

        fun translate(x: Double, y: Double, z: Double): Mat4d {
            return Mat4d(
                    1.0, 0.0, 0.0,   x,
                    0.0, 1.0, 0.0,   y,
                    0.0, 0.0, 1.0,   z,
                    0.0, 0.0, 0.0, 1.0
            )
        }
    }

    constructor(other: Mat3d) : this(
            other.m00, other.m01, other.m02, 0.0,
            other.m10, other.m11, other.m12, 0.0,
            other.m20, other.m21, other.m22, 0.0,
                  0.0,       0.0,       0.0, 1.0
    )

    operator fun times(that: Mat4d): Mat4d {
        return Mat4d(
                this.m00 * that.m00 + this.m01 * that.m10 + this.m02 * that.m20 + this.m03 * that.m30,
                this.m00 * that.m01 + this.m01 * that.m11 + this.m02 * that.m21 + this.m03 * that.m31,
                this.m00 * that.m02 + this.m01 * that.m12 + this.m02 * that.m22 + this.m03 * that.m32,
                this.m00 * that.m03 + this.m01 * that.m13 + this.m02 * that.m23 + this.m03 * that.m33,
                this.m10 * that.m00 + this.m11 * that.m10 + this.m12 * that.m20 + this.m13 * that.m30,
                this.m10 * that.m01 + this.m11 * that.m11 + this.m12 * that.m21 + this.m13 * that.m31,
                this.m10 * that.m02 + this.m11 * that.m12 + this.m12 * that.m22 + this.m13 * that.m32,
                this.m10 * that.m03 + this.m11 * that.m13 + this.m12 * that.m23 + this.m13 * that.m33,
                this.m20 * that.m00 + this.m21 * that.m10 + this.m22 * that.m20 + this.m23 * that.m30,
                this.m20 * that.m01 + this.m21 * that.m11 + this.m22 * that.m21 + this.m23 * that.m31,
                this.m20 * that.m02 + this.m21 * that.m12 + this.m22 * that.m22 + this.m23 * that.m32,
                this.m20 * that.m03 + this.m21 * that.m13 + this.m22 * that.m23 + this.m23 * that.m33,
                this.m30 * that.m00 + this.m31 * that.m10 + this.m32 * that.m20 + this.m33 * that.m30,
                this.m30 * that.m01 + this.m31 * that.m11 + this.m32 * that.m21 + this.m33 * that.m31,
                this.m30 * that.m02 + this.m31 * that.m12 + this.m32 * that.m22 + this.m33 * that.m32,
                this.m30 * that.m03 + this.m31 * that.m13 + this.m32 * that.m23 + this.m33 * that.m33
        )
    }

    operator fun times(that: Vec3d): Vec3d {
        return Vec3d(
                this.m00 * that.xCoord + this.m01 * that.yCoord + this.m02 * that.zCoord + this.m03,
                this.m10 * that.xCoord + this.m11 * that.yCoord + this.m12 * that.zCoord + this.m13,
                this.m20 * that.xCoord + this.m21 * that.yCoord + this.m22 * that.zCoord + this.m23
        )
    }
}