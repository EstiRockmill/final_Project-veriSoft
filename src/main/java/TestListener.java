import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG listener that integrates screen recording for each test.
 * Starts recording when a test begins and stops it when it ends, attaching results to Allure reports.
 */
public class TestListener implements ITestListener {

    /**
     * Called when a test starts.
     * Starts screen recording using the test method name.
     *
     * @param result Test result object containing test method information.
     */
    @Override
    public void onTestStart(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            ScreenRecorderUtil.startRecording(testName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when a test passes.
     * Stops recording and logs success message with duration.
     *
     * @param result Test result object.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        stopAndAttachRecordingInfo(true);
    }

    /**
     * Called when a test fails.
     * Stops recording and attaches the recording to Allure.
     *
     * @param result Test result object.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        stopAndAttachRecordingInfo(false);
    }

    /**
     * Called when a test is skipped.
     * Stops screen recording if running.
     *
     * @param result Test result object.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        stopRecording();
    }

    /**
     * Called when a test fails but is within success percentage.
     *
     * @param result Test result object.
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        stopRecording();
    }

    /**
     * Called before any test in the suite starts.
     *
     * @param context Test context object.
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println("===> התחלת סוויטה: " + context.getName());
    }

    /**
     * Called after all tests in the suite are finished.
     *
     * @param context Test context object.
     */
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("===> סיום סוויטה: " + context.getName());
    }

    /**
     * Helper method to stop the screen recording safely.
     */
    private void stopRecording() {
        try {
            ScreenRecorderUtil.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops recording and either attaches a success message or the video to Allure.
     *
     * @param isSuccess True if test passed, false if failed.
     */
    private void stopAndAttachRecordingInfo(boolean isSuccess) {
        try {
            ScreenRecorderUtil.stopRecording();

            if (isSuccess) {
                long durationMs = ScreenRecorderUtil.getRecordingDurationMillis();
                long seconds = durationMs / 1000;
                String message = "✅ הטסט הצליח. אורך ההקלטה: " + seconds + " שניות.";
                System.out.println(message);
                AllureUtils.attachText("Recording Info", message);
            } else {
                String path = ScreenRecorderUtil.getRecordingPath();
                AllureUtils.attachRecording(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
