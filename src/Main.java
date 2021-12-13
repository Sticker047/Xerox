import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String from = FileUtils.directoryRequest("Введите путь до папки источника");
        String to = FileUtils.directoryRequest("Введите путь до папки назначения");

        FileUtils.copyFolder(from, to);

    }

}
