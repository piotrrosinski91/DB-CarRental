import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class JdbcConfigTeset {

    @Test
    public void shouldReturnConfig(){
        Connection connection = JdbcConfig.getConnection();

        Assert.assertEquals(JdbcConfig.CONFIG, connection);
    }
}

