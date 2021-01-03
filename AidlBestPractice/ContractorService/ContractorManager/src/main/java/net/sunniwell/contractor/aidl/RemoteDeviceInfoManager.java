package net.sunniwell.contractor.aidl;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

import net.sunniwell.contractor.service.IRemoteDeviceInfoManager;

class RemoteDeviceInfoManager implements IRemoteDeviceInfoManager {

    private static final String TAG = "RemoteDeviceInfoManager";

    private static volatile RemoteDeviceInfoManager singleton = null;

    private RemoteDeviceInfoManager() {
    }

    public static RemoteDeviceInfoManager getInstance() {
        if (singleton == null) {
            synchronized (RemoteDeviceInfoManager.class) {
                if (singleton == null) {
                    singleton = new RemoteDeviceInfoManager();
                }
            }
        }
        return singleton;
    }

    @Override
    public String getMac() {
        IDeviceInfoManager remoteManager = getRemoteManager();
        if (remoteManager != null) {
            try {
                return remoteManager.getMac();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "unknown";
    }

    @Override
    public String getSN() {
        IDeviceInfoManager remoteManager = getRemoteManager();
        if (remoteManager != null) {
            try {
                return remoteManager.getSN();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "unknown";
    }

    private IDeviceInfoManager getRemoteManager() {
        if (RemoteContractorManager.getInstance().getRemoteContractorManager() != null) {
            try {
                return RemoteContractorManager.getInstance().getRemoteContractorManager().getDeviceInfoManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "getRemoteManager: manager is null");
        }
        return null;
    }
}
