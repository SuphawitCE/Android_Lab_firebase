package com.example.lab7_home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RecyclerViewHolderTest extends RecyclerView.ViewHolder {

    TextView textViewTitle;
    TextView textViewComment;

    public RecyclerViewHolderTest(@NonNull View itemView)
    {
        super(itemView);

        textViewComment = (TextView) itemView.findViewById(R.id.text_msg);
        textViewTitle = (TextView) itemView.findViewById(R.id.text_title);
    }

}
