package net.sunniwell.contractor.service;

public interface IRemoteLedControllerManager {

    /**
     * 设置 LED 灯的颜色
     *
     * @param color 灯的颜色
     * @return 0：设置成功；-1：设置失败；
     */
    int setLedColor(int color);

    /**
     * 设置LED灯模式
     *
     * @param level:灯的级别（0-100）
     * @return 0: 设置成功,-1:设置失败
     **/
    int setLedLevel(int level);
}
