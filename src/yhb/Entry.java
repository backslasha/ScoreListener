package yhb;

import com.google.gson.Gson;
import yhb.entity.Row;
import yhb.entity.ScoreResultBean;
import yhb.gui.MainPanel;
import yhb.utils.Utils;
import yhb.utils.Mail;
import yhb.utils.VerifyCode;

import javax.mail.MessagingException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Entry {

    public static String sessionId = null;
    public static int lastQueryItemCount = -1;

    public static void main(String[] args) {

        boolean headless = false;

        InputStream in = null;

        if (args.length == 1) {
            headless = args[0].equals("--headless");
            in = System.in;
        } else if (args.length == 2) {
            headless = args[0].equals("-f") || GraphicsEnvironment.isHeadless();
            File file = new File(args[1]);
            if (!file.exists()) {
                System.out.println("请指定正确的配置文件！");
                return;
            }
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (args.length == 0) {
            try {
                GraphicsEnvironment.isHeadless();
            } catch (HeadlessException e) {
                System.out.println("无图形界面环境！请使用 --headless 选项 或者 -f 选项");
            }
        }

        if (headless) {
            mainHeadless(in);
            return;
        }

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

    private static void mainHeadless(InputStream in) {

        if (in == null) {
            System.out.println("无效的输入流！");
            return;
        }

        String jSessionId, account, password, verifyCode;

        Scanner scanner = new Scanner(in);

        System.out.println("请找到一个浏览器，打开一个校园网，获取 JSESSIONID 的值，并在此输入：");

        jSessionId = "JSESSIONID=" + scanner.nextLine();

        System.out.println("请输入同一界面的验证码：");

        verifyCode = scanner.nextLine();

        System.out.println("请输入一个校园网帐号：");

        account = scanner.nextLine();

        System.out.println("请输入密码：");

        password = scanner.nextLine();

        if (!Utils.doLogin(account, password, verifyCode, jSessionId)) {
            System.out.println("登陆失败！程序退出");
            return;
        }

        String accountE, passwordE, accountG;

        System.out.println("登陆成功！");

        System.out.println("\n请输入一个用来发送电子邮件的邮箱帐号：");

        accountE = scanner.nextLine();

        System.out.println("请输入该邮箱帐号的登陆口令：");

        passwordE = scanner.nextLine();

        System.out.println("请输入一个接收电子邮箱的邮箱帐号：");

        accountG = scanner.nextLine();

        System.out.println("\n开始监听校园网成绩...");

        while (true) {

            ScoreResultBean scoreResultBean = new Gson().fromJson(Utils.doGetData(jSessionId), ScoreResultBean.class);

            List<Row> rows = scoreResultBean.getRows();

            if (Entry.lastQueryItemCount != -1 && Entry.lastQueryItemCount < rows.size()) {
                System.out.println("发现新成绩！！");

                StringBuilder content = new StringBuilder();
                for (Row row : rows) {
                    content.append(row);
                }

                // 发邮件
                Mail.Builder builder = new Mail.Builder();
                try {
                    builder.subject("收到一个新成绩")
                            .content(content.toString())
                            .account(accountE)
                            .password(passwordE)
                            .receipt(accountG)
                            .build()
                            .send();
                } catch (MessagingException e1) {
                    e1.printStackTrace();
                }

                Entry.lastQueryItemCount = rows.size();

            } else {
                System.out.println(new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()) + "：本次查询共查询到 " + rows.size() + " 条数据，无新数据；");
            }
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
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
