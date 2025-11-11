package utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    public static void set(ExtentTest test) {
        currentTest.set(test);
    }

    public static void clear() {
        currentTest.remove();
    }

    public static ExtentTest get() {
        return currentTest.get();
    }

    public static void info(String message) {
        ExtentTest test = get();
        if(test != null) {
            test.info(message);
        }
    }

    public static void pass(String message) {
        ExtentTest test = get();
        if(test != null) {
            test.pass(message);
        }
    }
}
