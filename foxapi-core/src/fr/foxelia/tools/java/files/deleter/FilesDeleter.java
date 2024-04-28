package fr.foxelia.tools.java.files.deleter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FilesDeleter is a class to delete files and directories recursively
 * <br>License: CC BY-SA 4.0
 * @author Foxelia
 * @apiNote This class was hugely inspired from an answer previously posted on internet, but I can't find the source anymore.
 * @version 1.0
 */
public class FilesDeleter {

    /**
     * Delete a file or a directory recursively
     * @param file The file or directory to delete
     * @throws IOException If the file or directory can't be deleted
     */
    public static void deleteRecursively(File file) throws IOException {
        if(file.isDirectory()) {
            for(File subfiles : file.listFiles()) deleteRecursively(subfiles);
        }
        if(!file.delete()) throw new FileNotFoundException("Failed to delete file: " + file);
    }

}
