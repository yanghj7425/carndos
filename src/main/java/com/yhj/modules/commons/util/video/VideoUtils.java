package com.yhj.modules.commons.util.video;

import com.alibaba.fastjson.JSONArray;
import com.yhj.modules.commons.util.video.pojo.VideoCommandParam;
import com.yhj.modules.commons.util.video.pojo.VideoImageCommandParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ffmpeg 视屏处理工具类
 */
public final class VideoUtils {
    private static Logger Log = LoggerFactory.getLogger(VideoUtils.class);

    // ffmepg 程序路径
    private static String ffmepg;

    static {
        String systemType = getSystemType();

        if (systemType.contains("windows")) {
            ffmepg = "D:/software/ffmpeg/bin/ffmpeg.exe";
        } else if (systemType.contains("linux")) {
            ffmepg = "/opt/ffmpeg/bin/ffmpeg";
        } else {
            throw new RuntimeException("不是 windows 或者 liunx 系统");
        }
    }

    private VideoUtils() {

    }


    /**
     * @param commandParam 视屏处理参数
     * @param threads      并发线程数量
     * @return boolean
     * @description 转变视屏格式为MP4 格式
     */
    public static boolean convertVideoToMp4(VideoCommandParam commandParam, String threads, VideoConsumer videoConsumer) {

        // 目标名称
        String targetName = generateTargetName();
        // 视屏转码命令参数
        List<String> videoCommand = new ArrayList<>();
        videoCommand.add(ffmepg);
        videoCommand.add("-i");
        videoCommand.add(commandParam.getSourceVideo());
        videoCommand.add("-threads");
        videoCommand.add(threads);
        videoCommand.add("-s");
        videoCommand.add(commandParam.getWidth() + "x" + commandParam.getHeight());
        videoCommand.add("-y");
        videoCommand.add(commandParam.getTargetFile() + targetName + ".mp4");
        try {
            ProcessBuilder builder = new ProcessBuilder(videoCommand);
            Process process = builder.start();
            parseErrorLogStream(process, videoConsumer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param commandParam 视屏处理参数
     * @param segmentCount 分段数量
     * @param threads      线程数量
     * @return true 成功，false 失败
     */
    public synchronized static boolean cutVideoToSegment(VideoCommandParam commandParam, double segmentCount, String threads, VideoConsumer videoConsumer) {

        if (segmentCount <= 0 || isValidFileName(commandParam.getTargetFile())) {
            return false;
        }
        // 总时长
        double videoTime = getVideoSecondTime(commandParam.getSourceVideo());
        if (videoTime <= 0) {
            return false;
        }
        // 分段时长
        double segmentSecond = videoTime / segmentCount;
        String targetName = generateTargetName();
        String videoName = commandParam.getTargetFile() + targetName;
        List<String> videoCommand = new ArrayList<>();
        videoCommand.add(ffmepg);
        videoCommand.add("-i");
        videoCommand.add(commandParam.getSourceVideo());
        videoCommand.add("-threads");
        videoCommand.add(threads);
        videoCommand.add("-ss");
        videoCommand.add("0");
        videoCommand.add("-t");
        videoCommand.add(String.valueOf(segmentSecond)); // 分段时长
        videoCommand.add("-s");
        videoCommand.add(commandParam.getWidth() + "x" + commandParam.getHeight());
        videoCommand.add("-y");
        videoCommand.add(videoName + ".mp4"); // 分段名称

        try {
            // 切片线程
            Thread segmentThread = new Thread(() -> {
                for (int i = 0; i < segmentCount; i++) {
                    videoCommand.set(6, String.valueOf(i * segmentSecond)); // 设置分段开始时间
                    videoCommand.set(12, videoName + i + ".mp4");
                    ProcessBuilder builder = new ProcessBuilder(videoCommand);
                    try {
                        Process process = builder.start();
                        parseErrorLogStream(process, videoConsumer);
                        process.waitFor();
                    } catch (Exception e) {
                        Log.error(e.getMessage(), e);
                    }
                }
            });
            segmentThread.start();
        } catch (Exception e) {
            Log.info(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * @param commandParam 图片参数
     * @return boolean
     * @description 切割视屏文件为 gif
     */
    public static boolean cutVideoToImage(VideoImageCommandParam commandParam, VideoConsumer videoConsumer) {
        // 目标名称
        String targetName = generateTargetName();
        List<String> gifCommand = new ArrayList<>();
        gifCommand.add(ffmepg);
        gifCommand.add("-i");
        gifCommand.add(commandParam.getSourceVideo());
        gifCommand.add("-ss");
        gifCommand.add(commandParam.getStartSecondTime());
        gifCommand.add("-t");
        gifCommand.add(commandParam.getDurationTime());
        gifCommand.add("-s");
        gifCommand.add(commandParam.getWidth() + "x" + commandParam.getHeight());
        gifCommand.add("-y");
        gifCommand.add(commandParam.getTargetFile() + targetName + "." + commandParam.getImageType());
        try {
            ProcessBuilder builder = new ProcessBuilder(gifCommand);
            Process process = builder.start();
            parseErrorLogStream(process, videoConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * @param sourceVideo 视屏原文件
     * @return double 时长 （秒）
     * @description 获取视屏时长
     */
    public static double getVideoSecondTime(String sourceVideo) {
        List<String> commands = new ArrayList<>();
        commands.add(ffmepg);
        commands.add("-i");
        commands.add(sourceVideo);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process process = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getSecondTimeLen(m.group(1));
                Log.info(sourceVideo + ",视频时长：" + time + ", 开始时间：" + m.group(2) + ",比特率：" + m.group(3) + "kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @param timeLen ffmpeg 获取的视频时长
     * @return 秒
     * @description 转变时长为秒
     */
    private static int getSecondTimeLen(String timeLen) {
        int second = 0;
        String strs[] = timeLen.split(":");
        if (strs[0].compareTo("0") > 0) {
            second += Integer.valueOf(strs[0]) * 60 * 60;//秒
        }
        if (strs[1].compareTo("0") > 0) {
            second += Integer.valueOf(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            second += Math.round(Float.valueOf(strs[2]));
        }
        return second;
    }


    /**
     * @return string
     * @description 根据时间生成一个目标名称
     */
    private static String generateTargetName() {
        Calendar c = Calendar.getInstance();
        return String.valueOf(c.getTimeInMillis()) + Math.round(Math.random() * 100000);
    }

    /**
     * @param process 进程对象
     * @description 读取错误日志
     */
    private synchronized static void parseErrorLogStream(Process process, VideoConsumer videoConsumer) {
        JSONArray pathJsonArray = new JSONArray();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        int lineNumber = 0;
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (Log.isDebugEnabled() || lineNumber % 10 == 0) {
                    Log.info(line);
                }
                if (line.contains("Output")) {
                    // 名称
                    String path = line.substring(line.indexOf("'") + 1, line.lastIndexOf("'"));
                    pathJsonArray.add(path);
                }
                lineNumber++;
            }
            if (!pathJsonArray.isEmpty()) {
                videoConsumer.consumer(pathJsonArray);
            }
            bufferedReader.close();
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * @return string
     * @description 获取系统类型
     * @link http://lopica.sourceforge.net/os.html
     */
    private static String getSystemType() {
        return System.getProperty("os.name").toLowerCase();
    }


    public static boolean isValidFileName(String fileName) {
        if (fileName == null || fileName.length() > 255) return false;
        else
            return fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
    }

}
