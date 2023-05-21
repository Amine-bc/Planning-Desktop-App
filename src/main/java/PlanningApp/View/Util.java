package PlanningApp.View;

import java.io.File;

public interface Util {

    public static String getPlatformIndependentFilePath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows operating system
            return "C:\\Users\\admin\\IdeaProjects\\PlanningAppJavaProject\\src\\main\\java\\PlanningApp\\Files\\users.ser";
        } else {
            // Linux and other Unix-like operating systems
            return "/home/am1n3/Projects/JavaPooTp/src/main/java/PlanningApp/Files/users.ser";
        }
}
}
