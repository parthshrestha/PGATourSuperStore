package Observer;

import java.io.*;

public class Logger implements Subscriber{
    protected String fileName;
    protected String source;
    public Logger(int day)
    {
        //create new file using day
        fileName = "logger_" + day + ".txt";
        //source= "/Users/shrestha/Desktop/LearnJAVA/Project4/src/proj4/Logs/";
        source = "./Logs/";
        try {
            File myFile = new File(source+fileName);
            myFile.getParentFile().mkdirs();
            if (myFile.createNewFile()) {//to know the file is reated
                System.out.println("File created: " + myFile.getName());
            } else {//if there is an error and it creates a second one
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("**An error has occurred.**");
            e.printStackTrace();
        }//referenced from https://www.w3schools.com/java/java_files_create.asp
    }


    @Override
    public void update(String type,String msg) {
//if logger in front of string
        //write to it
        try {

            if (type== "logstaff")
            {
                //System.out.println("logging staff");
//                PrintWriter myWriter = new PrintWriter(source+ fileName);
                PrintWriter myWriter = new PrintWriter(new FileOutputStream(new File(source+fileName),true));
                myWriter.printf("%-40s",msg);
                myWriter.close();
            }
            else if(type == "logstaffln")
            {
                //System.out.println("logging staff end line");
//                PrintWriter myWriter = new PrintWriter(source+ fileName);
                PrintWriter myWriter = new PrintWriter(new FileOutputStream(new File(source+fileName),true));
                myWriter.printf("%-40s\n",msg);
                myWriter.close();
            }
            else if (type == "logInventory")
            {
                //System.out.println("logging inventory");
//                PrintWriter myWriter = new PrintWriter(source+fileName);
                PrintWriter myWriter = new PrintWriter(new FileOutputStream(new File(source+fileName),true));
                myWriter.printf("%-40s",msg);
                myWriter.close();
            }
            else if(type == "logInventoryln")
            {
                //System.out.println("logging inventory end line");
//                PrintWriter myWriter = new PrintWriter(source+fileName);
                PrintWriter myWriter = new PrintWriter(new FileOutputStream(new File(source+fileName),true));
                myWriter.printf("%-40s\n",msg);
                myWriter.close();
            }
            if(type == "log")
            {
                //System.out.println("logging regular");
                FileWriter myWriter = new FileWriter(source+fileName,true);

                myWriter.write(": "+msg+"\n");
                myWriter.flush();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
