package com.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.*;
import java.nio.file.Files;

public class BasicFile {
        File f;
        private String absolutePath;
        private double fileSize;


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

        public double fileSize(){
           this.fileSize = f.length()/1000;
            return fileSize;
        }

        public String getPaths(){
            String result="";
            try {
                File dir = new File(this.f.getAbsolutePath().split(this.f.getName())[0]);
                File[] files = dir.listFiles();
                int i=0;
                while (i<files.length){
                    if(files[i].isFile())
                        result+= "File "+files[i].getName()+" is in "+ files[i].getAbsolutePath()+"\n";
                    else result+= "Directory "+files[i].getName()+" is in "+ files[i].getAbsolutePath()+"\n";
                    i++;
                }
            }catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "You didn't choose a file\n [Error message]: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            return result;
        }

        public String numberOfLines(){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(f.getName()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int lines = 0;
            try {
                while (reader.readLine() != null) lines++;
                reader.close();
            }catch (IOException e){

            }
            return Integer.toString(lines);
        }

        void display(String msg, String s, int t){
            JOptionPane.showMessageDialog(null, msg, s, t);
        }
    }
