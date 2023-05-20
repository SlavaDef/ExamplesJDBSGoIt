package org.example;

import java.sql.*;
import java.time.LocalDate;

public class Storage {
    // робимо клас синглтоном тобто клас у якого є рівно один обьект
         // одразу ініціалізуємо, конструктор приватний більше обьект не створиш
    private static final Storage INSTANCE = new Storage();

    private Connection connection; // створюємо конекшн
    private Storage()  { // в конструкторі одразу ініціалізуємо цей конекшн

        try {
            // адреса бази h2  ./ вказує що в поточному каталозі
            // но тут буде працювати не у всіх бо щляхи різні перероблюємо на считування с дсон
          //  String connectionUrl = "jdbc:h2:C:\\Users\\Vyacheslav\\PROJECTS\\WORK_WITH_JDBS/test";
             String connectionUrl = new Prefs().getString(Prefs.DB_URL);
            // буквально дай конекшн по урл
             connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static Storage getInstance(){
        return INSTANCE;
} // повертає обьект
public Connection getConnection(){
        return connection;
} // повертає конекшн

public void close()  {
    try {
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public int executeUpdate(String sql) { // метод для виконання запиту
    try (Statement st = connection.createStatement() ){
        return st.executeUpdate(sql); // тут повертаеться інт тому в методі повертаємо його

    } catch (Exception e) {
        e.printStackTrace();

        return -1;
    }

}
    // SELECT FROM table
public void selectFromTable(String selectSQL) throws SQLException {
    Storage storage = Storage.getInstance();

    Statement st = storage.getConnection().createStatement();
    ResultSet res  = st.executeQuery(selectSQL); // патерн ітератор

    if(res.next()){
        // extract
        long id = res.getLong("id");
        String name = res.getString("name");
        LocalDate birstday =  LocalDate.parse(res.getString("birstday"));

        System.out.println("id = "+ id);
        System.out.println("name = "+ name);
        System.out.println("birstday = "+ birstday);

    }else {
        System.out.println("Human with this id not found");
    }
    res.close();
    st.close();
}

// INSERT INTO human

    public void insertIntoTable(String nameTable,String name,LocalDate birstday){
        Storage storage = Storage.getInstance();
        String insertSql = String.format(
                "INSERT INTO " + nameTable + " (name,birstday) VALUES('%s', '%s')",
                name,
               // LocalDate.now()
                birstday
        );
                // LocalDate.of(1960,05,12)
        storage.executeUpdate(insertSql);
    }


// Statement предназначен для выполнения простых SQL-запросов без параметров;
// содержит базовые методы для выполнения запросов и извлечения результатов.

// Для соединения с БД необходимо использовать класс Connection пакета java.sql.
// После установления соединения можно выполнять различные SQL-запросы и получать результаты
// их обработки сервером. Приложение может открыть одно или несколько соединений с одной или
// несколькими различными СУБД.
// Открытие соединения, getConnection
// Стандартный способ установления соединения - это вызов метода getConnection класса DriverManager.
// Методу getConnection необходимо передать строку URL (Uniform Resource Locator).
// Стандарт подключения к серверу базы данных позволяет использовать следующие методы getConnection
// с параметрами:
// getConnection(url);
// getConnection(url, properties);
// getConnection(url, username, password);
// При использовании первого варианта, все параметры подключения указываются в url.

}
