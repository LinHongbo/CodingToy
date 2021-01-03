package net.sunniwell.contractor.aidl;

import android.os.RemoteException;

import net.sunniwell.contractor.utils.Logger;

public class LedControllerManager extends ILedControllerManager.Stub {

    private static volatile LedControllerManager singleton = null;

    private LedControllerManager() {
    }

    public static LedControllerManager getInstance() {
        if (singleton == null) {
            synchronized (RemoteLedControllerManager.class) {
                if (singleton == null) {
                    singleton = new LedControllerManager();
                }
            }
        }
        return singleton;
    }

    @Override
    public int setLedColor(int color) throws RemoteException {
        Logger.d("setLedColor color = " + color);
        return 0;
    }

    @Override
    public int setLedLevel(int level) throws RemoteException {
        Logger.d("setLedLevel, level = " + level);
        return 0;
    }
}
