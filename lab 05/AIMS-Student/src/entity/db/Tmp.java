package entity.db;

import java.io.File;
// Importing FileNotFoundException class for handling errors
import java.io.FileNotFoundException;
// Importing the Scanner class for reading text files
import java.util.Scanner;

class Tmp {
    public static void main(String[] args) {
        try {
            // Create f1 object of the file to read data
            File f1 = new File("./assets/db/aims.db");
            //System.out.println("da va try");
            Scanner dataReader = new Scanner(f1);
            while (dataReader.hasNextLine()) {
                System.out.println("da vao while");
                String fileData = dataReader.nextLine();
                System.out.println(fileData);
            }
            dataReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unexcpected error occurred!");
            exception.printStackTrace();
        }
    }
}