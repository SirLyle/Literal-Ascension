package com.jamieswhiteshirt.literalascension.common.entity

import com.jamieswhiteshirt.literalascension.util.*
import net.minecraft.entity.Entity
import net.minecraft.entity.MoverType
import net.minecraft.entity.item.EntityFireworkRocket
import net.minecraft.init.Items
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class EntityFlyingStepladder(world: World, pos: BlockPos, facing: EnumFacing) : Entity(world) {
    companion object {
        private val FACING = EntityDataManager.createKey(EntityFlyingStepladder::class.java, DataSerializers.FACING)
        private val TRAJECTORY_FALLOFF = EntityDataManager.createKey(EntityFlyingStepladder::class.java, DataSerializers.FLOAT)
    }

    constructor(world: World): this(world, BlockPos.ORIGIN, EnumFacing.NORTH)

    var facing: EnumFacing
        get() = dataManager.get(FACING)
        set(value) {
            dataManager.set(FACING, value)
        }

    var trajectoryFalloff: Float
        get() = dataManager.get(TRAJECTORY_FALLOFF)
        set(value) {
            dataManager.set(TRAJECTORY_FALLOFF, value)
        }

    init {
        height = 3.0F
        setSize(0.98F, 0.98F)
        setPosition(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5)
        setRotation(rand.nextFloat() * 360.0F, 0.0F)
        this.facing = facing
        this.trajectoryFalloff = rand.nextFloat() * rand.nextFloat() * 4.0F
        motionX = 0.0
        motionY = 0.0
        motionZ = 0.0
        prevPosX = posX
        prevPosY = posY
        prevPosZ = posZ
    }

    override fun writeEntityToNBT(compound: NBTTagCompound) {
        compound.setInteger("facing", facing.horizontalIndex)
    }

    override fun readEntityFromNBT(compound: NBTTagCompound) {
        facing = EnumFacing.getHorizontal(compound.getInteger("facing"))
    }

    override fun entityInit() {
        dataManager.register(FACING, EnumFacing.SOUTH)
        dataManager.register(TRAJECTORY_FALLOFF, 0.0F)
    }

    override fun onUpdate() {
        setRotation(rotationYaw, rotationPitch + trajectoryFalloff)

        val normalMatrix =
                Mat3d.rotate(Math.toRadians(-rotationYaw.toDouble()), Vec3d(0.0, 1.0, 0.0)) *
                Mat3d.rotate(Math.toRadians(rotationPitch.toDouble()), Vec3d(1.0, 0.0, 0.0)) *
                Mat3d.rotate(Math.toRadians(rotationYaw.toDouble()), Vec3d(0.0, 1.0, 0.0))

        val acceleration = normalMatrix * Vec3d(0.0, 0.08, 0.0) + Vec3d(0.0, -0.05, 0.0)
        vel = (vel + acceleration) * 0.98

        move(MoverType.SELF, motionX, motionY, motionZ)

        playSound(SoundEvents.ENTITY_FIREWORK_LAUNCH, 0.75F, 0.5F + rand.nextFloat() * 0.1F)

        if (world.isRemote) {
            val matrix =
                    Mat4d.translate(0.0, 1.5, 0.0) *
                    Mat4d(normalMatrix) *
                    Mat4d.translate(0.0, -1.5, 0.0)
            for (x in 0..1) {
                for (z in 0..1) {
                    val pos = matrix * Vec3d(x - 0.5, 0.0, z - 0.5) + pos
                    for (i in 0..1) {
                        val vel = normalMatrix * Vec3d(
                                (rand.nextDouble() - rand.nextDouble()) * 0.1,
                                rand.nextDouble() - rand.nextDouble() - 2.0,
                                (rand.nextDouble() - rand.nextDouble()) * 0.1
                        ) + vel
                        world.spawnParticle(EnumParticleTypes.FLAME,
                                pos.x, pos.y, pos.z,
                                vel.x, vel.y, vel.z)
                    }
                }
            }
        }

        if (!world.isRemote) {
            if (collided || ticksExisted > 80) {
                setDead()
                world.createExplosion(this, posX, posY + height / 2.0, posZ, 2.0F, true)

                val stack = ItemStack(Items.FIREWORKS).apply {
                    tagCompound = NBTTagCompound().apply { setTag("Fireworks", NBTTagCompound().apply {
                        setByte("Flight", 0)
                        setTag("Explosions", NBTTagList().apply {
                            appendTag(NBTTagCompound().apply {
                                setBoolean("Flicker", true)
                                setByte("Type", 1)
                                setIntArray("Colors", intArrayOf(0xFFA900))
                            })
                        })
                    })}
                }
                for (i in 0..6) {
                    val firework = EntityFireworkRocket(world, posX, posY, posZ, stack)
                    firework.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.25
                    firework.motionY = (rand.nextDouble() - rand.nextDouble()) * 0.25
                    firework.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.25
                    world.spawnEntity(firework)
                }
            }
        }

        super.onUpdate()
    }
}