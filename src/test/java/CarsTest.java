import org.junit.Assert;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarsTest {

    @Test
    public void shouldShowCars() throws SQLException {

        Assert.assertNotNull(Cars.showCars());
    }

    @Test
    public void shouldAddCar() throws SQLException {

        boolean notEmpty = false;

        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from carsTest");

        if (!(resultSet.next())) {
            Cars cars = Cars.addCarsForTests();
            PreparedStatement preparedStatement = JdbcConfig.getConnection().prepareStatement(
                    "INSERT into carsTest(mark, model, yearOfProduction, capacity, fuelType, doors, regNumber, price)" +
                            " VALUES ('" + cars.getMark() + "', '" + cars.getModel() + "', '" + cars.getYearOfProduction() + "', '" + cars.getCapacity() + "', '" +
                            cars.getFuelType() + "', '" + cars.getDoors() + "', '" + cars.getRegNumber() + "', '" + cars.getPrice() + "')");
            preparedStatement.execute();
            preparedStatement = JdbcConfig.getConnection().prepareStatement("DELETE from carsTest where price = 75");
            preparedStatement.execute();
            notEmpty = true;
        }
        Assert.assertTrue(notEmpty);
    }


    @Test
    public void shouldRemoveCar() {
        //mock??
    }


}
