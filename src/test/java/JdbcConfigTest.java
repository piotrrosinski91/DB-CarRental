import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class JdbcConfigTest {

    @Test
    public void shouldReturnConfig(){
        Connection connection = JdbcConfig.getConnection();

        Assert.assertNull(connection);
    }
}

