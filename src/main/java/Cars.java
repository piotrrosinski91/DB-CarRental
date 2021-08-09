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


    public static List<Cars> showCars() throws SQLException {
        List<Cars> carsList = new ArrayList<>();
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from auta");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String mark = resultSet.getString("marka");
                String model = resultSet.getString("model");
                int yearOfProduction = resultSet.getInt("rocznik");
                int capacity = resultSet.getInt("pojemnosc");
                String fuelType = resultSet.getString("rodzaj_paliwa");
                int doors = resultSet.getInt("ilosc_drzwi");
                String regNumber = resultSet.getString("nr_rej");
                BigDecimal price = resultSet.getBigDecimal("cena");

                Cars cars = new Cars(id, mark, model, yearOfProduction, capacity, fuelType, doors, regNumber, price);
                carsList.add(cars);
            }
        for (Cars cars: carsList) {
            System.out.println(cars);
        }
        statement.close();
        resultSet.close();

        return carsList;
    }

    public static void addCars() throws SQLException{

        boolean incorrectYear = true, incorrectCap = true, incorrectDoors = true;

        Scanner scanner = new Scanner(System.in);

        PreparedStatement preparedStatement = JdbcConfig.getConnection().prepareStatement(
                "INSERT into auta(marka, model, rocznik, pojemnosc, rodzaj_paliwa, ilosc_drzwi, nr_rej, cena) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        System.out.println("Adding new Car...");
        System.out.println("Type mark");
        preparedStatement.setString(1, scanner.nextLine());
        System.out.println("Type model");
        preparedStatement.setString(2, scanner.nextLine());
        System.out.println("Type fuelType");
        preparedStatement.setString(5, scanner.nextLine());
        System.out.println("Type regNumber");
        preparedStatement.setString(7, scanner.nextLine());

        while(true) {
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

    public static void removeCars() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        boolean notFoundID = true;
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id from auta");
        PreparedStatement preparedStatement;
        int id;
        showCars();
        System.out.println("\nType ID car witch you want to remove");
        while(true){
            try{
                id = scanner.nextInt();
                while (resultSet.next()){
                    if(id == resultSet.getInt("id")){
                        preparedStatement = JdbcConfig.getConnection().prepareStatement("DELETE from auta where id = " + id);
                        preparedStatement.execute();
                        System.out.println("Car with ID: " + id + " was removed");
                        notFoundID = false;
                        break;
                    }

                }
                if(notFoundID){
                    System.out.println("ID does not exist");
                }
                    break;
            } catch (InputMismatchException e){
                System.out.println("\nIncorrect value - must be number");
                scanner.next();
            }
        }
    }
}