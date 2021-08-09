import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConfigTest {

    @Test
    public void shouldReturnConfig() {

        Assert.assertNotNull(JdbcConfig.getConnection());
    }

}

