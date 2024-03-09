package me.trouper.ultracore.server.util;

import me.trouper.ultracore.UltraCore;

import java.io.File;
public class FileUtils {
    public static boolean folderExists(String folderName) {
        File folder = new File(UltraCore.getInstance().getDataFolder(), folderName);
        return folder.exists() && folder.isDirectory();
    }
    public static void createFolder(String folderName) {
        File folder = new File(UltraCore.getInstance().getDataFolder(), folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
