package com.example.homework02_andrewobrien;

public class ColorInfo
{
    private String redValue;
    private String greenValue;
    private String blueValue;
    private String hexValue;

    public ColorInfo()
    {

    }

    public ColorInfo(String r, String g, String b, String h)
    {
        redValue = r;
        greenValue = g;
        blueValue = b;
        hexValue = h;

    }

    //====================================================
    //GETTERS AND SETTERS
    //====================================================


    public String getRedValue()
    {
        return redValue;
    }

    public void setRedValue(String redValue)
    {
        this.redValue = redValue;
    }

    public String getGreenValue()
    {
        return greenValue;
    }

    public void setGreenValue(String greenValue)
    {
        this.greenValue = greenValue;
    }

    public String getBlueValue()
    {
        return blueValue;
    }

    public void setBlueValue(String blueValue)
    {
        this.blueValue = blueValue;
    }

    public String getHexValue()
    {
        return hexValue;
    }

    public void setHexValue(String hexValue)
    {
        this.hexValue = hexValue;
    }
}
