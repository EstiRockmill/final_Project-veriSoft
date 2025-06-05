import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecorderUtil extends ScreenRecorder {

    public static ScreenRecorder screenRecorder;
    public static String recordingName;
    private static long startTime;
    private static long endTime;

    public ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle captureArea)
            throws IOException, AWTException {
        super(cfg, captureArea,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null
        );
    }


    public static void startRecording(String testName) throws Exception {
        File file = new File("recordings");
        if (!file.exists()) file.mkdirs();
        recordingName = testName + "_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Rectangle captureArea = gc.getBounds();

        screenRecorder = new ScreenRecorderUtil(gc, captureArea) {
            @Override
            protected File createMovieFile(Format fileFormat) throws IOException {
                return new File(file, recordingName + ".avi");
            }
        };
        screenRecorder.start();
        startTime = System.currentTimeMillis();

    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
        endTime = System.currentTimeMillis();

    }

    public static String getRecordingPath() {
        return "recordings/" + recordingName + ".avi";
    }
    public static long getRecordingDurationMillis() {
        return endTime - startTime;
    }

}
