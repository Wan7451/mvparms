package com.jess.arms.widget.imageloader;

import android.content.Context;

/**
 * 图片加载策略
 * 设计模式中的策略模式
 *
 * @author wan7451
 * @data 2017/3/22
 */

public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context ctx, T config);

    void clear(Context ctx, T config);
}
