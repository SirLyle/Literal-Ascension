package com.jamieswhiteshirt.literalascension.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.features.Stepladders
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.Stepladder

abstract class StepladderDomain(val domainName: String, override val parent: Stepladders) : SubFeatureCollection<Stepladder>(domainName, parent)
