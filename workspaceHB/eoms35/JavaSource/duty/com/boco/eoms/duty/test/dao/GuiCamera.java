package com.boco.eoms.duty.test.dao;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 1:31:17 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class GuiCamera {
	private String fileName; // 文件的前缀

	private String defaultName = "GuiCamera";

	static int serialNum = 0;

	private String imageFormat; // 图像文件的格式

	private String defaultImageFormat = "png";

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	/***************************************************************************
	 * 默认的文件前缀为GuiCamera，文件格式为PNG格式 The default construct will use the default
	 * Image file surname "GuiCamera", and default image format "png"
	 **************************************************************************/
	public GuiCamera() {
		fileName = defaultName;
		imageFormat = defaultImageFormat;

	}

	/***************************************************************************
	 * @param s
	 *            the surname of the snapshot file
	 * @param format
	 *            the format of the image file, it can be "jpg" or "png"
	 *            本构造支持JPG和PNG文件的存储
	 **************************************************************************/
	public GuiCamera(String s, String format) {

		fileName = s;
		imageFormat = format;
	}

	/***************************************************************************
	 * 对屏幕进行拍照 snapShot the Gui once
	 **************************************************************************/
	public void snapShot() {

		try {
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenshot = (new Robot())
					.createScreenCapture(new Rectangle(0, 0,
							(int) d.getWidth(), (int) d.getHeight()));
			serialNum++;
			// 根据文件前缀变量和文件格式变量，自动生成文件名
			String name = fileName + "."
					+ imageFormat;
			File f = new File(name);
			System.out.print("Save File " + name);
			// 将screenshot对象写入图像文件
			ImageIO.write(screenshot, imageFormat, f);
			System.out.print("..Finished!\n");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		GuiCamera cam = new GuiCamera("c:\\Hello", "png");//

		cam.snapShot();
	}
}
