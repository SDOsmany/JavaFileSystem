package com.File;

import javax.swing.*;

public class GetData {
    /**
     * Method to get the string input from the user
     * @param s string inputted by the user
     * @return  returns the string in a dialog box
     */
    public static String getString(String s){
        return JOptionPane.showInputDialog(s);
    }
    /**
     * Method to get the integer input from the user
     * @param s string inputted by the user
     * @return  returns the integer value from the input
     */
    public static int getInt(String s){
        int temp =0;
        try{
            temp = Integer.parseInt(getString(s));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Your answer is not valid. Please input a number","Error",JOptionPane.ERROR_MESSAGE);
        }
        return temp;
    }

    /**
     * Method to get the double input from the user
     * @param s string inputted by the user
     * @return  returns the double value from the input
     */
    public static double getDouble(String s){
        double temp =0;
        try{
            temp = Double.parseDouble(getString(s));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Your answer is not valid. Please input a number","Error",JOptionPane.ERROR_MESSAGE);
        }
        return temp;
    }
}
