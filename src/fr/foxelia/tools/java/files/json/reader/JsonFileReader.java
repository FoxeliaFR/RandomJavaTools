package fr.foxelia.tools.java.files.json.reader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * This class is used to read a JSON file and return its content as a {@link com.google.gson.JsonObject} or a {@link java.lang.String}
 * <br>It use the GSON library to parse the JSON file
 * <br>License : CC BY-SA 4.0
 * <br>Author : Zarinoow, Foxelia
 * @version 1.0
 */
public class JsonFileReader {

    /**
     * This method is used to get the content of a JSON file from an {@link java.io.InputStream} and return it as a {@link java.lang.String}
     * @param inputStream The input stream of the JSON file
     * @return The content of the JSON file as a {@link java.lang.String}
     */
    public static String getJsonAsString(InputStream inputStream) {
        StringBuilder content = new StringBuilder();
        // Read the file
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, StandardCharsets.UTF_8))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    /**
     * This method is used to get the content of a JSON file from a {@link java.io.File} and return it as a {@link java.lang.String}
     * @param file The JSON file
     * @return The content of the JSON file as a {@link java.lang.String}
     * @throws FileNotFoundException If the file is not found
     * @see #getJsonAsString(InputStream)
     */
    public static String getJsonAsString(File file) throws FileNotFoundException {
        return getJsonAsString(new FileInputStream(file));
    }

    /**
     * This method is used to get the content of a JSON file from a {@link java.io.File} and return it as a {@link com.google.gson.JsonObject}
     * @param file The JSON file
     * @return The content of the JSON file as a {@link com.google.gson.JsonObject}
     * @throws FileNotFoundException If the file is not found
     * @see #getJsonAsString(File)
     */

    public static JsonObject getJsonAsObject(File file) throws FileNotFoundException {
        return JsonParser.parseString(getJsonAsString(file)).getAsJsonObject();
    }

}
