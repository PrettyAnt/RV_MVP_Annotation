package com.example.prettyant.mulrecyclerview.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.prettyant.R;
import com.example.prettyant.mulrecyclerview.ItemOnClickListener;

/**
 * Created by chenyu.
 * Created on 上午11:47.
 * Author'github https://github.com/PrettyAnt
 */

public class TextViewHolder extends BaseViewHolder {

    public   TextView tv_item_text;
    public TextViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
        super(itemView,itemOnClickListener);
        tv_item_text = itemView.findViewById(R.id.tv_item_text);
    }


}