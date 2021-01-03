package net.sunniwell.contractor;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import net.sunniwell.contractor.aidl.ContractorManager;

public class ContractorService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ActivityManager activityManager;
        return ContractorManager.getInstance();
    }
}
