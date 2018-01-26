package yhb.utils;

import com.google.gson.Gson;
import yhb.entity.LoginBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    public static boolean doLogin(String account, String password, String verifyCode, String sessionId) {
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL("http://222.200.98.147/new/login");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", sessionId);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("account=" + account + "&" +
                    "pwd=" + password + "&" +
                    "verifycode=" + verifyCode);


            String loginMsg = readInputStream(connection.getInputStream());

            LoginBean loginBean = new Gson().fromJson(loginMsg, LoginBean.class);

            return "登录成功".equals(loginBean.getMessage());


        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return false;
    }

    public static String doGetData(String sessionId) {
        String rawScore = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://222.200.98.147/xskccjxx!getDataList.action");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", sessionId);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("xnxqdm=201701&" +
                    "page=1&" +
                    "rows=50&" +
                    "sort=xnxqdm&" +
                    "order=asc");

            rawScore = readInputStream(connection.getInputStream());

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return rawScore;
    }

    private static String readInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder builder = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

}
