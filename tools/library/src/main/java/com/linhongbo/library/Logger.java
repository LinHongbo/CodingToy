package com.linhongbo.library;

import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Logger {

    /**
     * 日志打印级别
     */
    public enum LOG_LEVEL {
        ALL,
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        NONE
    }

    public static String TAG = "Logger";
    public static LOG_LEVEL mLevel = LOG_LEVEL.ALL;
    private static final Set<String> mBlackList = new HashSet<>();

    public static void init(String tag, List<String> array){
        TAG = tag;
        mBlackList.clear();
        mBlackList.addAll(array);
    }

    public static void v(String msg) {
        log(LOG_LEVEL.VERBOSE, null, msg);
    }

    public static void d(String msg) {
        log(LOG_LEVEL.DEBUG, null, msg);
    }

    public static void i(String msg) {
        log(LOG_LEVEL.INFO, null, msg);
    }

    public static void w(String msg) {
        log(LOG_LEVEL.WARN, null, msg);
    }

    public static void e(String msg) {
        log(LOG_LEVEL.ERROR, null, msg);
    }

    public static void v(String tag, String msg) {
        log(LOG_LEVEL.VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        log(LOG_LEVEL.DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        log(LOG_LEVEL.INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        log(LOG_LEVEL.WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        log(LOG_LEVEL.ERROR, tag, msg);
    }

    public static void e(Throwable e) {
        if (e == null) {
            return;
        }
        e(Log.getStackTraceString(e));
    }

    public static void log(LOG_LEVEL level, String tag, String msg) {
        log(level, tag, msg, true);
    }

    public static void log(LOG_LEVEL level, String tag, String msg, boolean needPosition) {
        if (level.ordinal() < mLevel.ordinal()) {
            return;
        }
        if (tag == null) {
            tag = TAG;
        } else if (!tag.contains(TAG)) {
            tag = TAG + "_" + tag;
        }
        if (needPosition) {
            msg = msgWithPosition(msg);
        }
        switch (level) {
            case ALL:
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            case NONE:
                break;
        }
    }

    private static String msgWithPosition(String msg) {
        String position = getPosition();
        if (msg != null && msg.length() < 100) {
            msg = msg + " " + position;
        } else {
            msg = position + "\n" + msg;
        }
        return msg;
    }

    /**
     * 获取调用位置
     *
     * @return 日志出现的行数
     */
    private static String getPosition() {
        if (mLevel != LOG_LEVEL.NONE) {
            StringBuilder buffer = new StringBuilder();

            int printTrackIndex = 4;
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[printTrackIndex];


            int maxSize = Thread.currentThread().getStackTrace().length;
            while (mBlackList.contains(stackTraceElement.getFileName())
                    && printTrackIndex < maxSize) {
                printTrackIndex++;
                stackTraceElement = Thread.currentThread().getStackTrace()[printTrackIndex];
            }
            buffer.append("(");
            buffer.append(stackTraceElement.getFileName());
            buffer.append(":");
            buffer.append(stackTraceElement.getLineNumber());
            buffer.append(")");
            return buffer.toString();
        }
        return null;
    }

    /**
     * 打印程序调用堆栈信息
     */
    public static void trackInfo() {
        if (mLevel != LOG_LEVEL.NONE) {
            StackTraceElement[] tracks = Thread.currentThread().getStackTrace();
            String trace = " TRACE  ";
            Log.i(TAG, trace + "-----------start---------------");
            StringBuilder buffer = new StringBuilder();
            for (StackTraceElement track : tracks) {
                buffer.append(track.getClassName());
                buffer.append(":");
                buffer.append(track.getMethodName());
                buffer.append("(");
                buffer.append(track.getFileName());
                buffer.append(":");
                buffer.append(track.getLineNumber());
                buffer.append(")\n");
            }
            Log.i(TAG, trace + buffer.toString());
            Log.i(TAG, trace + "-----------end---------------");
        }
    }
}
