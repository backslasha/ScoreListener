package yhb.demo;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;

public class GsonDemo {
    public static void main(String[] args) throws IOException {
        String loginMsg = "{\"code\":0,\"data\":\"/login!welcome.action\",\"message\":\"登录成功\"}\n";

        JsonReader reader = new JsonReader(new StringReader(loginMsg));
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if ("code".equals(name)) {
                System.out.println(reader);
            }

            System.out.println(reader.nextString());

        }
    }
}
