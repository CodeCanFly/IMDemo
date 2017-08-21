package net.barry.italker.push;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.barry.italker.common.Common;
import net.barry.italker.common.app.Acitvity;

import butterknife.BindView;

public class MainActivity extends Acitvity {
    @BindView(R.id.test_txt)
    TextView mTestText;



    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTestText.setText("Test Hello");
    }
}
