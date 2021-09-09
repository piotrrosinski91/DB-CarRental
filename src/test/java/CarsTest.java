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

        int id = Cars.addCarsForTests();
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("Select id from carsTest");

        while (rs.next())
            if (id == rs.getInt(1)) {
                Assert.assertTrue(true);
            }
    }


    @Test
    public void shouldRemoveCar() {
        //mock??
    }


}
