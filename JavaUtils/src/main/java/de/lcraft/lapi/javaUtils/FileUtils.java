package de.lcraft.lapi.javaUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    public static List<File> getAllFilesFromFolder(File folder) {
        return Arrays.asList(Objects.requireNonNull(folder.listFiles()));
    }
    public static List<String> getAllLinesFromFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

}
