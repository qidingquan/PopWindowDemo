package com.example.qdq.popwindowdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created on 2017/9/11.
 * Author:qdq
 * Description:功能描述
 */
public class PopListAdapter extends BaseAdapter {

    private List<String> dataList;
    private Context context;

    public PopListAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return dataList != null ? dataList.get(i) : 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_pop_list, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_show = view.findViewById(R.id.tv_show);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_show.setText(dataList.get(i));
        return view;
    }

    class ViewHolder {
        TextView tv_show;
    }
}
