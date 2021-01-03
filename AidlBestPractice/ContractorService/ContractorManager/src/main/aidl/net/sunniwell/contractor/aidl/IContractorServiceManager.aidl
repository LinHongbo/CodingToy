package net.sunniwell.contractor.aidl;

import net.sunniwell.contractor.aidl.IDeviceInfoManager;
import net.sunniwell.contractor.aidl.ILedControllerManager;

interface IContractorServiceManager {

    /**
     * @return 设备信息管理器，可获取系统信息
     */
    IDeviceInfoManager getDeviceInfoManager();

    /**
     * @return LED 灯管理器
     */
	ILedControllerManager getLedControllerManager();
}
