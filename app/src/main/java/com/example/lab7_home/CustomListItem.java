package com.example.lab7_home;


import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;
import android.content.Context;

//import com.google.firebase.database.core.Context;

import static java.security.AccessController.getContext;

//import static java.security.AccessController.getContext;

public class CustomListItem extends CustomViewGroup{

    private TextView tvName;
    private TextView tvGroup;
    

    public CustomListItem(Context context)
    {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomListItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs,0,0);
    }

    public CustomListItem(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs,defStyleAttr,0);
    }

    @TargetApi(21)
    public CustomListItem(Context context, AttributeSet attrs,int defStyleAttr,
                          int defStyleRes)
    {
        super(context,attrs,defStyleAttr,defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs,defStyleAttr,defStyleRes);
    }

    private void initWithAttrs(AttributeSet attrs, int i, int i1) {

    }

    private void initInstances() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvGroup = (TextView) findViewById(R.id.tvGroup);
    }

    private void initInflate() {
        inflate(getContext(),R.layout.custom_list_item,this);
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
    }

    public void setTvName(String text)
    {
        tvName.setText(text);
    }
    public void setTvGroup(String text){
        tvGroup.setText(text);
    }
}
