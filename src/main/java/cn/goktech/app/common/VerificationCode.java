package cn.goktech.app.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Jian Yueting
 */
public class VerificationCode {
    private int weight = 70;
    private int height = 30;
    private String text;
    private Random random = new SecureRandom();
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    // 去除1,L,l,I,i,O，0易混淆的字符
    private static final String CODES = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    private Color randomColor() {
        int r = this.random.nextInt(150);
        int g = this.random.nextInt(150);
        int b = this.random.nextInt(150);
        return new Color(r, g, b);
    }

    private Color randColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private Font randomFont() {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int size = random.nextInt(5) + 24;
        return new Font(fontName, Font.ITALIC, size);
    }

    private char randomChar() {
        int index = random.nextInt(CODES.length());
        return CODES.charAt(index);
    }

    private void drawDisturbLines(BufferedImage image) {
        int num = 155;
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x = random.nextInt(weight);
            int y = random.nextInt(height);
            int xl = random.nextInt(weight);
            int yl = random.nextInt(height);
            g.setColor(randColor(160, 200));
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(randColor(200, 250));
        g.fillRect(0, 0, weight, height);
        return image;
    }

    /**
     * 获取验证码图片的方法
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        drawDisturbLines(image);
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * weight / 4;
            g.setFont(randomFont());
            g.setColor(randomColor());
            g.drawString(s, x, height - 5);
        }
        this.text = sb.toString();
        return image;
    }


    /**
     * 获取验证码文本的方法
     */
    public String getText() {
        return text;
    }
}
