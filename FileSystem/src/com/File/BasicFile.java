
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class BasicFile {
        //reference variable to store the chosen file
        File f;

    /**
     * Parameterless constructor
     * Uses JFileChooser to allow user to select a file
     * from the root directory of the program
     */
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

    /**
     * A method that copies the selected file into the same directory
     * with the same file name plus COPY added at the end of the name
     */
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

    /**
     * Writes to an output file with a name Given by user
     * @param fileName name of the output file
     * @param append if true, content is appended to the file,
     *              if false content is overwritten
     */
    public void writeToFile(String fileName,boolean append){
            try {
                String read = "";
                BufferedReader br = new BufferedReader(new FileReader(f));
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".txt", StandardCharsets.UTF_8,append));
                while((read = br.readLine()) != null)
                    bw.write(read+"\n");
                br.close();
                bw.close();
            }catch(FileNotFoundException e){
                display("File was not found\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }catch (IOException e){
                display("Input error\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * A method to get the path of the selected file
         * @return returns the path of the file
         */
        public String getAbsolutePath(){
           return f.getAbsolutePath();
        }

        /**
         * A method to get the size in kilobytes of the file
         * @return returns the size in kilobytes of the file as a double
         */
        public double fileSize(){
            return f.length()/1000;
        }

        /**
         * A method to get the path of all the files in
         * the directory of the selected file
         * @return returns the path of the file or directory as a String
         */
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
                display("You didn't choose a file\n [Error message]: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            return result;
        }

        /**
         * A method to get the number of lines of a text file
         * @return returns the number of lines of the selected file as a String
         */
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

        /**
         * A method to get the content of a file
         * @return returns the content of the selected file as a String
         */
        public String displayContent(){
            String read;
            String result="";
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                while (( read= br.readLine())!= null)
                    result += read + "\n";
                br.close();
            }catch(FileNotFoundException e){
                display("File was not found\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }catch (IOException e){
                display("Input error\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            return result;
        }

    /**
     * A method to search for a String inside of the selected file
     * @param key the String to search for
     * @return returns all the lines where the string was found
     */
    public String search(String key){
            String read = "";
            String result= "";
            int lineNumber = 0;

            try{
                BufferedReader br = new BufferedReader(new FileReader(f));
                while((read = br.readLine()) != null) {
                    lineNumber++;
                    String[] arr = read.split(" ");

                        for(int count = 0;count < arr.length;count++){
                        if(arr[count].equalsIgnoreCase(key)){
                            result += Integer.toString(lineNumber)+": "+read+"\n";
                            break;
                        }
                    }
                }
                br.close();
            }catch(FileNotFoundException e){
                display("File was not found\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }catch (IOException e){
                display("Input error\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            return result;
        }

    /**
     * This method tokenizes the text inside of the
     * selected text file and recognizes many of the keyboard symbols
     * @return the string with all the tokens in new lines
     */
    public String  tokenize(){
        String result = "";
            try{
                StreamTokenizer st = new StreamTokenizer(new FileReader(f));

                st.eolIsSignificant(true);
                st.wordChars('"','"');
                st.wordChars('@','@');
                st.wordChars(',',',');
                st.wordChars('\'','\'');
                st.wordChars('!','!');
                st.wordChars('/','/');
                st.wordChars('\\','\\');
                st.wordChars('%','%');
                st.wordChars('&','&');
                st.wordChars('$','$');
                st.wordChars('#','#');
                st.wordChars('*','*');
                st.wordChars('(','(');
                st.wordChars(')',')');
                st.wordChars('+','+');
                st.wordChars('-','-');
                st.wordChars('=','=');
                st.wordChars('{','{');
                st.wordChars('}','}');
                st.lowerCaseMode(true);

                while (st.nextToken() != StreamTokenizer.TT_EOF){
                    switch (st.ttype){
                        case StreamTokenizer.TT_WORD:
                            result += st.sval+"\n";
                            break;
                        case StreamTokenizer.TT_NUMBER:
                            result  += Double.toString(st.nval)+"\n";
                            break;
                        case StreamTokenizer.TT_EOL:
                            result += "\tNew Line-->"+ st.sval + (char) st.ttype;
                            break;
                        default:
                            result+= (char)st.ttype + "++> not recognize"+"\n";
                    }
                }
            }catch(FileNotFoundException e){
                display("File was not found\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }catch (IOException e){
                display("Input error\nerror message: "+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            return result;
        }

        /**
         * A method that displays a message in a JOptionPane window
         * @param msg the message to output
         * @param s the title of the window
         * @param t the type of message to be displayed (e.g. Error , Information etc)
         */
        void display(String msg, String s, int t){
            JOptionPane.showMessageDialog(null, msg, s, t);
        }
    }
