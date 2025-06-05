package Report;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class AllureTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String path = ScreenRecorderUtil.getRecordingPath();
        AllureUtils.attachRecording(path);
    }
}
