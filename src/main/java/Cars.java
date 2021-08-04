import lombok.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                BigDecimal price = resultSet.getBigDecimal("cena/24h");

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

    public static void addCars(){

    }
}