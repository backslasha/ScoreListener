/*
 * Created by JFormDesigner on Thu Jan 25 23:00:03 CST 2018
 */

package yhb.gui;

import com.google.gson.Gson;
import yhb.Entry;
import yhb.entity.Row;
import yhb.entity.ScoreResultBean;
import yhb.utils.Mail;
import yhb.utils.Utils;
import yhb.utils.VerifyCode;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
        textFieldAccountE = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        textFieldPasswordE = new JTextField();
        label6 = new JLabel();
        textFieldAccountG = new JTextField();

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

        //---- label4 ----
        label4.setText(bundle.getString("MainPanel.label4.text"));
        label4.setLabelFor(textFieldAccountE);

        //---- label5 ----
        label5.setText(bundle.getString("MainPanel.label5.text"));
        label5.setLabelFor(textFieldPasswordE);

        //---- label6 ----
        label6.setText(bundle.getString("MainPanel.label6.text"));
        label6.setLabelFor(textFieldAccountG);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2)
                                .addComponent(label3)
                                .addComponent(labelVerifyCode, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1))
                            .addGap(3, 3, 3)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(textFieldAccount, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                                    .addGap(63, 63, 63)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label4)
                                        .addComponent(label5))
                                    .addGap(41, 41, 41))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(textFieldVerifyCode, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label6)
                                    .addGap(50, 50, 50)))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textFieldAccountG, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldPasswordE, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldAccountE, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(116, 116, 116)
                            .addComponent(buttonLogin)))
                    .addContainerGap(57, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(textFieldAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label4)
                                                .addComponent(textFieldAccountE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label5)
                                                .addComponent(textFieldPasswordE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(textFieldVerifyCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label6)
                                                .addComponent(textFieldAccountG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1)
                                    .addGap(18, 18, 18)
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelVerifyCode, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))))
                    .addGap(18, 18, 18)
                    .addComponent(buttonLogin)
                    .addContainerGap(117, Short.MAX_VALUE))
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
    private JTextField textFieldAccountE;
    private JLabel label4;
    private JLabel label5;
    private JTextField textFieldPasswordE;
    private JLabel label6;
    private JTextField textFieldAccountG;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    private void buttonLoginActionPerformed(ActionEvent e) {

        String account = textFieldAccount.getText();
        String password = textFieldPassword.getText();
        String accountG = textFieldAccountG.getText();
        String accountE = textFieldAccountE.getText();
        String passwordE = textFieldPasswordE.getText();
        String verifyCode = textFieldVerifyCode.getText();

        if (account == null || password == null || account.equals("") || account.equals("")) {
            return;
        }

        if (Utils.doLogin(account, password, verifyCode, Entry.sessionId)) {

            this.dispose();

            while (true) {

                ScoreResultBean scoreResultBean = new Gson().fromJson(Utils.doGetData(Entry.sessionId), ScoreResultBean.class);

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
                        builder.subject("您有一个新成绩")
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
                    System.out.println("本次查询共查询到 " + rows.size() + " 条数据，无新数据；");
                }

                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        } else {
            setVerifyCode(VerifyCode.ask(Entry.sessionId));
        }

    }

    public void setVerifyCode(BufferedImage imageIcon) {
        labelVerifyCode.setIcon(new ImageIcon(imageIcon));
    }

}
