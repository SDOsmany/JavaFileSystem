package com.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;

public class BasicFile {
        File f;
        private String absolutePath;


        public BasicFile(){
            JFileChooser choose = new JFileChooser(".");
            int status = choose.showOpenDialog(null);

            try{
                if(status != JFileChooser.APPROVE_OPTION)
                    throw new IOException();

                f = choose.getSelectedFile();

                if(!f.exists())
                    throw new FileNotFoundException();

                display(f.getName(),"File has been chosen", JOptionPane.INFORMATION_MESSAGE);
            }catch (FileNotFoundException e){

                display("File not found ...", e.toString(), JOptionPane.WARNING_MESSAGE);

            } catch (IOException e){

                display("Approve option was not selected", e.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
        public void copy(){
            if(f.isFile()) {
                String fileName = f.getName().split("\\.",2)[0]+"Copy";
                String extension = "."+f.getName().split("\\.",2)[1];
                File newFile = new File(fileName+extension);

                try{
                    Files.copy(f.toPath(),newFile.toPath());
                }catch (IOException e){
                    display(e.toString(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                if(newFile.exists()){
                    display("File successfully copied","Copied",JOptionPane.INFORMATION_MESSAGE);
                }
            }else{display("What you selected is not a file","ERROR",JOptionPane.ERROR_MESSAGE);}
        }

        public String getAbsolutePath(){
           this.absolutePath = f.getAbsolutePath();
           return this.absolutePath;
        }

        void display(String msg, String s, int t){
            JOptionPane.showMessageDialog(null, msg, s, t);
        }
    }
