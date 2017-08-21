package net.barry.italker.common.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/18.
 */

public abstract class Acitvity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化之前调用的初始化窗口
        initWindows();
        Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
        //判断是否初始化参数是否成功
        //getIntent().getExtras() 获取上一个activity传递的值
        if(initArgs(getIntent().getExtras()))
        {
            int layoutId=getContentLayoutId();
            //得到界面ID，设置到Activity
            setContentView(layoutId);
            Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
            initWidget();
            initData();
        }
        else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected  void initWindows()
    {

    }

    /**
     *初始化相关参数
     * @param bundle 参数Bundle
     * @return  如果参数正确返回true ，错误返回flase
     */
    protected  boolean  initArgs(Bundle bundle)
    {
        return  true;
    }
    /**
     *  得到当前界面的资源文件ID
     *  @return  资源文件ID
     */
    protected  abstract  int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected  void initWidget(){
        ButterKnife.bind(this);

    }
    /**
     * 初始化数据
     */

    protected void initData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时，finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //获取当前Activity下的所有fragment
        @SuppressLint("RestrictedApi")
        List<Fragment> fragments=getSupportFragmentManager().getFragments();
        //判断fragments集合是否为空
        if(fragments!=null && fragments.size()>0)
        {
            for(Fragment fragment:fragments) {
                //判断fragment是否来自 net.barry.italker.common.app.Fragment（instanceof 判断对象是否是特定类的一个实例）
                if (fragment instanceof  net.barry.italker.common.app.Fragment)
                {
                    //判断是否拦截了返回按钮
                    if(((net.barry.italker.common.app.Fragment) fragment).onBackPressed())
                    {
                        //如果有直接retuan
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
