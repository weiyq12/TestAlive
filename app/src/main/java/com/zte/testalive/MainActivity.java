package com.zte.testalive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zte.testalive.service.MyNotificationListenerService;

public class MainActivity extends Activity implements View.OnClickListener
{
    private Button btn_start_notification_service;
    private Button btn_stop_notification_service;

    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化组件
        initWidget();
        // 初始化数据
        initData();
    }

    private void initWidget()
    {
        btn_start_notification_service = (Button) findViewById(R.id.btn_start_notification_service);
        btn_stop_notification_service = (Button) findViewById(R.id.btn_stop_notification_service);

        btn_start_notification_service.setOnClickListener(this);
        btn_stop_notification_service.setOnClickListener(this);
    }

    private void initData()
    {

    }

    /**
     * 检查通知使用权
     */
    public static boolean checkNotificationPermission(Context context) {
        String pkg = context.getPackageName();
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        boolean enabled = flat != null && flat.contains(pkg);
        return enabled;
    }

    /**
     * 系统设置通知权限的界面
     * @param context
     */
    public void goNLPermission(Context context)
    {
        try
        {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            //context.startActivity(intent);
            startActivityForResult(intent, 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.i(tag, "onActivityResult requestCode=" + requestCode + ", resultCode=" + resultCode + ", data=" + data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(this, "onclick id=" + view.getId(), Toast.LENGTH_SHORT).show();

        switch (view.getId())
        {
            case R.id.btn_start_notification_service:
            {
                startNotifService();
                break;
            }
            case R.id.btn_stop_notification_service:
            {
                stopNotfiService();
                break;
            }
        }
    }

    private void startNotifService()
    {
        Toast.makeText(this, "启动NotificationListenerService", Toast.LENGTH_SHORT).show();

        boolean isPermitOk = checkNotificationPermission(this);
        if (!isPermitOk)
        {
            Log.i(tag, "permit is not OK, pop up permission activity.");
            goNLPermission(this);
            return;
        }

        startService(new Intent(this, MyNotificationListenerService.class));
        Log.i(tag, "启动NotificationListenerService");
    }


    private void stopNotfiService()
    {
        Toast.makeText(this, "停止NotificationListenerService", Toast.LENGTH_SHORT).show();
        stopService(new Intent(this, MyNotificationListenerService.class));
        Log.i(tag, "停止NotificationListenerService");
    }
}
