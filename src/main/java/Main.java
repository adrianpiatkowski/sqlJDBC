import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Connectis;integratedSecurity=true;";

        Connection conn = DriverManager.getConnection(url);
        System.out.println(conn);


        transactions(conn);

    }

    public static void transactions(Connection conn) throws SQLException{
        //TODO
        Scanner scanner = new Scanner(System.in);
        int wybrana=20;
        while (wybrana!=0){
            System.out.println("Co chcesz zrobić? \n1-Utworz nowy wpis\n2-Odczytaj dane\n3-Akutalizuj dane\n4-Usuń dane\n5-\n0-Zamknij program");
            wybrana = scanner.nextInt();

            switch (wybrana) {
                case 1:
                    create(conn);
                    break;
                case 2:
                    read(conn);
                    break;
                case 3:
                    update(conn);
                    break;
                case 4:
                    delete(conn);
                    break;
                case 5 :
            }

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

    private static void delete(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(conn);
        String delete = "DELETE FROM Employee WHERE ID=?";


        System.out.println("Podaj id pracownika do usunięcia");
        int id =scanner.nextInt();
        PreparedStatement ps2 = conn.prepareStatement(delete);
        ps2.setInt(1, id);

        int rowInsert = ps2.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }
    }

    private static void read(Connection conn) throws SQLException {

        System.out.println(conn);

        Statement statement = conn.createStatement();
        String sqlQuery = "Select * from Employee";
        ResultSet rs = statement.executeQuery(sqlQuery);
        while (rs.next()) {
            System.out.println("Id = " + rs.getInt(1));
            System.out.println("Nazwisko = " +rs.getString(2));
            System.out.println("Imię = "+rs.getString(3));
            System.out.println("Adres = "+rs.getString(4));
            System.out.println("Miasto = "+rs.getString(5));
            System.out.println("Wypłata = "+rs.getInt(6));
            System.out.println("Wiek = "+rs.getInt(7));
            System.out.println("Data rozpoczęcia pracy = "+rs.getDate(8));
          /* Przykład
            System.out.println(rs.getDate(8));
            System.out.println(rs.getDate("StartJobDate"));*/
            System.out.println("Benefit = "+rs.getInt(9));
        }

    }

    private static void create(Connection conn) throws SQLException {
        String insert = "Insert INTO Employee (LastName, FirstName, Address, City, Salary, Age, StartJobDate, Benefit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        wpisanieDanych(insert, conn);


    }

    private static void update(Connection conn) throws SQLException {

        System.out.println(conn);
        String update = "UPDATE Employee SET LastName=?, FirstName=?, Address=?, City=?, Salary=?, Age=?, StartJobDate=?, Benefit=? WHERE ID=?";

        Scanner scanner = new Scanner(System.in);
        String lastName;
        String firstName;
        String address;
        String city;
        int salary;
        int age;
        String startJobDate;
        int benefit;
        int id;


        System.out.println("Podaj nazwisko");
        lastName = scanner.next();
        System.out.println("Podaj imię");
        firstName=scanner.next();
        System.out.println("Podaj adres");
        address=scanner.next();
        System.out.println("Podaj miasto");
        city = scanner.next();
        System.out.println("Podaj pensję");
        salary=scanner.nextInt();
        System.out.println("Podaj wiek");
        age=scanner.nextInt();
        System.out.println("Podaj date rozpoczęcia pracy");
        startJobDate =scanner.next();
        System.out.println("CZy posiada benefit 0=nie 1=tak");
        benefit = scanner.nextInt();
        System.out.println("Podaj ID");
        id = scanner.nextInt();

        PreparedStatement ps = conn.prepareStatement(update);
        ps.setString(1, lastName);
        ps.setString(2, firstName);
        ps.setString(3, address);
        ps.setString(4, city);
        ps.setInt(5, salary);
        ps.setInt(6, age);
        ps.setDate(7, Date.valueOf(startJobDate));
        ps.setInt(8, benefit);
        ps.setInt(9,id);

        int rowInsert = ps.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }
    }

    private static void wpisanieDanych(String insert, Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String lastName;
        String firstName;
        String address;
        String city;
        int salary;
        int age;
        String startJobDate;
        int benefit;

        System.out.println("Podaj nazwisko");
        lastName = scanner.next();
        System.out.println("Podaj imię");
        firstName=scanner.next();
        System.out.println("Podaj adres");
        address=scanner.next();
        System.out.println("Podaj miasto");
        city = scanner.next();
        System.out.println("Podaj pensję");
        salary=scanner.nextInt();
        System.out.println("Podaj wiek");
        age=scanner.nextInt();
        System.out.println("Podaj date rozpoczęcia pracy");
        startJobDate =scanner.next();
        System.out.println("CZy posiada benefit 0=nie 1=tak");
        benefit = scanner.nextInt();

        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, lastName);
        ps.setString(2, firstName);
        ps.setString(3, address);
        ps.setString(4, city);
        ps.setInt(5, salary);
        ps.setInt(6, age);
        ps.setDate(7, Date.valueOf(startJobDate));
        ps.setInt(8, benefit);

        int rowInsert = ps.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }
    }



}
