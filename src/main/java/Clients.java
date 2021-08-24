import lombok.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Clients {
    private int id;
    private String name;
    private String surname;
    private int phoneNumber;


    public void addClients() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        PreparedStatement preparedStatement = JdbcConfig.getConnection().prepareStatement(
                "INSERT into clients(name, surname, phoneNumber) VALUES (?, ?, ?)");

        System.out.println("Adding new Clients...");
        System.out.println("Type name");
        preparedStatement.setString(1, scanner.nextLine());
        System.out.println("Type surname");
        preparedStatement.setString(2, scanner.nextLine());

        while (true) {
            try {
                System.out.println("Type phone number");
                preparedStatement.setInt(3, scanner.nextInt());
                scanner.nextLine();

                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect value - must be number");
                scanner.next();
            }
        }
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void removeClients() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean notFoundID = true;
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id from clients");
        PreparedStatement preparedStatement;
        int id;
        System.out.println("\nType ID client witch you want to remove");
        while (true) {
            try {
                id = scanner.nextInt();
                while (resultSet.next()) {
                    if (id == resultSet.getInt("id")) {
                        preparedStatement = JdbcConfig.getConnection().prepareStatement("DELETE from clients where id = " + id);
                        preparedStatement.execute();
                        System.out.println("Client with ID: " + id + " was removed");
                        notFoundID = false;
                        break;
                    }

                }
                if (notFoundID) {
                    System.out.println("ID does not exist");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nIncorrect value - must be number");
                scanner.next();
            }
        }
        statement.close();
        resultSet.close();
    }
    public static void modifyClients() throws SQLException {
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id from clients");
        PreparedStatement preparedStatement;
        Scanner scanner = new Scanner(System.in);
        int id;
        String nameOfColumn, value;
        boolean notFoundID = true;
        System.out.println("\nType ID clients witch you want to modify");
        while (true) {
            try {
                id = scanner.nextInt();
                scanner.nextLine();
                while (resultSet.next()) {
                    if (id == resultSet.getInt("id")) {
                        System.out.println("Type new value");
                        value = scanner.nextLine();
                        System.out.println("Type name of column to place the value");
                        nameOfColumn = scanner.nextLine();
                        try {
                            preparedStatement = JdbcConfig.getConnection().prepareStatement("UPDATE clients SET " + nameOfColumn + " = " + value + " WHERE clients.id = " + id);
                            preparedStatement.execute();
                            System.out.println("Done! Client " + id + " updated. New value for " + nameOfColumn + " is " + value);
                        } catch (SQLException e){
                            System.out.println("Column: " + nameOfColumn + " does not exist");
                        }
                        notFoundID = false;
                        break;
                    }

                }
                if (notFoundID) {
                    System.out.println("ID does not exist");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nIncorrect value - must be number");
                scanner.next();
            }
        }
    }
    public static List<Clients> showClients() throws SQLException {
        List<Clients> clientsList = new ArrayList<>();
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from clientfs");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int phoneNumber = resultSet.getInt("phoneNumber");

            Clients clients = new Clients(id, name, surname, phoneNumber);
            clientsList.add(clients);
        }
        for (Clients clients : clientsList) {
            System.out.println(clients);
        }
        statement.close();
        resultSet.close();

        return clientsList;
    }
}
