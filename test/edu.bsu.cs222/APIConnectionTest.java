import org.junit.Assert;
import org.junit.Test;


public class APIConnectionTest {

    @Test
    public void pullInputStream() throws Exception {
        SourceSearch sourceSearch = new SourceSearch();
        sourceSearch.pullInputStream();
        Assert.assertNotNull(sourceSearch);
    }
}