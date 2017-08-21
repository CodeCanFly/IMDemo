package net.barry.italker.common.widget.recycler;

import android.provider.ContactsContract;

/**
 * Created by Administrator on 2017/8/21.
 */

public interface AdapterCallback<Data> {
    void update(Data data , RecyclerAdapter.ViewHolder<Data>holder);
}
