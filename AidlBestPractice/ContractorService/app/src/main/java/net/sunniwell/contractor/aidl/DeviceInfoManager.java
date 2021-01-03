package net.sunniwell.contractor.aidl;

import android.os.RemoteException;

import net.sunniwell.contractor.utils.Logger;

public class DeviceInfoManager extends IDeviceInfoManager.Stub {

    private static volatile DeviceInfoManager singleton = null;

    private DeviceInfoManager() {
    }

    public static DeviceInfoManager getInstance() {
        if (singleton == null) {
            synchronized (RemoteDeviceInfoManager.class) {
                if (singleton == null) {
                    singleton = new DeviceInfoManager();
                }
            }
        }
        return singleton;
    }


    @Override
    public String getMac() throws RemoteException {
        Logger.d("getMac");
        return "getMac111111";
    }

    @Override
    public String getSN() throws RemoteException {
        Logger.d("getSN");
        return "getSN2222222";
    }
}
