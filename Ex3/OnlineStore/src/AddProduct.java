import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import static java.lang.System.exit;

public class AddProduct {

    public static void main(String[] args) throws IOException {

        System.out.println("Introduceti <ProductName>,<Price>,<Quantity>");

        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        String[] mesaj = inputString.split(",");
        String productName = mesaj[0];
        String price = mesaj[1];
        String quantity = mesaj[2];

        File file = new File("products.txt");

        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        boolean found = false;

        while (raf.getFilePointer() < raf.length()) {

            String productNameFile = raf.readLine();
            String[] mesaj2 = productNameFile.split(",");
            String name = mesaj2[0];
            if (name.equals(productName)) {
                System.out.println("Avem " + name);
                found = true;
                exit(0);
            }
        }

        if (!found) {

            String nameNumberString = productName + "," + price + "," + quantity;
            raf.writeBytes(nameNumberString);
            raf.writeBytes(System.lineSeparator());
            System.out.println(" Product added. ");
            raf.close();

        }
    }
}