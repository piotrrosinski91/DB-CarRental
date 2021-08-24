import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

public class CarsTest {

    @Test
    public void shouldShowCars() throws SQLException{

        Assert.assertNotNull(Cars.showCars());
    }
    @Test
    public void shouldAddCar() throws  SQLException{

    }

    @Test
    public void shouldRemoveCar(){
        //mock??
    }


}
