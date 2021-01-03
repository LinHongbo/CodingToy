package net.sunniwell.contractor.aidl;

import android.os.RemoteException;
import android.util.Log;

import net.sunniwell.contractor.service.IRemoteLedControllerManager;

class RemoteLedControllerManager implements IRemoteLedControllerManager {

    private static final String TAG = "RemoteLedControllerMana";
    private static volatile RemoteLedControllerManager singleton = null;

    private RemoteLedControllerManager() {
    }

    public static RemoteLedControllerManager getInstance() {
        if (singleton == null) {
            synchronized (RemoteLedControllerManager.class) {
                if (singleton == null) {
                    singleton = new RemoteLedControllerManager();
                }
            }
        }
        return singleton;
    }

    @Override
    public int setLedColor(int color) {
        ILedControllerManager remoteManager = getRemoteManager();
        if (remoteManager != null) {
            try {
                return remoteManager.setLedColor(color);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public int setLedLevel(int level) {
        ILedControllerManager remoteManager = getRemoteManager();
        if (remoteManager != null) {
            try {
                return remoteManager.setLedLevel(level);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    private ILedControllerManager getRemoteManager() {
        if (RemoteContractorManager.getInstance().getRemoteContractorManager() != null) {
            try {
                return RemoteContractorManager.getInstance().getRemoteContractorManager().getLedControllerManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "getRemoteManager: manager is null");
        }
        return null;
    }
}
