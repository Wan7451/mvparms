package com.jess.arms.base;

import android.app.Application;
import android.content.Context;

import com.jess.arms.di.component.DaggerAppComponent;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.di.module.GlobeConfigModule;
import com.jess.arms.di.module.ImageModule;

import javax.inject.Inject;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 本项目由
 * mvp
 * +dagger2
 * +retrofit
 * +rxjava
 * +androideventbus
 * +butterknife组成
 *
 * @author wan7451
 * @data 2017/3/22
 */

public abstract class BaseApplication extends Application {

    static private BaseApplication mApplication;
    private ClientModule mClientModule;   //提供网络操作对象
    private AppModule mAppModule;   //提供Application对象
    private ImageModule mImagerModule;  //提供图片加载对象
    private GlobeConfigModule mGlobeConfigModule;

    @Inject
    protected AppManager mAppManager;  //注解初始化
    @Inject
    protected ActivityLifecycle mActivityLifecycle; //注解初始化

    protected final String TAG = this.getClass().getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.mAppModule = new AppModule(this);//提供application
        DaggerAppComponent
                .builder()
                .appModule(mAppModule)
                .build()
                .inject(this); //provide  Application gson
        this.mImagerModule = new ImageModule();//使用策略模式  图片加载框架默认使用glide
        this.mClientModule = new ClientModule(mAppManager);//用于提供okhttp和retrofit的单例
        this.mGlobeConfigModule = checkNotNull(getGlobeConfigModule(), "lobeConfigModule is required");
        registerActivityLifecycleCallbacks(mActivityLifecycle);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mClientModule != null)
            this.mClientModule = null;
        if (mAppModule != null)
            this.mAppModule = null;
        if (mImagerModule != null)
            this.mImagerModule = null;
        if (mActivityLifecycle != null) {
            unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }
        if (mAppManager != null) {//释放资源
            this.mAppManager.release();
            this.mAppManager = null;
        }
        if (mApplication != null)
            this.mApplication = null;
    }


    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     *
     * @return
     */
    protected abstract GlobeConfigModule getGlobeConfigModule();


    /**
     * 返回创建的ClientModule
     *
     * @return ClientModule
     */
    public ClientModule getClientModule() {
        return mClientModule;
    }

    /**
     * 返回创建的AppModule
     *
     * @return AppModule
     */
    public AppModule getAppModule() {
        return mAppModule;
    }

    /**
     * 返回创建的ImageModule
     *
     * @return ImageModule
     */
    public ImageModule getImageModule() {
        return mImagerModule;
    }

    /**
     * 返回创建的AppManager
     *
     * @return AppManager
     */
    public AppManager getAppManager() {
        return mAppManager;
    }


    /**
     * 返回上下文
     *
     * @return
     */
    public static Context getContext() {
        return mApplication;
    }

}
