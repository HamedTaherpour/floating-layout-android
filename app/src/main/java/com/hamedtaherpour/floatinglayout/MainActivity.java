package com.hamedtaherpour.floatinglayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FloatingLayout.CallBack {

    private FloatingLayout floatingLayout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // ask for setting
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 25);
            }
        }

        floatingLayout = new FloatingLayout(this, R.layout.floating_layout, new FLGravity(Gravity.LEFT | Gravity.CENTER, 50, 0), this);
        button = findViewById(R.id.btn_run);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!floatingLayout.isShow())
                    floatingLayout.create();
            }
        });
    }

    @Override
    public void onClickListener(int resource) {
        if (resource == R.id.btn_close)
            floatingLayout.close();
    }

    @Override
    public void onCreateListener(View view) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCloseListener() {
        Toast.makeText(this, "Good bye", Toast.LENGTH_SHORT).show();
    }
}
