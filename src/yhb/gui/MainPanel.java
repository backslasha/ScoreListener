/*
 * Created by JFormDesigner on Thu Jan 25 23:00:03 CST 2018
 */

package yhb.gui;

import com.google.gson.Gson;
import yhb.Main;
import yhb.entity.LoginBean;
import yhb.entity.Row;
import yhb.entity.ScoreResultBean;
import yhb.utils.Mail;
import yhb.utils.VerifyCode;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author name
 */
public class MainPanel extends JFrame {
    public MainPanel() {
        initComponents();
        initMyStuff();
    }

    private void initMyStuff() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("yhb.gui.values");
        label1 = new JLabel();
        textFieldAccount = new JTextField();
        label2 = new JLabel();
        textFieldPassword = new JTextField();
        label3 = new JLabel();
        labelVerifyCode = new JLabel();
        buttonLogin = new JButton();
        textFieldVerifyCode = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText(bundle.getString("MainPanel.label1.text"));
        label1.setLabelFor(textFieldAccount);

        //---- textFieldAccount ----
        textFieldAccount.setText(bundle.getString("MainPanel.textFieldAccount.text"));

        //---- label2 ----
        label2.setText(bundle.getString("MainPanel.label2.text"));
        label2.setLabelFor(textFieldPassword);

        //---- textFieldPassword ----
        textFieldPassword.setText(bundle.getString("MainPanel.textFieldPassword.text"));

        //---- label3 ----
        label3.setText(bundle.getString("MainPanel.label3.text"));

        //---- buttonLogin ----
        buttonLogin.setText(bundle.getString("MainPanel.buttonLogin.text"));
        buttonLogin.addActionListener(e -> buttonLoginActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup()
                                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(label1)
                                                        .addGap(105, 105, 105))
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                        .addGroup(contentPaneLayout.createParallelGroup()
                                                                .addComponent(label2)
                                                                .addComponent(label3))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(labelVerifyCode, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)))
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(buttonLogin)
                                                .addGap(94, 94, 94))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(textFieldAccount, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textFieldVerifyCode, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                                                .addGap(59, 59, 59))))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelVerifyCode, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(textFieldAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label3))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(textFieldVerifyCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonLogin)
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textFieldAccount;
    private JLabel label2;
    private JTextField textFieldPassword;
    private JLabel label3;
    private JLabel labelVerifyCode;
    private JButton buttonLogin;
    private JTextField textFieldVerifyCode;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    private void buttonLoginActionPerformed(ActionEvent e) {

        if (Main.running) {
            Main.running = false;
            buttonLogin.setText("登陆并监听");
            return;
        }

        String account = textFieldAccount.getText();
        String password = textFieldPassword.getText();
        String verifyCode = textFieldVerifyCode.getText();

        if (account == null || password == null || account.equals("") || account.equals("")) {
            return;
        }

        if (doLogin(account, password, verifyCode)) {

            Main.running = true;
            buttonLogin.setText("监视中...");
            System.out.println("开始监视...");
            this.dispose();

            while (Main.running) {

                ScoreResultBean scoreResultBean = new Gson().fromJson(queryScore(), ScoreResultBean.class);

                List<Row> rows = scoreResultBean.getRows();

                if (Main.lastQueryItemCount != -1 && Main.lastQueryItemCount < rows.size()) {
                    System.out.println("发现新成绩！！");

                    StringBuilder content = new StringBuilder();
                    for (Row row : rows) {
                        content.append(row);
                    }

                    // 发邮件
                    Mail.Builder builder = new Mail.Builder();
                    try {
                        builder.subject("您有一个新成绩")
                                .content(content.toString())
                                .account("yaohaibiao@qq.com")
                                .password("ltsnqlwucfvjiedb")
                                .receipt("1127394004@qq.com")
                                .build()
                                .send();
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    }

                    Main.lastQueryItemCount = rows.size();

                } else {
                    System.out.println("本次查询共查询到 " + rows.size() + " 条数据，无新数据；");
                }

                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        } else {
            setVerifyCode(VerifyCode.ask(Main.sessionId));
        }

    }

    private String queryScore() {
        String rawScore = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://222.200.98.147/xskccjxx!getDataList.action");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", Main.sessionId);

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

    private boolean doLogin(String account, String password, String verifyCode) {
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL("http://222.200.98.147/new/login");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", Main.sessionId);

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

    private String readInputStream(InputStream inputStream) {
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

    public void setVerifyCode(BufferedImage imageIcon) {
        labelVerifyCode.setIcon(new ImageIcon(imageIcon));
    }

}
