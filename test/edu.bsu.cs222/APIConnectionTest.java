import org.apiguardian.api.API;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.net.URLConnection;

import static org.junit.Assert.*;

public class APIConnectionTest {

    @Test
    public void pullInputStream() throws Exception {
        APIConnection apiConnection = new APIConnection();
        apiConnection.pullInputStream();
        Assert.assertNotNull(apiConnection);
    }
}