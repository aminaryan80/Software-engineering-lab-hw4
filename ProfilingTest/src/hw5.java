import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class hw5 {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        createFile();
        writeToFile2();
    }

    private static void writeToFile2() {
        try {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < 100000; i++) {
                s.append(i).append('\n');
            }
            FileWriter myWriter = new FileWriter("result.txt");
            myWriter.write(s.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Successfully wrote to the file.");
    }

    private static void createFile() {
        try {
            File myObj = new File("result.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
