package com.zym.fastplatform.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
@Slf4j
@Component
public class PictureUtil implements ApplicationContextAware {
    private static Environment environment;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        environment = applicationContext.getEnvironment();
    }
    private static boolean isPgsqlEnvironment() {
        if (environment == null) {
            log.warn("Environment not initialized, defaulting to pgsql font");
            return true;
        }
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if ("pgsql".equals(profile)) {
                return true;
            }
        }
        return false;
    }
    private static Font loadCustomFont(int fontSize) {
        try {
            // 容器内字体文件路径（和Dockerfile拷贝的路径一致）
            String fontPath = "/usr/share/fonts/custom/SimHei.ttf";
            // 读取字体文件
            InputStream fontStream = Files.newInputStream(Paths.get(fontPath));
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            // 设置字体大小和样式（36号加粗）
            return baseFont.deriveFont(Font.BOLD, 36f);
        } catch (Exception e) {
            // 兜底方案：使用系统默认字体
            log.error("加载自定义字体失败，使用兜底字体：" + e.getMessage());
            return new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        }
    }
    private static Font getFont(int fontSize) {
        if (isPgsqlEnvironment()) {
            return new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        } else {
            return loadCustomFont(fontSize);
        }
    }

    public static String generateLogoFromText(String text) {
        // 图片参数
        int width = 120;
        int height = 120;
        int fontSize = 36;

        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 设置背景色
        g2d.setColor(new Color(173,216,230));
        g2d.fillRect(0, 0, width, height);

        // 设置文字样式
        g2d.setColor(Color.DARK_GRAY);
        // 使用Java内置的逻辑字体，确保在所有系统中都可用
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        g2d.setFont(font);

        // 计算文字位置（居中）
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent() - metrics.getDescent();
        int x = (width - textWidth) / 2;
        int y = (height + textHeight) / 2;

        // 绘制文字
        g2d.drawString(text, x, y);

        // 释放资源
        g2d.dispose();
        // 确保 img 文件夹存在
        try {
            String imgDirPath = System.getProperty("user.dir") + "\\img";
            File imgDir = new File(imgDirPath);
            if (!imgDir.exists()) {
                imgDir.mkdirs();
            }
            // 生成唯一的文件名
            String fileName = "logo_" + System.currentTimeMillis() + ".png";
            String filePath = imgDirPath + "\\" + fileName;
            // 保存图片
            File outputFile = new File(filePath);
            ImageIO.write(image, "png", outputFile);

            // 返回相对路径
            return "/img/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] generateLogoBytes(String text) {
        // 图片参数
        int width = 120;
        int height = 120;
        int fontSize = 36; // 增大字体大小

        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 设置背景色为浅蓝色
        g2d.setColor(new Color(173, 216, 230)); // 浅蓝色
        g2d.fillRect(0, 0, width, height);

        // 设置文字样式
        g2d.setColor(Color.DARK_GRAY); // 深色字体以提高对比度
        Font font = getFont(fontSize);
        g2d.setFont(font);

        // 计算文字位置（居中）
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent() - metrics.getDescent();
        int x = (width - textWidth) / 2;
        int y = (height + textHeight) / 2;

        // 绘制文字
        g2d.drawString(text, x, y);

        // 释放资源
        g2d.dispose();

        // 转换为字节数组
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}