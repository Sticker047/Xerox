import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileUtils {
    public static long calculateFolderSize(String path) {
        try {
            long size = Files.walk(Path.of(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .mapToLong(File::length)
                    .sum();


            return size;
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return 0;
    }

    public static String sizeConverter(long size) {

        if (size < 1024) return size + " Б";

        int exp = (int) (Math.log(size) / (Math.log(1024)));

        char unitsPrefix = "КМГТПЭ".charAt(exp - 1);

        return String.format("%.2f %sБ", size / Math.pow(1024, exp), unitsPrefix);

    }

    public static String directoryRequest(String request) {
        File directory;
        String path = "";
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println(request);
                path = scanner.nextLine();
                directory = new File(path);

                if (directory.exists() && directory.isDirectory() && path.charAt(path.length() - 1) != '\\') break;
                else System.out.println("Папка не найдена, попробуйте ещё раз");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return path;
    }

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {

        try {
            Stream<Path> str = Files.walk(Path.of(sourceDirectory));
            str.forEach(e ->
            {
                try {
                    if (!e.equals(Path.of(sourceDirectory)))
                        Files.copy(e, Path.of(destinationDirectory + e.toString().substring(sourceDirectory.length())));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
