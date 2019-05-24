package com.boco.eoms.sheet.base.flowchar;

/**
 * @see 画矩形
 * @author zhangxb
 * @version 1.0
 * @since 2008-09-09
 */

public class Rect implements VmlObject {
	
	private Position rectDefine  = null; //矩形的长宽
	private Position spaceDefine = null; //矩形之间的间距
	private Position position = null; //起点坐标

	private String id = ""; // 编号

	private String rectArcsize = "4321f";
	private String rectCoordsize = "21600,21600"; // 定义了21600 * 21600的画布
	private String rectFillcolor = "#F73809"; // 背景颜色

	private String rectStokecolor = "#000000"; // 边框颜色
	private String rectStrokeweight = "1"; // 边框宽度

	private String strokeStartarrow = "none";
	private String strokeEndarrow = "none";

	private String shadowOn = "t"; // 是否启用阴影
	private String shadowColor = "#b3b3b3"; // 阴影的颜色
	private String shadowOffsetr = "5px,5px"; // 阴影的偏离位置
	private String shadowType = "single";

	private String textboxStyle = "font-size:9.0pt;color:white"; // 文字区域的字体、大小、颜色
	private String textboxContent = ""; // 文字区域的内容

	private String fillType = "gradient"; // 线状的效果：线状渐变
	private String fillColor2 = "#F69679"; // 渐变的结束颜色

	private String link = ""; // 矩形的链接

	public Rect(Position rectDefine, Position spaceDefine, Position position, String id, String content) {
		this.rectDefine = rectDefine;
		this.spaceDefine = spaceDefine;		
		this.position = position;
		this.id = id;
		this.textboxContent = content;
	}

	public Rect(Position rectDefine, Position spaceDefine, Position position, String id) {
		this.rectDefine = rectDefine;
		this.spaceDefine = spaceDefine;		
		this.position = position;
		this.id = id;	
	}

	public Rect(Position rectDefine, Position spaceDefine, Position position) {
		this.rectDefine = rectDefine;
		this.spaceDefine = spaceDefine;		
		this.position = position;
	}

	public String getVml() {
		String strRt = "";

		if ("" != link) {
			strRt += "<a href=\"#\" style=\"text-decoration:none\" onclick=\"" + link + "\" >\n";
		}
		strRt += "<v:RoundRect id='*rectId*' "
				+ "style='Z-INDEX:1;LEFT:*rectLeft*px;TOP:*rectTop*px;WIDTH:*rectWidth*px;HEIGHT:*rectHeight*px;POSITION:absolute;' "
				+ "arcsize='*rectArcsize*' coordsize='*rectCoordsize*' fillcolor='*rectFillcolor*' "
				+ "stokecolor='*rectStokecolor*' strokeweight='*rectStrokeweight*' "
				+ "onmouseover='upRect()' onmouseout='downRect()'> \n"
				+ "<v:stroke startarrow='*strokeStartarrow*' endarrow='*strokeEndarrow*' /> \n"
				+ "<v:shadow on='*shadowOn*' color='*shadowColor*' offset='*shadowOffsetr*' type='*shadowType*'/> \n"
				+ "<v:textbox style='*textboxStyle*'>*textboxContent*</v:textbox> \n"
				+ "<v:fill type='*fillType*' color2='*fillColor2*' /> \n"
				+ "</v:RoundRect>\n";

		strRt = strRt.replaceAll("\\*rectId\\*", id);

		strRt = strRt.replaceAll("\\*rectLeft\\*", String.valueOf(position.getX()));
		strRt = strRt.replaceAll("\\*rectTop\\*", String.valueOf(position.getY()));
		
		strRt = strRt.replaceAll("\\*rectWidth\\*", String.valueOf(rectDefine.getX()));
		strRt = strRt.replaceAll("\\*rectHeight\\*", String.valueOf(rectDefine.getY()));

		strRt = strRt.replaceAll("\\*rectArcsize\\*", rectArcsize);
		strRt = strRt.replaceAll("\\*rectCoordsize\\*", rectCoordsize);
		strRt = strRt.replaceAll("\\*rectFillcolor\\*", rectFillcolor);

		strRt = strRt.replaceAll("\\*rectStokecolor\\*", rectStokecolor);
		strRt = strRt.replaceAll("\\*rectStrokeweight\\*", rectStrokeweight);

		strRt = strRt.replaceAll("\\*strokeStartarrow\\*", strokeStartarrow);
		strRt = strRt.replaceAll("\\*strokeEndarrow\\*", strokeEndarrow);

		strRt = strRt.replaceAll("\\*shadowOn\\*", shadowOn);
		strRt = strRt.replaceAll("\\*shadowColor\\*", shadowColor);
		strRt = strRt.replaceAll("\\*shadowOffsetr\\*", shadowOffsetr);
		strRt = strRt.replaceAll("\\*shadowType\\*", shadowType);

		strRt = strRt.replaceAll("\\*textboxStyle\\*", textboxStyle);
		strRt = strRt.replaceAll("\\*textboxContent\\*", textboxContent);

		strRt = strRt.replaceAll("\\*fillType\\*", fillType);
		strRt = strRt.replaceAll("\\*fillColor2\\*", fillColor2);

		if ("" != link) {
			strRt += "</a>\n\n";
		}

		return strRt;
	}

