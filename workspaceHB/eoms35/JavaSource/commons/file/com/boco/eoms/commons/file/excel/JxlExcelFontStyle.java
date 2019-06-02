package com.boco.eoms.commons.file.excel;

import java.util.HashMap;
import java.util.Map;

import jxl.format.BoldStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;

import com.boco.eoms.commons.file.config.model.FMExportTitle;

/**
 * <p>
 * Title:封装jxl excel 格式
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 29, 2007 3:10:04 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class JxlExcelFontStyle {

	/**
	 * 粗体
	 */
	public final static String BOLD_WEIGHT = "bold";

	/**
	 * 不加粗
	 */
	public final static String NOBOLD_WEIGHT = "nobold";

	/**
	 * 斜体
	 */
	public final static String ITALIC_STYLE = "italic";

	/**
	 * 不加斜体
	 */
	public final static String NO_ITALIC_STYLE = "noitalic";

	public final static String BLACK_COLOR = "black";

	public final static String BLUE_COLOR = "blue";

	public final static String BLUE2_COLOR = "blue2";

	public final static String BRIGHT_GREEN_COLOR = "grightgreen";

	public final static String BLUEGREY_COLOR = "bluegrey";

	public final static String BRIGHTGREEN_COLOR = "brightgreen";

	public final static String BROWN_COLOR = "brown";

	public final static String DARKGREEN_COLOR = "darkgreen";

	public final static String DARKPURLE_COLOR = "darkpurle";

	public final static String RED_COLOR = "red";

	public final static String RED2_COLOR = "red2";

	public final static String DARKTEAL_COLOR = "darkteal";

	public final static String DARKYELLOW_COLOR = "darkyellow";

	public final static String GRAY20_COLOR = "gray20";

	public final static String GRAY50_COLOR = "gray50";

	public final static String GRAY80_COLOR = "gray80";

	public final static String GRAY25PERCENT_COLOR = "gray25percent";

	public final static String GRAY40PERCENT_COLOR = "gray40percent";

	public final static String GRAY50PERCENT_COLOR = "gray50percent";

	public final static String GRAY80PERCENT_COLOR = "gray80percent";

	public final static String ICEBLUE_COLOR = "iceblue";

	public final static String GREEN_COLOR = "green";

	public final static String INDIGO_COLOR = "indigo";

	public final static String INVRY_COLOR = "invry";

	public final static String LIGHTBLUE_COLOR = "lightblue";

	public final static String LIGHTGREEN_COLOR = "lightgreen";

	public final static String LIGHTORANGE_COLOR = "lightorange";

	public final static String LIGHTTURQUOISE2_COLOR = "lightturquoise2";

	public final static String LIGHTTURQUOISE_COLOR = "lightturquoise";

	public final static String LIME_COLOR = "lime";

	public final static String OCEANBLUE_COLOR = "oceanblue";

	public final static String OLIVEGREEN_COLOR = "olivegreen";

	public final static String PALEBLUE_COLOR = "paleblue";

	public final static String PALETTEBLACK_COLOR = "paletteblack";

	public final static String ROSE_COLOR = "rose";

	public final static String SKYBLUE_COLOR = "skyblue";

	public final static String SEAGREEN_COLOR = "seagreen";

	public final static String ORANGE_COLOR = "orange";

	public final static String PINK_COLOR = "pink";

	public final static String PINK2_COLOR = "pink2";

	public final static String BLUE_GREY_COLOR = "bluegrey";

	// TODO 待开发
	/**
	 * 获取字体颜色
	 * 
	 * @param color
	 * @return
	 */
	public static Colour getColor(String color) {
		Map map = null;
		if (map == null) {
			map = new HashMap();
			map.put(BLACK_COLOR, Colour.BLACK);
			map.put(BLUE_COLOR, Colour.BLUE);
			map.put(BLUE2_COLOR, Colour.BLUE2);
			map.put(BLUE_GREY_COLOR, Colour.BLUE_GREY);
			map.put(BRIGHT_GREEN_COLOR, Colour.BRIGHT_GREEN);
			map.put(BROWN_COLOR, Colour.BROWN);
			map.put(BLUE2_COLOR, Colour.DARK_BLUE2);
			map.put(DARKGREEN_COLOR, Colour.DARK_GREEN);
			map.put(DARKPURLE_COLOR, Colour.DARK_PURPLE);
			map.put(RED_COLOR, Colour.RED);
			map.put(RED2_COLOR, Colour.DARK_RED2);
			map.put(DARKTEAL_COLOR, Colour.DARK_TEAL);
			map.put(DARKYELLOW_COLOR, Colour.DARK_YELLOW);
			map.put(GRAY20_COLOR, Colour.GRAY_25);
			map.put(GRAY50_COLOR, Colour.GRAY_50);
			map.put(GRAY80_COLOR, Colour.GRAY_80);
			map.put(GRAY25PERCENT_COLOR, Colour.GREY_25_PERCENT);
			map.put(GRAY40PERCENT_COLOR, Colour.GREY_40_PERCENT);
			map.put(GRAY50PERCENT_COLOR, Colour.GREY_50_PERCENT);
			map.put(GRAY80PERCENT_COLOR, Colour.GREY_80_PERCENT);
			map.put(ICEBLUE_COLOR, Colour.ICE_BLUE);
			map.put(GREEN_COLOR, Colour.GREEN);
			map.put(INDIGO_COLOR, Colour.INDIGO);
			map.put(INVRY_COLOR, Colour.IVORY);
			map.put(LIGHTBLUE_COLOR, Colour.LIGHT_BLUE);
			map.put(LIGHTGREEN_COLOR, Colour.LIGHT_GREEN);
			map.put(LIGHTORANGE_COLOR, Colour.LIGHT_ORANGE);
			map.put(LIGHTTURQUOISE_COLOR, Colour.LIGHT_TURQUOISE);
			map.put(LIGHTTURQUOISE2_COLOR, Colour.LIGHT_TURQUOISE2);
			map.put(LIME_COLOR, Colour.LIME);
			map.put(OCEANBLUE_COLOR, Colour.OCEAN_BLUE);
			map.put(OLIVEGREEN_COLOR, Colour.OLIVE_GREEN);
			map.put(PALEBLUE_COLOR, Colour.PALE_BLUE);
			map.put(PALETTEBLACK_COLOR, Colour.PALETTE_BLACK);
			map.put(ROSE_COLOR, Colour.ROSE);
			map.put(SKYBLUE_COLOR, Colour.SKY_BLUE);
			map.put(SEAGREEN_COLOR, Colour.SEA_GREEN);
			map.put(ORANGE_COLOR, Colour.ORANGE);
			map.put(PINK_COLOR, Colour.PINK);
			map.put(PINK2_COLOR, Colour.PINK2);
		}
		return (Colour) map.get(color);
	}

	public static boolean getStyle(String style) {

		if (ITALIC_STYLE.equals(style)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取字体格式
	 * 
	 * @param title
	 * @return
	 */
	public static WritableFont getWritableFont(FMExportTitle title) {
		WritableFont wf = null;
		// TODO 需要格式化字符
		// 粗体
		if (BOLD_WEIGHT.equals(title.getFont().getWeight())) {
			wf = new WritableFont(WritableFont.createFont(title.getFont()
					.getFamily()), title.getFont().getSize().intValue(),
					WritableFont.BOLD, getStyle(title.getFont().getStyle()),
					UnderlineStyle.NO_UNDERLINE, getColor(title.getFont()
							.getColor()));
		}
		// 不加粗
		else {
			wf = new WritableFont(WritableFont.createFont(title.getFont()
					.getFamily()), title.getFont().getSize().intValue(),
					WritableFont.NO_BOLD, getStyle(title.getFont().getStyle()),
					UnderlineStyle.NO_UNDERLINE, getColor(title.getFont()
							.getColor()));
		}
		return wf;
	}

	/**
	 * 获取粗细体,未使用
	 * 
	 * @param weight
	 * @return
	 */
	public static BoldStyle getWeight(String weight) {
		Map map = null;
		if (map == null) {
			map = new HashMap();
			map.put(BOLD_WEIGHT, WritableFont.BOLD);
			map.put(NOBOLD_WEIGHT, WritableFont.NO_BOLD);
		}
		return (BoldStyle) map.get(weight);
	}

}
