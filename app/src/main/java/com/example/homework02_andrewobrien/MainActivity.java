package com.example.homework02_andrewobrien;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    TextView tv_j_redTitle;
    TextView tv_j_greenTitle;
    TextView tv_j_blueTitle;
    TextView tv_j_hexRepresentationTitle;

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

        tv_j_redTitle = findViewById(R.id.tv_v_redTitle);
        tv_j_greenTitle = findViewById(R.id.tv_v_greenTitle);
        tv_j_blueTitle = findViewById(R.id.tv_v_blueTitle);
        tv_j_hexRepresentationTitle = findViewById(R.id.tv_v_hexRepresentationTitle);

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
        listViewEventListener();
        changeTextColor();
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
                changeTextColor();
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
                changeTextColor();
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
                changeTextColor();
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

    private void listViewEventListener()
    {
        lv_j_colorList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Get the cell that was clicked
                ColorInfo clickedColor = listOfColors.get(i);
                //set the main GUI to the color
                main_j_background.setBackgroundColor(Color.parseColor(clickedColor.getHexValue()));
                sb_j_redSeekBar.setProgress(Integer.parseInt(clickedColor.getRedValue()));
                sb_j_greenSeekBar.setProgress(Integer.parseInt(clickedColor.getGreenValue()));
                sb_j_blueSeekBar.setProgress(Integer.parseInt(clickedColor.getBlueValue()));
                tv_j_hexRepresentation.setText(clickedColor.getHexValue());
            }
        });
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

    private void changeTextColor()
    {
        int colorIntForm = Color.parseColor(tv_j_hexRepresentation.getText().toString());
        //testing
        Log.d("COLOR INT FORM:", String.valueOf(colorIntForm));

        int red = Color.red(colorIntForm);
        int green = Color.green(colorIntForm);
        int blue = Color.blue(colorIntForm);

        //calculation I found on stackoverflow to calculate brightness
        double brightness = (red * 0.299 + green * 0.587 + blue * 0.114);

        //128 is the halfway point between dark and light
        if (brightness < 128)
        {
            //set all text to white
            tv_j_redValue.setTextColor(Color.WHITE);
            tv_j_greenValue.setTextColor(Color.WHITE);
            tv_j_blueValue.setTextColor(Color.WHITE);
            tv_j_hexRepresentation.setTextColor(Color.WHITE);
            tv_j_redTitle.setTextColor(Color.WHITE);
            tv_j_greenTitle.setTextColor(Color.WHITE);
            tv_j_blueTitle.setTextColor(Color.WHITE);
            tv_j_hexRepresentationTitle.setTextColor(Color.WHITE);
        }
        else
        {
            tv_j_redValue.setTextColor(Color.BLACK);
            tv_j_greenValue.setTextColor(Color.BLACK);
            tv_j_blueValue.setTextColor(Color.BLACK);
            tv_j_hexRepresentation.setTextColor(Color.BLACK);
            tv_j_redTitle.setTextColor(Color.BLACK);
            tv_j_greenTitle.setTextColor(Color.BLACK);
            tv_j_blueTitle.setTextColor(Color.BLACK);
            tv_j_hexRepresentationTitle.setTextColor(Color.BLACK);
        }
    }
}