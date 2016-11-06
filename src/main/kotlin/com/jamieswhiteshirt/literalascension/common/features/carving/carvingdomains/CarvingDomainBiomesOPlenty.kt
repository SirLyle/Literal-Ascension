package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains

import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.CarvableBlockBiomesOPlentyLog
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvingDomainBiomesOPlenty(config: Configuration, parent: CarvingDomains) : CarvingDomain("biomesoplenty", parent) {
    val LOG_0 = optionalOn(config, Block.getBlockFromName("biomesoplenty:log_0")?.let { CarvableBlockBiomesOPlentyLog(it, "log_0", arrayOf("sacred_oak", "cherry", "umbran", "fir"), config, this) })
    val LOG_1 = optionalOn(config, Block.getBlockFromName("biomesoplenty:log_1")?.let { CarvableBlockBiomesOPlentyLog(it, "log_1", arrayOf("ethereal", "magic", "mangrove", "palm"), config, this) })
    val LOG_2 = optionalOn(config, Block.getBlockFromName("biomesoplenty:log_2")?.let { CarvableBlockBiomesOPlentyLog(it, "log_2", arrayOf("redwood", "willow", "pine", "hellbark"), config, this) })
    val LOG_3 = optionalOn(config, Block.getBlockFromName("biomesoplenty:log_3")?.let { CarvableBlockBiomesOPlentyLog(it, "log_3", arrayOf("jacaranda", "mahogany", "ebony", "eucalyptus"), config, this) })
    val LOG_4 = optionalOn(config, Block.getBlockFromName("biomesoplenty:log_4")?.let { CarvableBlockBiomesOPlentyLog(it, "log_4", arrayOf("giant_flower", "dead"), config, this) })
}