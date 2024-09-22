package com.example.homework02_andrewobrien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColors;
    public ColorListAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColors = ls;
    }


    @Override
    public int getCount()
    {
        return listOfColors.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfColors.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        //find the GUI elements in my custom_cell
        TextView redValue   = view.findViewById(R.id.tv_v_cc_redValue);
        TextView greenValue = view.findViewById(R.id.tv_v_cc_greenValue);
        TextView blueValue  = view.findViewById(R.id.tv_v_cc_blueValue);
        TextView hexValue   = view.findViewById(R.id.tv_v_cc_hexValue);

        //get data from this specific color from the colorList
        //I can access different elements based off the i value that is passed to this function
        ColorInfo clr = listOfColors.get(i);

        //set the GUI for custom_cell
        redValue.setText(clr.getRedValue());
        greenValue.setText(clr.getGreenValue());
        blueValue.setText(clr.getGreenValue());
        hexValue.setText(clr.getHexValue());

        return view;
    }



}
