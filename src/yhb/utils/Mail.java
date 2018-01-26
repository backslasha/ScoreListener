package yhb.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    private String subject;
    private String content;
    private String receipt;
    private String account;
    private String password;

    public static class Builder {
        private Mail mMail;

        public Builder() {
            this.mMail = new Mail();
        }

        public Builder subject(String subject) {
            this.mMail.subject = subject;
            return this;
        }
        public Builder content(String content) {
            this.mMail.content = content;
            return this;
        }
        public Builder receipt(String receipt) {
            this.mMail.receipt = receipt;
            return this;
        }
        public Builder account(String account) {
            this.mMail.account = account;
            return this;
        }
        public Builder password(String password) {
            this.mMail.password = password;
            return this;
        }

        public Mail build() {
            return mMail;
        }
    }

    public void send() throws MessagingException {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "false");

        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");

        // 如果登陆服务器要求 ssl，加上此两句
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");

        // 设置邮件服务器主机名，这里是腾讯的 smtp 服务器
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        msg.setSubject(subject);
        // 设置邮件正文内容
        msg.setText(content);
        // 设置发件人，不一定需要和真实邮箱地址相同
        msg.setFrom(new InternetAddress(account));

        Transport transport = session.getTransport();
        // 连接邮件服务器，填写可以登陆邮箱的帐号密码
        transport.connect(account, password);
        // 发送邮件，填写接收人邮箱地址
        transport.sendMessage(msg, new Address[]{new InternetAddress(receipt)});
        // 关闭连接
        transport.close();
    }



    public static void main(String[] args) throws MessagingException {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "false");

        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");

        // 如果登陆服务器要求 ssl，加上此两句
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");

        // 设置邮件服务器主机名，这里是腾讯的 smtp 服务器
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        msg.setSubject("JavaMail测试");
        // 设置邮件正文内容
        msg.setText("这是一封由JavaMail发送的邮件！");
        // 设置发件人，不一定需要和真实邮箱地址相同
        msg.setFrom(new InternetAddress("yaohaibiao2333@qq.com"));

        Transport transport = session.getTransport();
        // 连接邮件服务器，填写可以登陆邮箱的帐号密码
        transport.connect("yaohaibiao2333@qq.com", "ltsnqlwucfvjiedb");
        // 发送邮件，填写接收人邮箱地址
        transport.sendMessage(msg, new Address[]{new InternetAddress("1240562850@qq.com")});
        // 关闭连接
        transport.close();
    }
}
