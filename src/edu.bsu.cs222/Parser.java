import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    public List<Revision> parseCode(InputStream input) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisions = new JsonArray();
        List<Revision> revisionList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisions = entryObject.getAsJsonArray("revisions");
        }
        for (JsonElement value : revisions) {
            Revision revision = getValues(value);
            revisionList.add(revision);
            System.out.println(revision.getUserName());

        }
        return revisionList;
    }

    private Revision getValues(JsonElement value) {
        String userName = "";
        String timeStamp = "";
        Revision revision = null;

        for (Map.Entry<String, JsonElement> revisionMap : value.getAsJsonObject().entrySet()) {
            String currentValue = revisionMap.getValue().getAsString();
            if (revisionMap.getKey().equals("user")) {              //Switch statement would not work
                userName = currentValue;                            //because these aren't constant
            } else if (revisionMap.getKey().equals("anon")) {       //variables.
                String anon = revisionMap.getValue().getAsString();
                if (revisionMap.getKey().equals("timestamp")) {
                    timeStamp = currentValue;
                }
                revision = new Revision(userName, anon, timeStamp);
            } else if (revisionMap.getKey().equals("timestamp")) {
                timeStamp = currentValue;
                revision = new Revision(userName, timeStamp);
            }
        }
        return revision;
    }
}
