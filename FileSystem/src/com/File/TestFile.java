package com.File;
import javax.swing.*;
public class TestFile {

    public static void main(String[] args) {
        boolean done = false;
        BasicFile f;


        while (!done){

            int i = GetData.getInt("Enter option\n1. OpenFile\n2. ...\n4. Quit");

            try{

                switch (i){
                    case 1:
                        f = new BasicFile();
                        int j = GetData.getInt("1.Copy the file\n2. Get Attributes\n.7 Exit");
                        switch (j){
                            case 1:
                                f.copy();
                                break;
                            case 2:
                               JOptionPane.showMessageDialog(null,f.getAbsolutePath());
                               break;
                            case 7:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Sorry that option is not defined, try a different one","Error",JOptionPane.ERROR_MESSAGE);
                        }
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
