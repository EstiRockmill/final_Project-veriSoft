package Report;

import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureUtils {

    @Attachment(value = "Recording", type = "video/avi")
    public static byte[] attachRecording(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String message) {
        return message;
    }

}
