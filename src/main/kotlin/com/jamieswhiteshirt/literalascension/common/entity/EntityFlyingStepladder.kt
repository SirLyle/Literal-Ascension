package com.jamieswhiteshirt.literalascension.common.entity

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
import net.minecraft.world.World
import org.lwjgl.util.vector.Matrix4f
import org.lwjgl.util.vector.Vector3f
import org.lwjgl.util.vector.Vector4f

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

        val normalMatrix = Matrix4f()
                .rotate(-Math.toRadians(rotationYaw.toDouble()).toFloat(), Vector3f(0.0F, 1.0F, 0.0F))
                .rotate(Math.toRadians(rotationPitch.toDouble()).toFloat(), Vector3f(1.0F, 0.0F, 0.0F))
                .rotate(Math.toRadians(rotationYaw.toDouble()).toFloat(), Vector3f(0.0F, 1.0F, 0.0F))

        val acceleration = Vector4f().apply {
            Matrix4f.transform(normalMatrix, Vector4f(0.0F, 0.08F, 0.0F, 1.0F), this)
        }

        setVelocity(
                (motionX + acceleration.x) * 0.98,
                (motionY + acceleration.y - 0.05) * 0.98,
                (motionZ + acceleration.z) * 0.98
        )

        move(MoverType.SELF, motionX, motionY, motionZ)

        playSound(SoundEvents.ENTITY_FIREWORK_LAUNCH, 0.75F, 0.5F + rand.nextFloat() * 0.1F)

        if (world.isRemote) {
            val matrix = Matrix4f.mul(
                    Matrix4f.mul(
                            Matrix4f().translate(Vector3f(0.0F, 1.5F, 0.0F)),
                            normalMatrix, null
                    ),
                    Matrix4f().translate(Vector3f(0.0F, -1.5F, 0.0F)),
                    null
            )
            for (x in 0..1) {
                for (z in 0..1) {
                    val pos = Vector4f().apply {
                        Matrix4f.transform(matrix, Vector4f(x - 0.5F, 0.0F, z - 0.5F, 1.0F), this)
                    }
                    for (i in 0..1) {
                        val motion = Vector4f().apply {
                            Matrix4f.transform(normalMatrix, Vector4f(
                                    (rand.nextFloat() - rand.nextFloat()) * 0.1F,
                                    rand.nextFloat() - rand.nextFloat() - 2.0F,
                                    (rand.nextFloat() - rand.nextFloat()) * 0.1F,
                                    1.0F
                            ), this)
                        }

                        world.spawnParticle(EnumParticleTypes.FLAME,
                                posX + pos.x, posY + pos.y, posZ + pos.z,
                                motionX + motion.x, motionY + motion.y, motionZ + motion.z)
                    }
                }
            }
        }

        if (!world.isRemote) {
            if (isCollided || ticksExisted > 80) {
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