package de.lcraft.lapi.javaUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    public static List<File> getAllFilesFromFolder(File folder) {
        return Arrays.asList(Objects.requireNonNull(folder.listFiles()));
    }

}
