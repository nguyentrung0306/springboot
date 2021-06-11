import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class copyFile {

    public static void main(String[] args) {
        copyF();
    }

     public static void copyF() {
        Long count = 0l;
        long fileExist = 0l;
         try {
             File myFile = new File("C:/Users/PGT/Desktop/REPORT_VHR/phan.txt");
             Scanner myReader = new Scanner(myFile);
             while (myReader.hasNextLine()) {
                 String filePath = myReader.nextLine();
                 String target = "C:/Users/PGT/Desktop/vhr3.0/" + filePath;
                 String resource = "C:/Users/PGT/Desktop/VHR_3.0/vhr3.0/" + filePath;
                 Path targetPath = Paths.get(target);
                 Path resourcePath = Paths.get(resource);

                 if (!Files.exists(targetPath)) {
                     Files.createDirectories(targetPath);
                 }
                 Files.copy(resourcePath,targetPath, StandardCopyOption.REPLACE_EXISTING);
                 count ++;
             }
             myReader.close();
         } catch (IOException e) {
             System.out.println("An error occurred.");
             e.printStackTrace();
         }
         System.out.println("Count: " + count);
         System.out.println("File in dir: " + getFilesCount(new File("C:/Users/PGT/Desktop/vhr3.0/")));
     }

    public static int getFilesCount(File file) {
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files)
            if (f.isDirectory())
                count += getFilesCount(f);
            else
                count++;

        return count;
    }

}
