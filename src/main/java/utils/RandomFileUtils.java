package utils;

import java.io.File;
import java.util.Random;

public class RandomFileUtils {
    public static String getRandomJpg(String directoryPath) {
        File folder = new File(directoryPath);

        File[] jpgFiles = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg")
        );

        if (jpgFiles == null || jpgFiles.length == 0) {
            throw new RuntimeException("Không tìm thấy file JPG trong thư mục: " + directoryPath);
        }

        Random random = new Random();
        File randomFile = jpgFiles[random.nextInt(jpgFiles.length)];

        return randomFile.getAbsolutePath();
    }
}
