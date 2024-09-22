package com.example.homework02_andrewobrien;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //SEEKBARS
    SeekBar sb_j_redSeekBar;
    SeekBar sb_j_greenSeekBar;
    SeekBar sb_j_blueSeekBar;

    //TEXTVIEWS
    TextView tv_j_redValue;
    TextView tv_j_greenValue;
    TextView tv_j_blueValue;
    TextView tv_j_hexRepresentation;

    //BUTTON
    Button btn_j_saveColor;

    //LISTVIEW
    ListView lv_j_colorList;

    //CONSTRAINT LAYOUT (BACKGROUND)
    ConstraintLayout main_j_background;

    //REFERENCE TO ADAPTER
    ColorListAdapter adapter;

    //ARRAY LIST
    ArrayList <ColorInfo> listOfColors;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //connecting GUI and Java variables
        //SEEKBARS
        sb_j_redSeekBar        = findViewById(R.id.sb_v_redSeekBar);
        sb_j_greenSeekBar      = findViewById(R.id.sb_v_greenSeekBar);
        sb_j_blueSeekBar       = findViewById(R.id.sb_v_blueSeekBar);

        //TEXTVIEWS
        tv_j_redValue          = findViewById(R.id.tv_v_redValue);
        tv_j_greenValue        = findViewById(R.id.tv_v_greenValue);
        tv_j_blueValue         = findViewById(R.id.tv_v_blueValue);
        tv_j_hexRepresentation = findViewById(R.id.tv_v_hexRepresentation);

        //BUTTON
        btn_j_saveColor        = findViewById(R.id.btn_v_saveColor);

        //LISTVIEW
        lv_j_colorList         = findViewById(R.id.lv_v_colorList);

        //CONSTRAINT LAYOUT
        main_j_background = findViewById(R.id.main_v_background);



        //set max values for the seekbars
        sb_j_redSeekBar.setMax(255);
        sb_j_greenSeekBar.setMax(255);
        sb_j_blueSeekBar.setMax(255);

        //changes seekbar values as the user scrolls
        seekbarChangeEvent();
        updateHexColor();

        listOfColors = new ArrayList<ColorInfo>();
        //testing
        main_j_background.setBackgroundColor(Color.parseColor(tv_j_hexRepresentation.getText().toString()));

        saveColorButtonClickEvent();
        fillListView();
    }

    private void seekbarChangeEvent()
    {
        sb_j_redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                //change the textView for red to display the value of the seekbar
                tv_j_redValue.setText(i + "");
                updateHexColor();
                //change background
                main_j_background.setBackgroundColor(Color.parseColor(tv_j_hexRepresentation.getText().toString()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_j_greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_greenValue.setText(i + "");
                updateHexColor();
                //change background
                main_j_background.setBackgroundColor(Color.parseColor(tv_j_hexRepresentation.getText().toString()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_j_blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_blueValue.setText(i + "");
                updateHexColor();
                //change background
                main_j_background.setBackgroundColor(Color.parseColor(tv_j_hexRepresentation.getText().toString()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void saveColorButtonClickEvent()
    {
        btn_j_saveColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("TESTING: BTN CLICK", "You have reached the inside of saveColorButtonCLickEvent");
                //on button click, do this
                addColorToList();
            }
        });
    }

    private void updateHexColor()
    {
        //get the red, green, and blue values from their textViews
        int red = Integer.valueOf(tv_j_redValue.getText().toString());
        int green = Integer.valueOf((tv_j_greenValue.getText().toString()));
        int blue = Integer.valueOf(tv_j_blueValue.getText().toString());
        String finalHexColor;

        //convert each into hex
        String redHex = Integer.toHexString(red);
        String greenHex = Integer.toHexString(green);
        String blueHex = Integer.toHexString(blue);

        //add a zero to the front if necessary
        if (redHex.length() == 1)
        {
            redHex = "0" + redHex;
        }
        if (greenHex.length() == 1)
        {
            greenHex = "0" + greenHex;
        }
        if (blueHex.length() == 1)
        {
            blueHex = "0" + blueHex;
        }

        //create the final hex color representation
        finalHexColor = "#" + redHex + greenHex + blueHex;

        //display the hex color representaiton
        tv_j_hexRepresentation.setText(finalHexColor.toUpperCase());
    }

    private void addColorToList()
    {
        Log.d("TESTING 2: ADD COLOR TO LIST", "You are inside the function addColorToList()");

        //create a new ColorInfo object
        ColorInfo colorToAdd = new ColorInfo();
        //fill data into the new ColorInfo object
        //get the information for the TextViews

        colorToAdd.setRedValue(tv_j_redValue.getText().toString());
        colorToAdd.setGreenValue(tv_j_greenValue.getText().toString());
        colorToAdd.setBlueValue(tv_j_blueValue.getText().toString());
        colorToAdd.setHexValue(tv_j_hexRepresentation.getText().toString());

        //add to list
        listOfColors.add(colorToAdd);
        //notify adapter
        adapter.notifyDataSetChanged();

        //reset the bars
        sb_j_redSeekBar.setProgress(255);
        sb_j_greenSeekBar.setProgress(255);
        sb_j_blueSeekBar.setProgress(255);

    }

    private void fillListView()
    {
        adapter = new ColorListAdapter(this, listOfColors);
        //set the listview adapter
        lv_j_colorList.setAdapter(adapter);
    }

}