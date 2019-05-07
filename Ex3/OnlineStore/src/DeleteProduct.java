import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DeleteProduct {
    public static void main(String[] args) throws IOException {
        try {

            System.out.println("Introduceti numele produsului pe care doriti sa-l stergeti");

            Scanner scanner = new Scanner(System.in);
            String newName = scanner.nextLine();

            String nameNumberString;
            String name;

            int index;

            File file = new File("products.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {

                String message = raf.readLine();

                String[] mesaj = message.split(",");
                String productNameFile = mesaj[0];

                if (newName.equals(productNameFile)) {
                    found = true;
                    break;
                }
            }


            if (found) {

                File tmpFile = new File("temp.txt");

                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                // Set file pointer to start
                raf.seek(0);

                // Traversing the products.txt file
                while (raf.getFilePointer() < raf.length()) {

                    nameNumberString = raf.readLine();

                    index = nameNumberString.indexOf(',');
                    name = nameNumberString.substring(0, index);

                    if (name.equals(newName)) {

                        // Skip inserting this contact
                        // into the temporary file
                        continue;
                    }

                    // Add this contact in the temporary file
                    tmpraf.writeBytes(nameNumberString);

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

                tmpraf.close();
                raf.close();

                tmpFile.delete();

                System.out.println(" Product deleted. ");
            }
            else {
                raf.close();
                System.out.println(" Input name" + " does not exists. ");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}

