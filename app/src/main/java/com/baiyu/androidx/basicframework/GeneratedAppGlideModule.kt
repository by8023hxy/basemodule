package com.baiyu.androidx.basicframework

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * @author Baiyu
 * @date :2020/9/13 2:34 PM September
 * @version: 1.0
 */
@GlideModule
class GeneratedAppGlideModule : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}