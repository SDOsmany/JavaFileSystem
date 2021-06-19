package com.File;
import javax.swing.*;
public class TestFile {

    public static void main(String[] args) {
        boolean done = false;
        BasicFile f;

        String menu = "Enter option\n1. OpenFile\n2. ...\n4. Quit";
        while (!done){
            String s = JOptionPane.showInputDialog(menu);

            try{
                int i = Integer.parseInt(s);
                switch (i){
                    case 1:
                        f = new BasicFile();
                        break;
                    case 4:
                        done = true;
                        break;
                    default:
                        display("This option is undefined","Error");
                        break;
                }
            }catch (NumberFormatException|NullPointerException e){
                display(e.toString(),"Error");
            }
        }

    }
    static void display(String s, String err){
        JOptionPane.showMessageDialog(null, s,err, JOptionPane.ERROR_MESSAGE);
    }
}
