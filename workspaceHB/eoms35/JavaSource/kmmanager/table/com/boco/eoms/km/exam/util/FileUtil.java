package com.boco.eoms.km.exam.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import org.apache.struts.upload.FormFile;

public class FileUtil {

    private static Random random = new Random();

    /**
     * * 创建空白文件
     *
     * @param fileName 文件名
     * @param dir      保存文件的目录
     * @return
     */

    private static File createNewFile(String fileName, String dir) {

        File dirs = new File(dir);

        // 看文件夹是否存在，如果不存在新建目录

        if (!dirs.exists())

            dirs.mkdirs();

        // 拼凑文件完成路径

        File file = new File(dir + File.separator + fileName);

        try {

            // 判断是否有同名名字，如果有同名文件加随机数改变文件名

            while (file.exists()) {

                int ran = getRandomNumber();

                String prefix = getFileNamePrefix(fileName);

                String suffix = getFileNameSuffix(fileName);

                String name = prefix + ran + "." + suffix;

                file = new File(dir + File.separator + name);

            }

            file.createNewFile();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return file;

    }

    /**
     * 获得随机数
     *
     * @return
     */

    public static int getRandomNumber() {

        return Math.abs(random.nextInt());

    }

    /**
     * 分割文件名 如a.txt 返回 a
     *
     * @param fileName
     * @return
     */

    private static String getFileNamePrefix(String fileName) {

        int dot = fileName.lastIndexOf(".");

        return fileName.substring(0, dot);

    }

    /**
     * 获得文件后缀
     *
     * @param fileName
     * @return
     */

    private static String getFileNameSuffix(String fileName) {

        int dot = fileName.lastIndexOf(".");

        return fileName.substring(dot + 1);

    }

    /**
     * 上传文件
     *
     * @param file
     * @param dir
     * @return
     */

    public static String uploadFile(FormFile file, String dir) {

        // 获得文件名

        String fileName = file.getFileName();
        fileName = new Date().getTime() + getRandomNumber()
                + fileName.substring(fileName.lastIndexOf("."));

        InputStream in = null;

        OutputStream out = null;

        try {

            in = new BufferedInputStream(file.getInputStream());// 构造输入流

            File f = createNewFile(fileName, dir);

            out = new BufferedOutputStream(new FileOutputStream(f));// 构造文件输出流

            byte[] buffered = new byte[8192];// 读入缓存

            int size = 0;// 一次读到的真实大小

            while ((size = in.read(buffered, 0, 8192)) != -1) {

                out.write(buffered, 0, size);

            }

            out.flush();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (in != null)
                    in.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

            try {

                if (out != null)
                    out.close();

            } catch (IOException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

        return fileName;

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(getRandomNumber());
    }
}
