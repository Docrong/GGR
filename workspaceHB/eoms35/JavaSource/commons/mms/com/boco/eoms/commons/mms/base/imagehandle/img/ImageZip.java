package com.boco.eoms.commons.mms.base.imagehandle.img;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 不好用啊 先放在这里了 转换的图片不正确 不能打开
 * @author Administrator
 *
 */
public class ImageZip {
	
	public static void main(String[] args)
	{
		int i = SmallPic(400,300,"D:/","BarChart.gif" ,"D:/" ,"BarChart-zip.gif","gif",0,0);
		System.out.println(i);
	}
	
	/**
	  * 图片转换功能
	  *
	  * @param width
	  *            int 目标图片宽度
	  * @param height
	  *            int 目标图片高度
	  * @param fromDir
	  *            String 源文件夹
	  * @param fromFile
	  *            String 源文件名
	  * @param toDir
	  *            String 目标文件夹
	  * @param toSmallFile
	  *            String 目标文件名
	  * @param chanageFormat
	  *            String JPG PNG BMP GIF
	  * @param ratioType
	  *            int 为0时受比例限制,为1时不受比例限制强行按给定尺寸进行转换
	  * @param isFromFileDel
	  *            int 0表示不删除原文件,1表示删除原文件;
	  * @return int 1成功0失败
	  */
	 public static int SmallPic(int width, int height, String fromDir, String fromFile,
	   String toDir, String toSmallFile, String chanageFormat,
	   int ratioType, int isFromFileDel) {
	  try {
	   String Imagetype = chanageFormat;
	   File fF = new File(fromDir, fromFile);
	   File tF = new File(toDir, toSmallFile);
	   BufferedImage bi = ImageIO.read(fF);
	   int pwidth = (int) (bi.getWidth());
	   int pheight = (int) (bi.getHeight());
	   double ratio = 1.0000;
	   double ratioX = 1.0000;
	   double ratioY = 1.0000;
	   Image Itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
	   if (ratioType != 1) {
	    if ((pwidth > width) || (pheight > height)) {
	     double ratioO = 1.0000;
	     double ratioN = 1.0000;
	     ratioO = (double) height / width;
	     ratioN = (double) pheight / pwidth;
	     double Nheight = 0;
	     double Nwidth = 0;
	     if (ratioN > ratioO) {
	      // 超高的
	      Nheight = height;
	      Nwidth = height / ratioN;
	     } else {
	      // 超宽
	      Nwidth = width;
	      Nheight = width * ratioN;
	     }
	     ratioX = Nwidth / pwidth;
	     ratioY = Nheight / pheight;
	    }
	   } else {
	    ratioX = (double) width / pwidth;
	    ratioY = (double) height / pheight;
	    // ratioX=0.55;
	    // ratioY=0.43;
	   }
	   AffineTransformOp op = new AffineTransformOp(AffineTransform
	     .getScaleInstance(ratioX, ratioY), null);
	   Itemp = op.filter(bi, null);
	   ImageIO.write((BufferedImage) Itemp, Imagetype, tF);
	   int fFlen = (int) fF.length();
	   if (isFromFileDel == 1) {
	    fF.delete();
	   }
	   return 1;
	  } catch (Exception e) {
	   /*
	    * Log log = Log.getInstance();
	    * log.Addlog("Mobile转换图片出错(mobileImage.chanageMobPic.SmallPic)：" +
	    * e.getMessage());
	    */
	   e.printStackTrace();
	   return 0;
	  }
	 }
}
