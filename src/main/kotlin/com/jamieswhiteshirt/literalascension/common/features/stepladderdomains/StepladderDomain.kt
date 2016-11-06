package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.Stepladder

abstract class StepladderDomain(val domainName: String, override val parent: Stepladders) : SubFeatureCollection<Stepladder>(domainName, parent)