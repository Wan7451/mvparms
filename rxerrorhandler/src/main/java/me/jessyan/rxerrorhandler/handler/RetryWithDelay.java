package me.jessyan.rxerrorhandler.handler;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Rx延迟重试
 * 1类为错误Observable  2类为再次重试的Observable
 *
 * @author wan7451
 * @data 2017/3/22
 */

public class RetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {
    public final String TAG = this.getClass().getSimpleName();
    private final int maxRetries;
    private final int retryDelaySecond;
    private int retryCount;

    /**
     * RetryWithDelay 构造器
     *
     * @param maxRetries       重试次数
     * @param retryDelaySecond 延迟多长时间再次重试
     */
    public RetryWithDelay(int maxRetries, int retryDelaySecond) {
        this.maxRetries = maxRetries;
        this.retryDelaySecond = retryDelaySecond;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (++retryCount <= maxRetries) {
                            // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                            Log.d(TAG, "get error, it will try after " + retryDelaySecond
                                    + " second, retry count " + retryCount);
                            return Observable.timer(retryDelaySecond,
                                    TimeUnit.SECONDS);
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);
                    }
                });
    }
}