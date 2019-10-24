package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;


public class APIConnectionTest {

    @Test
    public void pullInputStream() throws Exception {
        SourceSort sourceSort = new SourceSort();
        sourceSort.pullInputStream();
        Assert.assertNotNull(sourceSort);
    }
}