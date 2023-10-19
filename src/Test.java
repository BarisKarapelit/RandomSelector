import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("src/cevapogrencilistesi.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("Some String");
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.println("Blah blah" + "\n");
        printWriter.close();
    }
}