	public Position getSpaceDefine() {
		return spaceDefine;
	}

	public void setSpaceDefine(Position spaceDefine) {
		this.spaceDefine = spaceDefine;
	}

	public Position getRectDefine() {
		return rectDefine;
	}

	public void setRectDefine(Position rectDefine) {
		this.rectDefine = rectDefine;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRectArcsize() {
		return rectArcsize;
	}

	public void setRectArcsize(String rectArcsize) {
		this.rectArcsize = rectArcsize;
	}

	public String getRectCoordsize() {
		return rectCoordsize;
	}

	public void setRectCoordsize(String rectCoordsize) {
		this.rectCoordsize = rectCoordsize;
	}

	public String getRectFillcolor() {
		return rectFillcolor;
	}

	public void setRectFillcolor(String rectFillcolor) {
		this.rectFillcolor = rectFillcolor;
	}

	public String getRectStokecolor() {
		return rectStokecolor;
	}

	public void setRectStokecolor(String rectStokecolor) {
		this.rectStokecolor = rectStokecolor;
	}

	public String getRectStrokeweight() {
		return rectStrokeweight;
	}

	public void setRectStrokeweight(String rectStrokeweight) {
		this.rectStrokeweight = rectStrokeweight;
	}

	public String getStrokeStartarrow() {
		return strokeStartarrow;
	}

	public void setStrokeStartarrow(String strokeStartarrow) {
		this.strokeStartarrow = strokeStartarrow;
	}

	public String getStrokeEndarrow() {
		return strokeEndarrow;
	}

	public void setStrokeEndarrow(String strokeEndarrow) {
		this.strokeEndarrow = strokeEndarrow;
	}

	public String getShadowOn() {
		return shadowOn;
	}

	public void setShadowOn(String shadowOn) {
		this.shadowOn = shadowOn;
	}

	public String getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(String shadowColor) {
		this.shadowColor = shadowColor;
	}

	public String getShadowOffsetr() {
		return shadowOffsetr;
	}

	public void setShadowOffsetr(String shadowOffsetr) {
		this.shadowOffsetr = shadowOffsetr;
	}

	public String getShadowType() {
		return shadowType;
	}

	public void setShadowType(String shadowType) {
		this.shadowType = shadowType;
	}

	public String getTextboxStyle() {
		return textboxStyle;
	}

	public void setTextboxStyle(String textboxStyle) {
		this.textboxStyle = textboxStyle;
	}

	public String getTextboxContent() {
		return textboxContent;
	}

	public void setTextboxContent(String textboxContent) {
		this.textboxContent = textboxContent;
	}

	public String getFillType() {
		return fillType;
	}

	public void setFillType(String fillType) {
		this.fillType = fillType;
	}

	public String getFillColor2() {
		return fillColor2;
	}

	public void setFillColor2(String fillColor2) {
		this.fillColor2 = fillColor2;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
