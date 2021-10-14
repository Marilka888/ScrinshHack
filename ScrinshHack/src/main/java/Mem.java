import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.FileInputStream;
import java.io.InputStream;

    public class Mem {

        private static final String ACCESS_TOKEN = "TOKEN";//я же не дурочка свой токен оставлять

        public static void main(String[] args) throws DbxException {

            // Create Dropbox client
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            //индекс для номера дубликата (чтобы юзать вечный цикл добавления фото)
            long i = 0;

            // Upload infinity "SecretInfo.png" to Dropbox
            for (;;) {
                try {
                    i += 1;
                    InputStream in = new FileInputStream("./src/main/resources/Mem.png");
                    client.files().uploadBuilder("/SecretInfo" + i + ".png")
                            .uploadAndFinish(in);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

               //стартуем поток, который берёт паузу в 5 сек
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
