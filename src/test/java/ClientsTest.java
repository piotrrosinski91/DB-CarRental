import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class ClientsTest {

    @Test
    public void shouldShowClients() throws SQLException {
        Assert.assertNotNull(Clients.showClients());
    }
}
