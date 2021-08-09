import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class CarsTest {

    @Test
    public void shouldShowCars() throws SQLException{

        Assert.assertNotNull(Cars.showCars());
    }
    @Test
    public void shouldAddCar(){
        //mock??
    }

    @Test
    public void shouldRemoveCar(){
        //mock??
    }


}
