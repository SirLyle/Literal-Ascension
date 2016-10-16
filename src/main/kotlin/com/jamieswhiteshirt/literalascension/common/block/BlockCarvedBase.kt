package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.common.carvedblock.CarvedBlock

abstract class BlockCarvedBase(val type: CarvedBlock) : BlockDelegate(type.modelState, type.material.hardness)
