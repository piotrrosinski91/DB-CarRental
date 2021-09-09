import lombok.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cars {
    private int id;
    private String mark;
    private String model;
    private int yearOfProduction;
    private int capacity;
    private String fuelType;
    private int doors;
    private String regNumber;
    private BigDecimal price;

    public Cars(String mark, String model, int yearOfProduction, int capacity, String fuelType, int doors, String regNumber, BigDecimal price) {
        this.mark = mark;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.doors = doors;
        this.regNumber = regNumber;
        this.price = price;
    }

    public static List<Cars> showCars() throws SQLException {
        List<Cars> carsList = new ArrayList<>();
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from cars");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String mark = resultSet.getString("mark");
            String model = resultSet.getString("model");
            int yearOfProduction = resultSet.getInt("yearOfProduction");
            int capacity = resultSet.getInt("capacity");
            String fuelType = resultSet.getString("fuelType");
            int doors = resultSet.getInt("doors");
            String regNumber = resultSet.getString("regNumber");
            BigDecimal price = resultSet.getBigDecimal("price");

            Cars cars = new Cars(id, mark, model, yearOfProduction, capacity, fuelType, doors, regNumber, price);
            carsList.add(cars);
        }
        for (Cars cars : carsList) {
            System.out.println(cars);
        }
        statement.close();
        resultSet.close();

        return carsList;
    }

    public static void addCars() throws SQLException {

        boolean incorrectYear = true, incorrectCap = true, incorrectDoors = true;

        Scanner scanner = new Scanner(System.in);

        PreparedStatement preparedStatement = JdbcConfig.getConnection().prepareStatement(
                "INSERT into cars(mark, model, yearOfProduction, capacity, fuelType, doors, regNumber, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        System.out.println("Adding new Car...");
        System.out.println("Type mark");
        preparedStatement.setString(1, scanner.nextLine());
        System.out.println("Type model");
        preparedStatement.setString(2, scanner.nextLine());
        System.out.println("Type fuelType");
        preparedStatement.setString(5, scanner.nextLine());
        System.out.println("Type regNumber");
        preparedStatement.setString(7, scanner.nextLine());


        while (true) {
            try {
                if (incorrectYear) {
                    System.out.println("Type yearOfProduction");
                    preparedStatement.setInt(3, scanner.nextInt());
                    incorrectYear = false;
                    scanner.nextLine();
                }
                if (incorrectCap) {
                    System.out.println("Type capacity");
                    preparedStatement.setInt(4, scanner.nextInt());
                    incorrectCap = false;
                    scanner.nextLine();
                }
                if (incorrectDoors) {
                    System.out.println("Type doors");
                    preparedStatement.setInt(6, scanner.nextInt());
                    incorrectDoors = false;
                    scanner.nextLine();
                }
                System.out.println("Type price");
                preparedStatement.setBigDecimal(8, scanner.nextBigDecimal());
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
    public static int addCarsForTests() throws SQLException{

        int id = 0;

        String query = ("INSERT into carsTest(mark, model, yearOfProduction, capacity, fuelType, doors, regNumber, price) VALUES (1, 2, 3, 4, 5, 6, 7, 8)");
        Connection con = JdbcConfig.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        while(rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public static void removeCars() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean notFoundID = true;
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id from cars");
        PreparedStatement preparedStatement;
        int id;
        showCars();
        System.out.println("\nType ID car witch you want to remove");
        while (true) {
            try {
                id = scanner.nextInt();
                while (resultSet.next()) {
                    if (id == resultSet.getInt("id")) {
                        preparedStatement = JdbcConfig.getConnection().prepareStatement("DELETE from cars where id = " + id);
                        preparedStatement.execute();
                        System.out.println("Car with ID: " + id + " was removed");
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

    public static void modifyCars() throws SQLException {
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id from cars");
        PreparedStatement preparedStatement;
        Scanner scanner = new Scanner(System.in);
        String nameOfColumn, value, id;
        boolean notFoundID = true;
        showCars();
        System.out.println("\nType ID car witch you want to modify");
        while (true) {
            try {
                id = scanner.nextLine();
                while (resultSet.next()) {
                    if (id.equals(resultSet.getString("id"))) {
                        System.out.println("Type new value");
                        value = scanner.nextLine();
                        System.out.println("Type name of column to place the value");
                        nameOfColumn = scanner.nextLine();
                        try {
                            preparedStatement = JdbcConfig.getConnection().prepareStatement("UPDATE cars SET " + nameOfColumn + " = '" + value + "' WHERE cars.id = " + id);
                            preparedStatement.execute();
                            System.out.println("Done! Car " + id + " updated. New value for " + nameOfColumn + " is " + value);
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
}