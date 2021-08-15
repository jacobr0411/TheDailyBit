package edu.bsu.cs222;

import edu.bsu.cs222.SourceSearch;
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