package yhb.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class VerifyCode {
    public static BufferedImage ask(String sessionId) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://222.200.98.147/yzm?d=" + new Date().getTime());
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Cookie", sessionId);

            BufferedImage imageIcon = ImageIO.read(connection.getInputStream());

            if (imageIcon != null) {
                return imageIcon;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
}
