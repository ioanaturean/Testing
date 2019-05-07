import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class UpdateProduct {

    public static void main(String[] args) throws IOException {
        System.out.println("Introduceti produsul pe care doriti sa il modificati" +
                " <ProductName>,<Price>,<Quantity>");

        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        String[] mesaj = inputString.split(",");
        String newName = mesaj[0];
        String newPrice = mesaj[1];
        String newQuantity = mesaj[2];

        try {
            File file = new File("products.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                String message = raf.readLine();

                String[] mesaj1 = message.split(",");
                String productNameFile = mesaj1[0];

                if (newName.equals(productNameFile)) {
                    found = true;
                    break;
                }

            }

            // Update the contact if record exists.
            if (found) {

                File tmpFile = new File("temp.txt");

                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                raf.seek(0);

                // Traversing the friendsContact.txt file
                while (raf.getFilePointer() < raf.length()) {

                    String productName = raf.readLine();

                    String[] mesaj2 = productName.split(",");
                    String productNameFile = mesaj2[0];

                    if (newName.equals(productNameFile)) {

                        productName = productNameFile + "," + newPrice + "," + newQuantity;
                    }

                    tmpraf.writeBytes(productName);
                    tmpraf.writeBytes(System.lineSeparator());
                }


                // Set both files pointers to start
                raf.seek(0);
                tmpraf.seek(0);

                // Copy the contents from
                // the temporary file to original file.
                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                // Set the length of the original file
                // to that of temporary.
                raf.setLength(tmpraf.length());

                // Closing the resources.
                tmpraf.close();
                raf.close();

                // Deleting the temporary file
                tmpFile.delete();

                System.out.println(" Product updated. ");
            } else {

                raf.close();
                System.out.println(" Input name" + " does not exists. ");
            }

        } catch (IOException ioe) {
            System.out.println(ioe);

        }
    }
}
