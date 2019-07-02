package com.hamedtaherpour.floatinglayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        floatingLayout = new FloatingLayout(this, R.layout.floating_layout, this);

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
