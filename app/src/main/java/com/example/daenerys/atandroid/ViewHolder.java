package com.example.daenerys.atandroid;

import android.view.View;
import android.widget.TextView;

/**
 * Created by daenerys on 12/17/17.
 */

public class ViewHolder {
    protected TextView descricao;

    public ViewHolder(View view) {
        descricao = (TextView) view.findViewById(R.id.textViewCard);
    }
}
