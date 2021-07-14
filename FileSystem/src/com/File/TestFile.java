import javax.swing.*;


public class TestFile {

    public static void main(String[] args){


        boolean done = false;
        BasicFile f;


            try {
                while (!done) {

                    int i = GetData.getInt("Enter option\n1. OpenFile\n2. Quit");

                    switch (i) {
                        case 1:
                            f = new BasicFile();
                            int j = GetData.getInt("1.Copy the file\n2. Write to output file\n3. Display file content\n" +
                                    "4. Get Attributes\n5. Search\n6. Tokenize\n7. Exit");
                            switch (j) {
                                case 1:
                                    f.copy();
                                    break;
                                case 2:
                                    String fileName = GetData.getString("File name: ");

                                    int k = GetData.getInt("1. Append\n2. Overwrite");

                                    switch (k){
                                        case 1:
                                            f.writeToFile(fileName,true);
                                            break;
                                        case 2:
                                            f.writeToFile(fileName,false);
                                            break;
                                        default:
                                            display("Sorry that choice is not defined","Error");
                                            break;
                                    }
                                    break;
                                case 3:
                                    String display = "File Content: \n\n"+f.displayContent();
                                    JTextArea text = new JTextArea(display, 10, 50);
                                    JScrollPane pane = new JScrollPane(text);
                                    JOptionPane.showMessageDialog(null, pane, "Attributes",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                case 4:
                                    display = "File Path: "+f.getAbsolutePath()+"\n\nFiles in the Directory: \n"+f.getPaths()+
                                                        "\nFile size: "+f.fileSize()+"KB\n\nNumber of lines: "+f.numberOfLines();
                                    text = new JTextArea(display, 10, 50);
                                    pane = new JScrollPane(text);
                                    JOptionPane.showMessageDialog(null, pane, "Directory paths",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                case 5:
                                    String key = GetData.getString("What would you like to search for ?");
                                    text = new JTextArea(f.search(key), 10, 50);
                                    pane = new JScrollPane(text);
                                    JOptionPane.showMessageDialog(null, pane, "Search",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                case 6:
                                    text = new JTextArea(f.tokenize(), 10, 50);
                                    pane = new JScrollPane(text);
                                    JOptionPane.showMessageDialog(null, pane, "Tokenized input",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                case 7:
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Sorry that option is not defined, try a different one", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case 2:
                            done = true;
                            break;
                        default:
                            display("This option is undefined", "Error");
                            break;
                    }
                }
                }catch(NumberFormatException | NullPointerException e){
                    display(e.toString(), "Error");
                }
            }

            //method to display any errors
    static void display(String s, String err){
        JOptionPane.showMessageDialog(null, s,err, JOptionPane.ERROR_MESSAGE);
    }
}
