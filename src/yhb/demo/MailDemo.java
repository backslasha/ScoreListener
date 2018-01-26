package yhb.demo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailDemo {
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
        transport.sendMessage(msg, new Address[]{new InternetAddress("1127394004@qq.com")});
        // 关闭连接
        transport.close();
    }
}
