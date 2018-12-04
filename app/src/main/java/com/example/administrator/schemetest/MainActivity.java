package com.example.administrator.schemetest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTest;
    private String url;
    private WebView mWbLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTest = findViewById(R.id.btn_test);
        mWbLoad = findViewById(R.id.wb_laod);
        mTest.setOnClickListener(this);
        //
        /**
         * scheme = rong
         * host = com.guinong.up
         * path = /goodsDetail
         * data = 4623   唤起页面需要的参数（你们需要关注的）
         */
        url = "rong://com.guinong.up/ynwlogin?access_app_account=15308006438&access_app_token=524387BBE6BFC2AD60EDE760223659DF";
    }

    @Override
    public void onClick(View v) {
        if (hasAppLication()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            showNormalDialog();
        }
    }

    /**
     * 前往应用市场下载
     */
    private void showNormalDialog() {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("你未安装该贵农往app")
                        .setMessage("是否前往下载安装")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mWbLoad.loadUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.guinong.up");
                                    }
                                }).setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
        normalDialog.show();
    }

    /**
     * 判断app是否安装
     *
     * @return
     */
    private boolean hasAppLication() {
        PackageManager manager = this.getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse(url));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }
}
