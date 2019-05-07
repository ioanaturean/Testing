import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DisplayProducts {

    public static void main(String data[]) {

        try {
            String productName;

            File file = new File("products.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "r");

            while (raf.getFilePointer() < raf.length()) {

                productName = raf.readLine();

                String[] mesaj = productName.split(",");
                String productNameFile = mesaj[0];
                String priceFile = mesaj[1];
                String quantFile = mesaj[2];

                System.out.println("Product Name: " + productNameFile + "\n" +
                        "Price: " + priceFile + "\n" +
                        "Quantity: " + quantFile + "\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
