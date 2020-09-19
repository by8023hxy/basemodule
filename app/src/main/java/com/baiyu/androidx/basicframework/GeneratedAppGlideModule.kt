package com.baiyu.androidx.basicframework;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author Baiyu
 * @date :2020/9/13 2:34 PM September
 * @version: 1.0
 */
@GlideModule
public class GeneratedAppGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}