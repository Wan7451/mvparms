package me.jessyan.rxerrorhandler.handler.listener;

import android.content.Context;

/**
 * 错误处理接口
 *
 * @author wan7451
 * @data 2017/3/22
 */

public interface ResponseErroListener {

    void handleResponseError(Context context, Exception e);

    ResponseErroListener EMPTY = new ResponseErroListener() {
        @Override
        public void handleResponseError(Context context, Exception e) {

        }
    };
}
