package me.jessyan.rxerrorhandler.handler;

import android.content.Context;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErroListener;

/**
 * 错误处理工厂类
 * @author  wan7451
 * @data 2017/3/22
 */

public class ErrorHandlerFactory {
    public final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private ResponseErroListener mResponseErroListener;

    public ErrorHandlerFactory(Context mContext, ResponseErroListener mResponseErroListener) {
        this.mResponseErroListener = mResponseErroListener;
        this.mContext = mContext;
    }

    /**
     * 处理错误
     *
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErroListener.handleResponseError(mContext, (Exception) throwable);
    }
}
