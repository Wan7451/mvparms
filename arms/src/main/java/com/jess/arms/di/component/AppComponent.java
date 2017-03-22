package com.jess.arms.di.component;

import com.jess.arms.base.BaseApplication;
import com.jess.arms.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App component
 * @author  wan7451
 * @data 2017/3/22
 */
    
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(BaseApplication application);
}
