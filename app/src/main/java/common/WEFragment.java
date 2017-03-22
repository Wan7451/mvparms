package common;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.mvp.Presenter;
import com.squareup.leakcanary.RefWatcher;

/**
 * WEFragment
 *
 * @author wan7451
 * @data 2017/3/22
 */

public abstract class WEFragment<P extends Presenter> extends BaseFragment<P> {
    protected WEApplication mWeApplication;

    @Override
    protected void ComponentInject() {
        mWeApplication = (WEApplication) mActivity.getApplication();
        setupFragmentComponent(mWeApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = WEApplication.getRefWatcher(getActivity());//使用leakCanary检测fragment的内存泄漏
        if (watcher != null) {
            watcher.watch(this);
        }
        this.mWeApplication = null;
    }
}
