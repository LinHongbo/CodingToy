package net.sunniwell.contractor.service;

import android.content.Context;

public interface IRemoteContractorManager {

    interface IInitCallback {
        void initSucceed();

        void initFailed();
    }

    void init(Context context);

    void init(Context context, IInitCallback callback);


    /**
     * @return 设备信息管理器，可获取系统信息
     */
    IRemoteDeviceInfoManager getDeviceInfoManager();

    /**
     * @return LED 灯管理器
     */
    IRemoteLedControllerManager getLedControllerManager();
}
