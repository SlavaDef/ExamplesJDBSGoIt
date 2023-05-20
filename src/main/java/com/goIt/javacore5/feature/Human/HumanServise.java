package com.goIt.javacore5.feature.Human;

import org.example.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class HumanServise {
    private Storage storage;
    private Statement statement;

    public HumanServise(Storage storage) throws SQLException { // конструктор приймає Storage як аргумент
        this.storage = storage;
        statement = storage.getConnection().createStatement();

    }




    public void printHumanIds() { // метод виводе всі id FROM human

        try (Statement st = storage.getConnection().createStatement()) {

            try (ResultSet res = st.executeQuery("SELECT id FROM human")) {
                while (res.next()) {
                    long id = res.getLong("id");
                    System.out.println("ID = " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void createNewHuman(String name, LocalDate birstday) {
        String insertSql = String.format("INSERT INTO human (name,birstday) VALUES('%s', '%s')",
                name,
                birstday
        );

        storage.executeUpdate(insertSql);
    }

    public void printHumanInfo(long id) throws SQLException {
      //  try (Statement st = storage.getConnection().createStatement()) {
            String sql = "SELECT name, birstday FROM human WHERE id = " + id;

            try (ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {
                    String name = rs.getString("name");
                    String birthday = rs.getString("birstday");
                    System.out.println("name = " + name + " birthday = " + birthday);

                } else {
                    System.out.println("No such id");
                }
            }
        }

    }
//}


/*
Класс ResultSet представляет результирующий набор данных и обеспечивает приложению
построчный доступ к результатам запросов. При обработке запроса ResultSet поддерживает
указатель на текущую обрабатываемую строку.
 */