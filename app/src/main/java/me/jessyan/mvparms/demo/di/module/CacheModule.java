package me.jessyan.mvparms.demo.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;
import me.jessyan.mvparms.demo.mvp.model.api.cache.CommonCache;

/**
 * 通过CommonCache 缓存对象
 *
 * @author wan7451
 * @data 2017/3/22
 */

@Module
public class CacheModule {

    @Singleton
    @Provides
    CommonCache provideCommonService(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }


}
