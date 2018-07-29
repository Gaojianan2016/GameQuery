package com.gjn.gamequery.utils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileUtils
 * Created by gjn
 * on 2018-07-29 3:15.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";
    public static void writeFile(File file, String string){
        FileOutputStream fos = null;

        File parentFile = new File(file.getParent());
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try{
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(string.getBytes());
            fos.flush();
            Log.e(TAG, "write " + file.getName() + " success.");
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {}
        }
    }
}
