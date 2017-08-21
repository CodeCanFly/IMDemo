package net.barry.italker.common.widget.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.barry.italker.common.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener,View.OnLongClickListener
{
    private  final List<Data> mDtaaList=new ArrayList();

    /**
     * 创建一个ViewHolder
     * @param parent RecyclerView
     * @param viewType 界面的类型,约定为xml布局的Id
     * @return ViewHolder
     */

    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        //得到layoutinflate 用于初始化为view
        LayoutInflater inflater= android.view.LayoutInflater.from(parent.getContext());
        //把xml id为viewtype的文件初始化为一个 root view
        View root=inflater.inflate(viewType,parent,false);
        ViewHolder<Data> holder=onCreateViewHolder( root,viewType);
        //设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);
        //设置View的Tag为ViewHolder，进行双向绑定
        root.setTag(R.id.tag_recyler_holder,holder);
        //进行界面注解绑定
        holder.unbinder= ButterKnife.bind(holder,root);
        return null;
    }

    public abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);
    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        //得到绑定的数据
        Data data=mDtaaList.get(position);
        //触发Holder的绑定方法
        holder.bind(data);

    }

    /**
     * 绑定数据到一个Holder上
     * @param holder viewHolder
     * @param position 坐标
     */
    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    /**
     * 得到当前集合数据量
     * @return
     */

    @Override
    public int getItemCount() {
        return 0;
    }

    public  static  abstract class ViewHolder<Data> extends  RecyclerView.ViewHolder{
        private Unbinder unbinder;
        private  AdapterCallback<Data> callback;
        protected Data mData;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * 用于绑定数据的触发
         * @param data 绑定的数据
         */
        void bind(Data data)
        {
            this.mData=data;
            onBind(data);
        }

        /**
         * 当触发绑定数据的时候回调，必须复写
         * @param data 绑定的数据
         */
        protected  abstract  void onBind(Data data);

        /**
         * Holder自己对自己对应的Data进行更新操作
         * @param data
         */
        public  void updateData(Data data)
        {
            if(this.callback!=null)
            {
                this.callback.update(data,this);
            }

        }

    }
}
