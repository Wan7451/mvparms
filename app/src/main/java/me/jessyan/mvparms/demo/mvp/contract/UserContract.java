package me.jessyan.mvparms.demo.mvp.contract;

import com.jess.arms.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import me.jessyan.mvparms.demo.mvp.model.entity.User;
import rx.Observable;

/**
 * 约束类
 * @author  wan7451
 * @data 2017/3/22
 */

public interface UserContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);
        void startLoadMore();
        void endLoadMore();
        //申请权限
        RxPermissions getRxPermissions();
    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends com.jess.arms.mvp.Model {
        Observable<List<User>> getUsers(int lastIdQueried, boolean update);
    }
}
