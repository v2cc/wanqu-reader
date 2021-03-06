package com.steve.wanqureader;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orm.SugarContext;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by steve on 3/28/16.
 */
public class WanquApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        mContext = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext());
        Fresco.initialize(this);
        SugarContext.init(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        WanquApplication application = (WanquApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public static Context getContext() {
        return mContext;
    }
}
