package me.jessyan.rxerrorhandler.handler;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import rx.Subscriber;

/**
 * 处理 Rx 错误的Subscriber
 * @author  wan7451
 * @data 2017/3/22
 */

public abstract class ErrorHandleSubscriber<T> extends Subscriber<T> {
    private ErrorHandlerFactory mHandlerFactory;

    public ErrorHandleSubscriber(RxErrorHandler rxErrorHandler){
        this.mHandlerFactory = rxErrorHandler.getmHandlerFactory();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mHandlerFactory.handleError(e);
    }

}

