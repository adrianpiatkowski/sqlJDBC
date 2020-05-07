import java.sql.*;

public class SQLConnection {
    /*
    1. Otwórz nowy projekt mavenowy  oraz wrzuć go na gita
    2. Utwórz klasę która połączy się z Twoją bazą danych oraz wrzuć na gita
    3. Wykonaj zapytanie które wyświetli wszystkie rekordy z tabeli employees oraz wrzuć na gita
    4. Utwórz metodę, która będzie dodawała nowe wpisy do bazy danych oraz wrzuć na gita
    5. Utwórz metodę, która będzie aktualizowałą wpisy w bazie danych oraz wrzuć na gita
    6. Utwórz metodę, która będzie usuwała wpisy z bazy danych
    7. Napisz tranzakcję dla metod typu CRUD oraz wrzuć na gita
    8. Napisz testy jednostkowe dla swojej aplikacji oraz wrzuć na gita
    9. Wykorzystaj projekt, w którym łączyliśmy się z API i pobieraliśmy dane o pracownikach
    i zrób metodę, która doda ich do bazy danych a brakujące dane wygeneruje lub będzie ustawiać domyślną wartość
    */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Connectis;integratedSecurity=true;";

        Connection conn = DriverManager.getConnection(url);
        System.out.println(conn);

        Statement statement = conn.createStatement();
        String sqlQuery = "Select * from Employees";
        ResultSet rs = statement.executeQuery(sqlQuery);
        while (rs.next()) {
            /* Przykład
            System.out.println(rs.getDate(8));*/
            System.out.println(rs.getDate("StartJobDate"));
        }



        //insert
        String insert = "Insert INTO Employees (LastName, FirstName, Address, City, Salary, Age, StartJobDate, Benefit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, "Jarek");
        ps.setString(2, "Garek");
        ps.setString(3, "Bursztynowa");
        ps.setString(4, "Wilno");
        ps.setInt(5, 4500);
        ps.setInt(6, 42);
        ps.setDate(7, Date.valueOf("2018-10-15"));
        ps.setInt(8, 1);

       int rowInsert = ps.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }

    }









    public void transactions(Connection conn) {
     //   String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=CONNECTIS;integratedSecurity=true;";
        try {
            conn.setAutoCommit(Boolean.FALSE);
            //do
            conn.commit();
        } catch (SQLException e) {
      //      conn.rollback();
            e.printStackTrace();
        } finally {
     //       conn.setAutoCommit(Boolean.TRUE);
        }
    }


}
