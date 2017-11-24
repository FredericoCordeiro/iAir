package com.taes.iair.iair;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fredy on 20/11/2017.
 */

public class AdapterCidades extends ArrayAdapter<String> {


    public AdapterCidades(Context context, ArrayList<String> cidades) {
        super(context, 0, cidades);
    }
    int selectedIndex = -1;

    public void setSelectedIndex(int index){
        selectedIndex = index;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String cidade = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview_favorito, parent, false);

        }
        CheckedTextView name = (CheckedTextView) convertView.findViewById(R.id.checkedTextView);
        name.setText(cidade);

        if(selectedIndex == position){
            name.setCheckMarkDrawable(android.R.drawable.btn_star_big_on);
            name.setChecked(true);
        }
        else{
            name.setCheckMarkDrawable(android.R.drawable.btn_star_big_off);
            name.setChecked(false);
        }

        return convertView;
    }




}
