package com.boco.eoms.km.expert.util;

import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertPhotoConstants {

    public static String getPhysicalPath(String path, int i, ServletContext m_application) throws IOException {
        boolean m_denyPhysicalPath = false;

        String s1 = new String();
        String s2 = new String();

        boolean flag = false;

        String fileSeparator = System.getProperty("file.separator");

        if (path == null || path.equals(""))
            throw new IllegalArgumentException("There is no specified destination file (1140).");

        if (path.lastIndexOf("\\") >= 0) {
            s1 = path.substring(0, path.lastIndexOf("\\"));
            s2 = path.substring(path.lastIndexOf("\\") + 1);
        }

        if (path.lastIndexOf("/") >= 0) {
            s1 = path.substring(0, path.lastIndexOf("/"));
            s2 = path.substring(path.lastIndexOf("/") + 1);
        }

        s1 = (s1.length() != 0) ? s1 : "/";

        java.io.File file = new java.io.File(s1);
        if (file.exists())
            flag = true;

        if (i == 0) {
            if (isVirtual(m_application, s1)) {
                s1 = m_application.getRealPath(s1);
                if (s1.endsWith(fileSeparator))
                    s1 = s1 + s2;
                else
                    s1 = s1 + fileSeparator + s2;
                return s1;
            }
            if (flag) {
                if (m_denyPhysicalPath) {
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                }
                return path;
            }
            throw new IllegalArgumentException("This path does not exist (1135).");
        }

        if (i == 1) {
            if (isVirtual(m_application, s1)) {
                s1 = m_application.getRealPath(s1);
                if (s1.endsWith(fileSeparator))
                    s1 = s1 + s2;
                else
                    s1 = s1 + fileSeparator + s2;
                return s1;
            }
            if (flag) {
                throw new IllegalArgumentException("The path is not a virtual path.");
            }
            throw new IllegalArgumentException("This path does not exist (1135).");
        }

        if (i == 2) {
            if (flag) {
                if (m_denyPhysicalPath) {
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                }
                return path;
            }
            if (isVirtual(m_application, s1)) {
                throw new IllegalArgumentException("The path is not a physical path.");
            }
            throw new IllegalArgumentException("This path does not exist (1135).");
        }

        return null;
    }

    private static boolean isVirtual(ServletContext m_application, String s) {
        if (m_application.getRealPath(s) != null) {
            java.io.File file = new java.io.File(m_application.getRealPath(s));
            return file.exists();
        }
        return false;
    }

    private static BufferedImage makeThumbnail(Image img, int width, int height) {
        BufferedImage tag = new BufferedImage(width, height, 1);
        Graphics g = tag.getGraphics();
        g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);
        g.dispose();
        return tag;
    }

    private static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile) throws IOException {
        String fileName = subImageFile.getName();
        String formatName = fileName.substring(fileName.lastIndexOf(46) + 1);
        BufferedImage subImage = new BufferedImage(subImageBounds.width, subImageBounds.height, 1);
        Graphics g = subImage.getGraphics();
        if ((subImageBounds.width > image.getWidth()) || (subImageBounds.height > image.getHeight())) {
            int left = subImageBounds.x;
            int top = subImageBounds.y;
            if (image.getWidth() < subImageBounds.width)
                left = (subImageBounds.width - image.getWidth()) / 2;
            if (image.getHeight() < subImageBounds.height)
                top = (subImageBounds.height - image.getHeight()) / 2;
            g.setColor(Color.white);
            g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
            g.drawImage(image, left, top, null);
            ImageIO.write(image, formatName, subImageFile);
            //System.out.println("if is running left:" + left + " top: " + top);
        } else {
            g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
                    subImageBounds.width, subImageBounds.height), 0, 0, null);
            //System.out.println("else is running");
        }
        g.dispose();
        ImageIO.write(subImage, formatName, subImageFile);
    }

    public static void cut(String srcImageFile, String descDir, int width, int height, Rectangle rect) throws IOException {
        Image image = ImageIO.read(new File(srcImageFile));
        BufferedImage bImage = makeThumbnail(image, width, height);
        saveSubImage(bImage, rect, new File(descDir));
    }

    public static void cut(File srcImageFile, File descDir, int width, int height, Rectangle rect) throws IOException {
        Image image = ImageIO.read(srcImageFile);
        BufferedImage bImage = makeThumbnail(image, width, height);
        saveSubImage(bImage, rect, descDir);
    }
}