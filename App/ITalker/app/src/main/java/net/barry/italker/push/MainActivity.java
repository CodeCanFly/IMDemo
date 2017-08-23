package net.barry.italker.push;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.barry.italker.common.Common;
import net.barry.italker.common.app.Acitvity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Acitvity implements IView {
    @BindView(R.id.txt_result)
    TextView mResultText;

    @BindView(R.id.edit)
    EditText mInputText;

    private IPresenter  mPresenter;




    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new Presenter(this);
    }

    @OnClick(R.id.btn_submit)
    void onSumbit()
    {
        mPresenter.search();
    }

    @Override
    public String getInputString() {
        return mInputText.getText().toString();
    }

    @Override
    public void setResultString(String string) {
            mResultText.setText(string);
    }
}
