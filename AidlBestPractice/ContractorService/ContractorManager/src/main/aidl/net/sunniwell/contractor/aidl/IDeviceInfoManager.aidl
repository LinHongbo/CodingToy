package net.sunniwell.contractor.aidl;

interface IDeviceInfoManager {

    /**
     * 获取设备 MAC
     *
     * @return 设备 MAC
     */
    String getMac();

    /**
     * 获取设备序列号
     *
     * @return 设备序列号
     */
    String getSN();
}
