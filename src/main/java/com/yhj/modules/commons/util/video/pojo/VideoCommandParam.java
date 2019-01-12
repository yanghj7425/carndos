package com.yhj.modules.commons.util.video.pojo;


public class VideoCommandParam {

    // 宽：默认值
    private String width;

    // 高：默认值
    private String height;

    // 视屏源文件：绝对路径
    private String sourceVideo;

    // 目标文件路径：绝对路径
    private String targetFile;

    /**
     * @param width       宽：像素
     * @param height      高：像素
     * @param sourceVideo 视屏源文件： 绝对路径
     * @param targetFile  目标路径： 绝对路径
     */
    public VideoCommandParam(String width, String height, String sourceVideo, String targetFile) {
        this.width = width.trim();
        this.height = height.trim();
        this.sourceVideo = sourceVideo.trim();
        this.targetFile = targetFile.trim();
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getSourceVideo() {
        return sourceVideo;
    }

    public String getTargetFile() {
        return targetFile;
    }

    @Override
    public String toString() {
        return "VideoCommandParam{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", sourceVideo='" + sourceVideo + '\'' +
                ", targetFile='" + targetFile + '\'' +
                '}';
    }
}
