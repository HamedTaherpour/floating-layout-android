package io.hamed.floatinglayoutandroid

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.hamed.floatinglayout.CallBack
import io.hamed.floatinglayout.FloatingLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CallBack {

    private var floatingLayout: FloatingLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, 25)
            }
        }

        floatingLayout = FloatingLayout(this, R.layout.floating_layout, this)
        btn_run.setOnClickListener { if (!floatingLayout!!.isShow()) floatingLayout!!.create() }
    }

    override fun onClickListener(resourceId: Int) {
        if (resourceId == R.id.btn_close)
            floatingLayout!!.close()
    }

    override fun onCreateListener(view: View?) {
        Toast.makeText(this, "On Create", Toast.LENGTH_SHORT).show();
    }

    override fun onCloseListener() {
        Toast.makeText(this, "On Destroy", Toast.LENGTH_SHORT).show();
    }
}
