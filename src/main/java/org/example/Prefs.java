package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Prefs { // це ніби клас настройка

    public static final String DB_URL = "dbUrl";

    public static final String DEFAULT_FILE_NAME = "prefs.json";

    public static final String INIT_DB_FILENAME = "initDbSql";

    private Map<String,Object> prefs = new HashMap<>();

    public Prefs(){
        this(DEFAULT_FILE_NAME);
    } // конструктор для дефолтного


    public Prefs(String filename)  {
        try {
            String json = Files.readAllLines(Paths.get(filename))// читаємо всі рядки в вхідному файлі
                    .stream()
                    .collect(Collectors.joining("\n"));

   TypeToken<?> typeToken =
           TypeToken.getParameterized(Map.class,String.class,Object.class);

            prefs = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getString(String key){ // повертає якесь налаштування як рядок

        return getPref(key).toString();
    }

    public Object getPref(String key){ // метод повертає тип Object в якому може бути що завгодно
        return prefs.get(key);
    } // поверне обьект


}
