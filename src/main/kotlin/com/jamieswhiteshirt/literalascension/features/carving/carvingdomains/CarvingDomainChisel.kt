package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains

import com.jamieswhiteshirt.literalascension.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.CarvableBlockChiselStoneExtra
import net.minecraft.block.Block
import net.minecraftforge.common.config.Configuration

class CarvingDomainChisel(config: Configuration, parent: CarvingDomains) : CarvingDomain("chisel", parent) {
    val BASALTEXTRA    = optionalOn(config, Block.getBlockFromName("chisel:basaltextra")?.let { CarvableBlockChiselStoneExtra(it, "basaltextra", config, this) })
    val LIMESTONEEXTRA = optionalOn(config, Block.getBlockFromName("chisel:limestoneextra")?.let { CarvableBlockChiselStoneExtra(it, "limestoneextra", config, this) })
    val MARBLEEXTRA    = optionalOn(config, Block.getBlockFromName("chisel:marbleextra")?.let { CarvableBlockChiselStoneExtra(it, "marbleextra", config, this) })
}