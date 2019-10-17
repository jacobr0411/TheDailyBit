
import org.junit.Assert;
import org.junit.Test;


public class APIConnectionTest {

    @Test
    public void pullInputStream() throws Exception {
        APIConnection apiConnection = new APIConnection();
        apiConnection.pullInputStream();
        Assert.assertNotNull(apiConnection);
    }
}