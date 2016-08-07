package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.common.EnumCarvedBlockType

abstract class BlockCarvedBase(val type: EnumCarvedBlockType) : BlockDelegate(type.modelState, type.material.hardness)
