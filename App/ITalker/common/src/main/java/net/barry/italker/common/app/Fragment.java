package net.barry.italker.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/18.
 */

public abstract class Fragment extends android.support.v4.app.Fragment{
    private  View mRoot;
    private Unbinder mRootUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        //getArguments() 获取Activity传递的值
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRoot==null) {
            int layId = getContentLayoutId();
            //初始化当前的根布局，但是不在创建时就添加到container里边
            View root = inflater.inflate(layId, container, false);
            initWidget(root);
            mRoot=root;
        }
        else {
            //判断母布局是否为空
            if (mRoot.getParent()!=null)
            {
                //把当前Root从父控件移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }
        return  mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View初始化完成后 创建数据
        initData();
    }
    /**
     *初始化相关参数
     */
    protected  void initArgs(Bundle bundle)
    {

    }


    /**
     *  得到当前界面的资源文件ID
     *  @return  资源文件ID
     */
    protected  abstract  int getContentLayoutId();
    /**
     * 初始化控件
     */
    protected  void initWidget(View root){
         mRootUnBinder= ButterKnife.bind(this,root);

    }
    /**
     * 初始化数据
     */
    protected void initData(){

    }

    /**
     * 返回按键触发时调用
     * @return 返回True 代表我已处理返回逻辑,Activity 不用自己finish
     * 返回 false代表我没有处理逻辑，Activity 自己走自己的逻辑
     */
    public  boolean onBackPressed()
    {
        return false;
    }

}
