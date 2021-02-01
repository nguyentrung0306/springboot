import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class copyFile {
    public static void main(String[] args) {
        copyF();
    }

     public static void copyF() {
         try {
             File myFile = new File("C:/Users/PGT/Desktop/change.txt");
             Scanner myReader = new Scanner(myFile);
             while (myReader.hasNextLine()) {
                 String filePath = myReader.nextLine();
                 String target = "C:/Users/PGT/Desktop/" + filePath;
                 String resource = "C:/Users/PGT/Desktop/VHR_3.0/vhr3.0/" + filePath;
                 Path targetPath = Paths.get(target);
                 Path resourcePath = Paths.get(resource);

                 if (!Files.exists(targetPath)) {
                     Files.createDirectories(targetPath);
                 }
                 Files.copy(resourcePath,targetPath, StandardCopyOption.REPLACE_EXISTING);
             }
             myReader.close();
         } catch (IOException e) {
             System.out.println("An error occurred.");
             e.printStackTrace();
         }
     }
}
