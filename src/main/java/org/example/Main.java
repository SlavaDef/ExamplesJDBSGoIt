package org.example;

import com.goIt.javacore5.feature.DataBazeInitService;
import com.goIt.javacore5.feature.Human.HumanServise;
import com.goIt.javacore5.feature.Human.HumanServiseV2;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Storage storage = Storage.getInstance(); // тільки так можемо отримати обьект

        // тут визиваємо метод executeUpdate із класу Storage і передаємо команду sql
        //  storage.executeUpdate("DROP TABLE test_table2"); // видалення таблиці

        //storage.executeUpdate("CREATE TABLE test_table44 (name VARCHAR(100))"); // cтворення таблиці
        //storage.executeUpdate("DROP TABLE test_table44");

        new DataBazeInitService().initDB(storage);

        // INSERT INTO human
       /* String insertSql = String.format(
                "INSERT INTO human (name,birstday) VALUES('%s', '%s')",
                "Elon Musk",
                LocalDate.now()
                // LocalDate.of(1960,05,12)
        );
        storage.executeUpdate(insertSql); */

        // SELECT FROM human

        //String selectSQL = "SELECT id, name, birstday FROM human WHERE id = 3 ";

        //storage.selectFromTable(selectSQL); // метод повертає даннф з таблицф по індексу

        // storage.insertIntoTable("human", "Erick Kartman",2000,10,20);

        // HumanServise humanServise = new HumanServise(storage); // обьект класу

        //   humanServise.createNewHuman("Sara Konrat", LocalDate.now().minusYears(5));

        // humanServise.printHumanIds();

        // humanServise.printHumanInfo(4);

        HumanServiseV2 humanServiseV2 = new HumanServiseV2(storage);

       /* StringBuilder longName = new StringBuilder(); // одне слово 100 разів
        for(int i = 0;i<100;i++){
            longName.append("Text");
        } */

       /*  boolean resalt = humanServiseV2.createHuman(
                 // longName.toString(), // пробуємо додати рядок більше 100
                "Tom J",
                 LocalDate.now().minusYears(40)
        );

         System.out.println(resalt);
*/
        // humanServiseV2.printHumanInfo(7);
      /*  for (long id : humanServiseV2.getIds()) { // цікавий вивід масиву данних задіяно два метода з класу
            humanServiseV2.printHumanInfo(id);
        } */
        // List<Long> ids = humanServiseV2.getIds();
        // System.out.println("ids = " + ids);

        // заміряємо час виконнання Statement і prepareStatement

        HumanServise humanServise22 = new HumanServise(storage);
        HumanServiseV2 humanServiseV22 = new HumanServiseV2(storage);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
           // humanServise22.printHumanInfo(1);
            humanServiseV22.printHumanInfo(1);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(end+" milicecs");
    }
}