package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks.CarvedBlock

abstract class BlockCarvedBase(val feature: CarvedBlock) : BlockDelegate(feature.modelState, feature.material.hardness)
