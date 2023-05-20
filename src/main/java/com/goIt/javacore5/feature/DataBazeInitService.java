package com.goIt.javacore5.feature;

import org.example.Prefs;
import org.example.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBazeInitService {

   // public static final String INIT_DB_FILENAME = "initDb.sql";

    public void initDB(Storage storage) throws IOException {

        Object initDbFilename = new Prefs().getString(Prefs.INIT_DB_FILENAME);

String sql = String.join(
        "\n",
        Files.readAllLines(Paths.get((String) initDbFilename))
        );

              storage.executeUpdate(sql);
    }
}
