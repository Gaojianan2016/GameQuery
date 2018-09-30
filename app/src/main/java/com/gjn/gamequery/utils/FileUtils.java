package com.gjn.gamequery.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileUtils
 * Created by gjn
 * on 2018-07-29 3:15.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void openFile(Context context, String filePath) {
        File file = new File(filePath);
        openFile(context, file);
    }

    public static void openFile(Context context, File file) {
        String mimeType = getTypeFromSuffix(file);
        Uri uri = Uri.fromFile(file);
        if (mimeType != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, mimeType);
            context.startActivity(intent);
        }
    }

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

    public static boolean writeFile(File file, InputStream stream){
        return writeFile(file, stream, 0, null);
    }

    public static boolean writeFile(File file, InputStream stream, int total, OnWritingListener listener){
        byte[] buf = new byte[20480];
        int len = 0;
        int progress = 0;
        FileOutputStream fos = null;
        if (stream == null) {
            Log.e(TAG, "InputStream is null.");
            return false;
        }
        File parentFile = new File(file.getParent());
        if (!parentFile.exists()) {
            Log.d(TAG, "create file path: " + parentFile.getPath());
            parentFile.mkdirs();
        }
        if (file.exists()) {
            Log.d(TAG, "delete old file.");
            file.delete();
        }
        try {
            if (!file.exists()) {
                Log.d(TAG, "create file: " + file.getName());
                file.createNewFile();
            }
            if (listener != null) {
                listener.onStart();
            }
            fos = new FileOutputStream(file);
            if ((len = stream.read(buf)) != -1) {
                fos.write(buf, 0, len);
                progress += len;
                if (listener != null) {
                    listener.onWriting(progress, total);
                }
            }
            fos.flush();
            if (listener != null) {
                listener.onEnd(true);
            }
            Log.d(TAG, "write success file: " + file.getName());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                stream.close();
                if (fos != null) {
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if (listener != null) {
            listener.onEnd(false);
        }
        return false;
    }

    public static String getSuffix(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    public static String getTypeFromSuffix(File file) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(getSuffix(file));
    }

    public static void deleteFile(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            String[] children = directory.list();
            for (String child : children) {
                deleteFile(new File(directory, child));
            }
        }
        directory.delete();
    }

    public static void deleteFile(String directoryPath) {
        File directory = new File(directoryPath);
        deleteFile(directory);
    }

    public static long getFileSize(String directoryPath) {
        File directory = new File(directoryPath);
        return getFileSize(directory);
    }

    public static long getFileSize(File directory) {
        long size = 0;

        try {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    size += getFileSize(file);
                } else {
                    size += file.length();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "get file size error", e);
        }
        return size;
    }

    public interface OnWritingListener{
        void onStart();
        void onWriting(int progress, int total);
        void onEnd(boolean result);
    }
}
