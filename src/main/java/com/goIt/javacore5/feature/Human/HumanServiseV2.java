package com.goIt.javacore5.feature.Human;

import org.example.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HumanServiseV2 {

    // оскільки сам сторедж повертає конекшн то робимо конструктов який буде приймати цей сторедж
    // потрібно два преперед стетмента завдяки одному будио отримувати інфу з хьюмана завдяки іншому
    // будемо створювати хюмена

    private PreparedStatement insertSt;
    private PreparedStatement selectByIdSt;

    // наступна задача вивести всіх юзерів

    private PreparedStatement selectAllSt;

    public HumanServiseV2(Storage storage) throws SQLException {
        Connection connection = storage.getConnection();
        // далі ніби створюємо шаблони для PreparedStatement

        insertSt = connection.prepareStatement(
                // VALUES(?,?) на місці параметрів просто вказуємо "?" далі будемо дописувати + не потрібні лапки
                "INSERT INTO human (name, birstday) VALUES(?,?)"
        );

        selectByIdSt = connection.prepareStatement(
                "SELECT name, birstday FROM human WHERE id = ? "
        );

        selectAllSt = connection.prepareStatement(
                "SELECT id FROM human"
        );
    }

    // тепер пишемо методи перший для вставки

    public boolean createHuman(String name, LocalDate birstday) {
        try {
            insertSt.setString(1, name);
            insertSt.setString(2, birstday.toString());// передамо дату як стринг
            return insertSt.executeUpdate() == 1; // якщо метод поверне  1 то все ок

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // метод для отримання інфи по id
    public boolean printHumanInfo(long id) {

        try {
            selectByIdSt.setLong(1, id);
        } catch (Exception ex) {
            return false;
        }
        try (ResultSet rs = selectByIdSt.executeQuery()) {
            if (!rs.next()) {
                System.out.println("Human with id " + id + " not foun");
                return false;
            }
            String name = rs.getString("name");
            String birstday = rs.getString("birstday");
            System.out.println("name = " + name + ", birstday = " + birstday);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public List<Long> getIds() {

        List<Long> list = new ArrayList();

        try (ResultSet rs = selectAllSt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getLong("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}


