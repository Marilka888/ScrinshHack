import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUploader extends Thread{

    private DbxClientV2 client;
    private BufferedImage image;
    private DateTimeFormatter formatter;

    public ScreenshotUploader(DbxClientV2 client, BufferedImage image){
        this.client = client;
        this.image = image;
        formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    }

    @Override
    public void run(){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            byte[] bytes = os.toByteArray();
            InputStream in = new ByteArrayInputStream(bytes);

            LocalDateTime now = LocalDateTime.now();
            String fileName = formatter.format(now);
            client.files().uploadBuilder("/" + fileName + ".png")
                    .uploadAndFinish(in);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

