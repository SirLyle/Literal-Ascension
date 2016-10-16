package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.common.init.EnumCarvedBlocks

abstract class BlockCarvedBase(val type: EnumCarvedBlocks) : BlockDelegate(type.modelState, type.material.hardness)
