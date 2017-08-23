package net.barry.italker.common.widget.recycler;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.barry.italker.common.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener,View.OnLongClickListener,AdapterCallback<Data>
{
    private  final List<Data> mDtaaList;
    private AdapterListener<Data> mListener;

    /**
     * 构造函数模块
     */

    public  RecyclerAdapter( )
    {
        this(null);
    }
    public  RecyclerAdapter(AdapterListener<Data> listener )
    {
        this(new ArrayList<Data>(),listener);
    }


    public  RecyclerAdapter(List<Data> dataList,AdapterListener<Data> listener )
    {
        this.mDtaaList=dataList;
        this.mListener=listener;
    }

    /**
     * 复写默认的布局类型返回
     * @param position 坐标
     * @return 复写后返回的都是XML文件的ID
     */
    public  int getItemViewType(int position)
    {
        return  getItemViewType(position,mDtaaList.get(position));
    }


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
        //设置View的Tag为ViewHolder，进行双向绑定
        root.setTag(R.id.tag_recyler_holder,holder);
        //设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        //进行界面注解绑定
        holder.unbinder= ButterKnife.bind(holder,root);
        //绑定callback
        holder.callback=this;
        return holder;
    }

    /**
     * 得到布局的类型
     * @param position 坐标
     * @param data 数据
     * @return XML文件的ID，用于创建ViewHolder
     */
    @LayoutRes
    protected  abstract int getItemViewType(int position,Data data);
    /**
     * 得到一个新的ViewHolder
     * @param root 根布局
     * @param viewType  布局类型，其实就是xml的ID
     * @return ViewHolder
     */

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
        return mDtaaList.size();
    }

    /**
     * 插入一条数据并通知插入
     * @param data
     */
    public void add(Data data)
    {
        mDtaaList.add(data);
        notifyItemInserted(mDtaaList.size()-1);
    }
    /**
     * 插入一堆数据，并通知这段集合更新
     * @param dataList
     */
    public  void add(Data... dataList)
    {
        if(dataList!=null&&dataList.length>0)
        {
            int startPos=mDtaaList.size()-1;
            Collections.addAll(mDtaaList,dataList);
            notifyItemRangeInserted(startPos,dataList.length);
        }

    }


    /**
     * 插入一堆数据，并通知这段集合更新
     * @param dataList
     */
    public  void add(Collection<Data> dataList)
    {
         if(dataList!=null&&dataList.size()>0)
         {
             int startPos=mDtaaList.size()-1;
             Collections.addAll(dataList);
             notifyItemRangeInserted(startPos,dataList.size());
         }

    }

    /**
     * 替换为一个新的集合，其中包括了清空
     * @param dataList 一个新的集合
     */
    public  void  replace(Collection<Data> dataList)
    {
        mDtaaList.clear();
        if(dataList!=null&&dataList.size()>0)
        return;
        mDtaaList.addAll(dataList);
        notifyDataSetChanged();
    }
    /**
     * 删除操作
     */
    public  void clear()
    {
        mDtaaList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        ViewHolder viewHolder= (ViewHolder) v.getTag(R.id.tag_recyler_holder);
        if(this.mListener!=null)
        {
            //得到ViewHolder当前对应的适配器中的坐标
            int pos=viewHolder.getAdapterPosition();
            //回掉方法
            this.mListener.onItemClick(viewHolder,mDtaaList.get(pos));
        }

    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder viewHolder= (ViewHolder) v.getTag(R.id.tag_recyler_holder);
        if(this.mListener!=null)
        {
            //得到ViewHolder当前对应的适配器中的坐标
            int pos=viewHolder.getAdapterPosition();
            //回掉方法
            this.mListener.onItemLongClick(viewHolder,mDtaaList.get(pos));
        }
        return true;
    }

    /**
     * 设置适配器的监听
     * @param adapterListener
     */
    public  void setListenter(AdapterListener<Data> adapterListener)
    {
        this.mListener=adapterListener;
    }

    /**
     * 自定义监听器
     * @param <Data>泛型
     */
    public interface  AdapterListener<Data>{
        //当Cell点击触发
        void  onItemClick(RecyclerAdapter.ViewHolder holder,Data data);
        //当Cell长按触发
        void  onItemLongClick(RecyclerAdapter.ViewHolder holder,Data data);
    }
    /**
     * 自定义ViewHolder
     * @param <Data>
     */
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
