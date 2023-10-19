import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/ogrencilistesi.txt";
        HashMap<String, String> ogrenciListesi = new HashMap<>();
        HashMap<String, String> cevapOgrenciListesi = new HashMap<>();
        HashMap<String, String> cevapOgrenciListesi2 = new HashMap<>();
        int randomOgrenciSayisi;
        Scanner scanner = new Scanner(System.in);
        try {
            //Read file
            FileReader javaFileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(javaFileReader);
            String line;
            //Write file
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/cevapogrencilistesi.txt", true));
            BufferedReader reader = new BufferedReader(new FileReader("src/cevapogrencilistesi.txt"));

            while ((bufferedReader.readLine()) != null) {
                line = bufferedReader.readLine();
                ogrenciListesi.put(String.valueOf(ogrenciListesi.size() + 1), line);
            }
            while ((reader.readLine()) != null) {
                line = reader.readLine();
                if (line != null) { // Check if the line is not null
                    StringBuilder sb = new StringBuilder(line);
                    int index = sb.indexOf(" ");
                    cevapOgrenciListesi2.put(sb.substring(0, index), sb.substring(index + 1));
                }
            }

            boolean choice = true;
            while (choice) {
                if (cevapOgrenciListesi2.size() >= 56) {
                    System.out.println("Tüm öğrenciler cevapladı");
                    writer = new BufferedWriter(new FileWriter("src/cevapogrencilistesi.txt"));
                    writer.write("");  //clears file
                    System.out.println("Dosya temizlendi");
                    break;
                }
                randomOgrenciSayisi = (int) (Math.random() * 56);
                boolean isExist = ogrenciListesi.containsKey(String.valueOf(randomOgrenciSayisi));
                if (isExist) {

                    System.out.println(ogrenciListesi.get(String.valueOf(randomOgrenciSayisi)));
                    System.out.print("\n öğrenci derste ve soruya yorum yaptı mı? (E/H) : ");
                    String cevap = scanner.nextLine();
                    if (cevap.equals("E") || cevap.equals("e")) {
                        isExist = cevapOgrenciListesi2.containsKey(String.valueOf(randomOgrenciSayisi));
                        if (!isExist) {
                            System.out.println("Öğrenci bulundu");
                            System.out.println("Öğrenci derste ve soruya yorum yaptı");
                            cevapOgrenciListesi.put(String.valueOf(randomOgrenciSayisi), ogrenciListesi.get(String.valueOf(randomOgrenciSayisi)));
                            writer.write(randomOgrenciSayisi + " " + ogrenciListesi.get(String.valueOf(randomOgrenciSayisi)));
                            writer.newLine();
                            writer.flush();
                            System.out.println("Yeni ogrenci secilsin mi? (E/H)");
                            String cevap2 = scanner.nextLine();
                            choice = cevap2.equals("E") || cevap2.equals("e");
                        } else {
                            System.out.println("Öğrenci derste ve soruya yorum yaptı");
                            System.out.println("Öğrenci daha önce cevapladı");
                            System.out.println("Yeni ogrenci secilsin mi? (E/H)");
                            String cevap2 = scanner.nextLine();
                            choice = cevap2.equals("E") || cevap2.equals("e");
                        }
                    } else {
                        System.out.println("Öğrenci derste ve soruya yorum yapmadı");
                        System.out.println("Yeni ogrenci secilsin mi? (E/H)");
                        String cevap2 = scanner.nextLine();
                        choice = cevap2.equals("E") || cevap2.equals("e");

                    }
                } else {
                    System.out.println("Öğrenci bulunamadı");
                }
            }
            writer.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}