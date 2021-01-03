package net.sunniwell.contractor.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import net.sunniwell.contractor.service.IRemoteContractorManager;
import net.sunniwell.contractor.service.IRemoteDeviceInfoManager;
import net.sunniwell.contractor.service.IRemoteLedControllerManager;

public class RemoteContractorManager implements IRemoteContractorManager {

    private static final String TAG = "ContractorManager";
    private static volatile RemoteContractorManager singleton = null;
    private static final String ACTION_INTERFACE_SERVICE = "net.sunniwell.action.INTERFACE_SERVICE";
    private static final String PKG_INTERFACE_SERVICE = "net.sunniwell.contractor.service";
    private static final String CLASS_INTERFACE_SERVICE = "net.sunniwell.contractor.ContractorService";
    private static final String PERMISSION_INTERFACE_SERVICE = "net.sunniwell.permission.CONTRACTOR";
    private static Context mContext;
    private static IContractorServiceManager mRemoteManager;

    private RemoteContractorManager() {
    }

    public static RemoteContractorManager getInstance() {
        if (singleton == null) {
            synchronized (RemoteContractorManager.class) {
                if (singleton == null) {
                    singleton = new RemoteContractorManager();
                }
            }
        }
        return singleton;
    }

    IContractorServiceManager getRemoteContractorManager() {
        return mRemoteManager;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, final IInitCallback callback) {
        mContext = context.getApplicationContext();
        Intent intent = new Intent(ACTION_INTERFACE_SERVICE);
        intent.setClassName(PKG_INTERFACE_SERVICE, CLASS_INTERFACE_SERVICE);
        mContext.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mRemoteManager = IContractorServiceManager.Stub.asInterface(iBinder);
                if (callback != null) {
                    callback.initSucceed();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mRemoteManager = null;
                if (callback != null) {
                    callback.initFailed();
                }
            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public IRemoteDeviceInfoManager getDeviceInfoManager() {
        return RemoteDeviceInfoManager.getInstance();
    }

    @Override
    public IRemoteLedControllerManager getLedControllerManager() {
        return RemoteLedControllerManager.getInstance();
    }
}
