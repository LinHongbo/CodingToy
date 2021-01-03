package net.sunniwell.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.sunniwell.contractor.aidl.RemoteContractorManager;
import net.sunniwell.contractor.service.IRemoteContractorManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RemoteContractorManager.getInstance().init(this, new IRemoteContractorManager.IInitCallback() {
            @Override
            public void initSucceed() {
                Log.d(TAG, "initSucceed: ");
                Toast.makeText(MainActivity.this, "initSucceed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void initFailed() {
                Log.d(TAG, "initFailed: ");
                Toast.makeText(MainActivity.this, "initFailed: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getMac(View view) {
        String mac = RemoteContractorManager.getInstance().getDeviceInfoManager().getMac();
        Log.d(TAG, "getMac: mac = " + mac);
        Toast.makeText(MainActivity.this, "mac: " + mac, Toast.LENGTH_SHORT).show();
    }

    public void getSN(View view) {
        String sn = RemoteContractorManager.getInstance().getDeviceInfoManager().getSN();
        Log.d(TAG, "getSN: sn = " + sn);
        Toast.makeText(MainActivity.this, "sn: " + sn, Toast.LENGTH_SHORT).show();
    }

    public void setLedColor(View view) {
        int ret = RemoteContractorManager.getInstance().getLedControllerManager().setLedColor(5);
        Log.d(TAG, "setLedColor: ret = " + ret);
        Toast.makeText(MainActivity.this, "setLedColor: " + ret, Toast.LENGTH_SHORT).show();
    }

    public void setLedLevel(View view) {
        int ret = RemoteContractorManager.getInstance().getLedControllerManager().setLedLevel(5);
        Log.d(TAG, "setLedLevel: ret = " + ret);
        Toast.makeText(MainActivity.this, "setLedLevel: " + ret, Toast.LENGTH_SHORT).show();
    }
}
