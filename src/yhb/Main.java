package yhb;

import yhb.gui.MainPanel;
import yhb.utils.VerifyCode;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {

    public static String sessionId = null;
    public static int lastQueryItemCount = -1;
    public static boolean running = false;

    public static void main(String[] args) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {

            URL url = new URL("http://222.200.98.147/");

            connection = (HttpURLConnection) url.openConnection();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            sessionId = getSessionId(connection);

            BufferedImage imageIcon = VerifyCode.ask(sessionId);
            if (imageIcon != null) {
                MainPanel mainPanel = new MainPanel();
                mainPanel.setVerifyCode(imageIcon);
                mainPanel.setVisible(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getSessionId(HttpURLConnection connection) {
        //获取cookie
        Map<String, List<String>> map = connection.getHeaderFields();
        Set<String> set = map.keySet();

        for (String key : set) {
            if (key != null && key.equals("Set-Cookie")) {
                return getValueFromList(map.get(key));
            }
        }

        return null;
    }

    private static String getValueFromList(List<String> headerValue) {

        StringBuilder builder = new StringBuilder();

        for (String str : headerValue) {
            builder.append(str);
        }

        return builder.toString();
    }
}
