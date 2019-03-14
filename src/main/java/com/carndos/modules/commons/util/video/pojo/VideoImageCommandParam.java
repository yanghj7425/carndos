package com.carndos.modules.commons.util.video.pojo;

/**
 * 截图命令可变参数
 * <p>
 * 若图片类型 imageType：jpg、jpeg、bmp、png、gif 除这几种外 抛出 RuntimeException
 */
public class VideoImageCommandParam extends VideoCommandParam {

    // 开始时间：秒
    private String startSecondTime;

    // 动画持续时间时间
    private String durationTime;

    // 图片类型
    private String imageType;


    /**
     * 若图片类型 imageType：jpg、jpeg、bmp、png、gif 除这几种外 抛出 RuntimeException
     * 如果 durationTime 默认6秒
     *
     * @param width           宽：像素
     * @param height          高：像素
     * @param sourceVideo     源视屏文件
     * @param targetFile      目标文件
     * @param startSecondTime 开始时间：秒
     * @param durationTime    持续时间：秒，小于 8
     * @param imageType       图片类型
     */
    public VideoImageCommandParam(String width, String height, String sourceVideo, String targetFile, String startSecondTime, String durationTime, String imageType) {
        super(width, height, sourceVideo, targetFile);
        if (!isValidateImage(imageType)) {
            throw new RuntimeException("传入图片类型错误");
        }

        this.startSecondTime = startSecondTime.trim();
        this.durationTime = Double.valueOf(durationTime.trim()) <= 0 ? "6" : durationTime.trim();
        this.imageType = imageType.trim();
    }


    public String getStartSecondTime() {
        return startSecondTime;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public String getImageType() {
        return imageType;
    }

    private boolean isValidateImage(String imageType) {
        return imageType.equals("jpg") || imageType.equals("jpeg") || imageType.equals("bmp") || imageType.equals("png") || imageType.equals("gif");
    }

}
