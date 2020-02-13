package com.example.lab7_home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

public class MyAdapter extends BaseAdapter
{
    //CustomListItem item;

    List<MyData> users;

    public MyAdapter(List<MyData> users) {
        this.users = users;
    }

    @Override
    public int getCount() {
        if(users == null)   return 0;
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CustomListItem item;
        if(view != null){
            item = (CustomListItem) view;
        }
        else{
            item = new CustomListItem(viewGroup.getContext());
        }
        MyData myData = users.get(position);
        item.setTvName(myData.getName());
        item.setTvGroup(myData.getGroup());

        return item;
    }
}
