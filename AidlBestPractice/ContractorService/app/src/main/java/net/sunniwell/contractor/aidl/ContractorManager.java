package net.sunniwell.contractor.aidl;

import android.os.RemoteException;

import net.sunniwell.contractor.utils.Logger;

public class ContractorManager extends IContractorServiceManager.Stub {

    private static volatile ContractorManager singleton = null;

    private ContractorManager() {
    }

    public static ContractorManager getInstance() {
        if (singleton == null) {
            synchronized (RemoteContractorManager.class) {
                if (singleton == null) {
                    singleton = new ContractorManager();
                }
            }
        }
        return singleton;
    }


    @Override
    public IDeviceInfoManager getDeviceInfoManager() throws RemoteException {
        Logger.d("getDeviceInfoManager");
        return DeviceInfoManager.getInstance();
    }

    @Override
    public ILedControllerManager getLedControllerManager() throws RemoteException {
        Logger.d("getLedControllerManager");
        return LedControllerManager.getInstance();
    }
}
