package com.example.homework02_andrewobrien;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

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

        TextView redTitle = view.findViewById(R.id.tv_v_cc_redTitle);
        TextView greenTitle = view.findViewById(R.id.tv_v_cc_greenTitle);
        TextView blueTitle = view.findViewById(R.id.tv_v_cc_blueTitle);
        TextView hexValueTitle = view.findViewById(R.id.tv_v_cc_hexValueTitle);

        ConstraintLayout background = view.findViewById(R.id.cl_v_cc_background);

        //get data from this specific color from the colorList
        //I can access different elements based off the i value that is passed to this function
        ColorInfo clr = listOfColors.get(i);

        //set the GUI for custom_cell
        redValue.setText(clr.getRedValue());
        greenValue.setText(clr.getGreenValue());
        blueValue.setText(clr.getBlueValue());
        hexValue.setText(clr.getHexValue());

        //===============COLOR CHANGE================
        //change the color of the text if it is too dark or too bright
        String hexString = hexValue.getText().toString();
        int colorIntForm = Color.parseColor(hexString);

        int red = Color.red(colorIntForm);
        int green = Color.green(colorIntForm);
        int blue = Color.blue(colorIntForm);

        //calculation I found on stackoverflow to calculate brightness
        double brightness = (red * 0.299 + green * 0.587 + blue * 0.114);

        if (brightness < 128)
        {
            redValue.setTextColor(Color.WHITE);
            greenValue.setTextColor(Color.WHITE);
            blueValue.setTextColor(Color.WHITE);
            redTitle.setTextColor(Color.WHITE);
            greenTitle.setTextColor(Color.WHITE);
            blueTitle.setTextColor(Color.WHITE);
            hexValueTitle.setTextColor(Color.WHITE);
            hexValue.setTextColor(Color.WHITE);
        }
        else
        {
            redValue.setTextColor(Color.BLACK);
            greenValue.setTextColor(Color.BLACK);
            blueValue.setTextColor(Color.BLACK);
            redTitle.setTextColor(Color.BLACK);
            greenTitle.setTextColor(Color.BLACK);
            blueTitle.setTextColor(Color.BLACK);
            hexValueTitle.setTextColor(Color.BLACK);
            hexValueTitle.setTextColor(Color.BLACK);
        }
        //===============COLOR CHANGE================

        background.setBackgroundColor(Color.parseColor(hexValue.getText().toString()));

        return view;
    }



}
