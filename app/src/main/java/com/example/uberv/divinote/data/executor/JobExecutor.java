package com.example.uberv.divinote.data.executor;

import android.support.annotation.NonNull;

import com.example.uberv.divinote.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import timber.log.Timber;

public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor mThreadPoolExecutor;

    @Inject
    public JobExecutor() {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 10;

        mThreadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int mCounter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable, "android_" + mCounter++);

            thread.setPriority(Thread.NORM_PRIORITY + 1);

            // A exception handler is created to log the exception from threads
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    Timber.e(thread.getName() + " encountered an error: " + ex.getMessage());
                }
            });

            return new Thread(runnable, "android_" + mCounter++);
        }
    }
}
