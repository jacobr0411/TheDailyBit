import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void connectToGoogle() {
        InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        Parser parser = new Parser();
        parser.connectToGoogle(input);
    }

    @Test
    public void getTitleList() {
        List<Articles> articleList = new ArrayList<>();
        Parser parser = new Parser();
        parser.getTitleList();
    }
}