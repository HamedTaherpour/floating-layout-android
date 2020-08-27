package io.hamed.floatinglayoutandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.hamed.floatinglayout.callback.FloatingCallBack;
import io.hamed.floatinglayout.FloatingLayout;

public class MainActivity extends AppCompatActivity {

    private FloatingLayout floatingLayout;
    private FloatingCallBack floatingCallBack = new FloatingCallBack() {
        @Override
        public void onCreateListener(View view) {
            Button btn = view.findViewById(R.id.btn_close);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floatingLayout.close();
                }
            });
        }

        @Override
        public void onCloseListener() {
            Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open:
                if (!isNeedPermission())
                    showFloating();
                break;
            case R.id.btn_permission:
                if (isNeedPermission())
                    requestPermission();
                break;
        }
    }

    private boolean isNeedPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this);
    }

    private void requestPermission() {
        Intent intent = new Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName())
        );
        startActivityForResult(intent, 25);
    }

    private void showFloating() {
        floatingLayout = new FloatingLayout(this, R.layout.sample_layout, floatingCallBack);
        floatingLayout.create();
    }
}