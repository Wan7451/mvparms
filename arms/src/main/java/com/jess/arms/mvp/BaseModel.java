package com.jess.arms.mvp;

import com.jess.arms.http.BaseCacheManager;
import com.jess.arms.http.BaseServiceManager;

/**
 * BaseModel
 * @author  wan7451
 * @data 2017/3/22
 */

public class BaseModel<S extends BaseServiceManager, C extends BaseCacheManager> implements Model {
    protected S mServiceManager;//服务管理类,用于网络请求
    protected C mCacheManager;//缓存管理类,用于管理本地或者内存缓存

    public BaseModel(S serviceManager, C cacheManager) {
        this.mServiceManager = serviceManager;
        this.mCacheManager = cacheManager;
    }

    @Override
    public void onDestroy() {
        if (mServiceManager != null) {
            mServiceManager = null;
        }
        if (mCacheManager != null) {
            mCacheManager = null;
        }
    }
}
