import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class CreateFile {

   public void writeInFile(String msg) {
       try {
           FileWriter myWriter = new FileWriter("filename.txt");
           myWriter.write(msg);
           myWriter.close();
           System.out.println("Successfully wrote to the file.");
       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }


   }
}

