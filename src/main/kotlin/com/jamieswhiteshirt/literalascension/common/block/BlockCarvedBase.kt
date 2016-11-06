package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.carvableblocktypes.CarvableBlockType

abstract class BlockCarvedBase(val feature: CarvableBlockType) : BlockDelegate(feature.modelState)
