package com.boco.eoms.sheet.base.flowchar;

/**
 * @see 画折线
 * @author zhangxb
 * @version 1.0
 * @since 2008-09-09
 */

public class PolyLine implements VmlObject {

	private String id = "";

	private Position position = new Position(); // 起点坐标

	private String points = "0,0 0,0 0,0 0,0"; // 线的坐标

	private String lineStrokecolor = "#006699"; // 线的颜色
	private String lineStrokeweight = "1pt"; // 线的粗细

	private String strokeDashstyle = ""; // 线的类型
	private String strokeStartarrow = "oval"; // 线开始的时候有圆点
	private String strokeEndarrow = "classic"; // 线结束的时候有箭头

	private Position textFromPos = new Position(); // 操作内容起始点坐标
	private Position textToPos = new Position(); // 操作内容终止始点坐标

	private String textContent = ""; // 操作名称
	private String link = ""; // 链接

	public PolyLine(Rect rFrom, Rect rTo, String content) {
		textContent = content;
		setRect(rFrom, rTo);
	}

	public PolyLine(Rect rFrom, Rect rTo) {
		setRect(rFrom, rTo);
	}


	public String getVml() {
		String strRt = "";

		if (!link.equals("")) {
			strRt += "<a href=\"#\" style=\"text-decoration:none\" onclick=\"" + link + "\" >\n";
		}

		strRt += "<v:PolyLine id='*linkId*' "
				+ "style='Z-INDEX:1;LEFT:*polyLineLeft*px;TOP:*polyLineTop*px;POSITION:absolute;' "
				+ "points='*points*' "
				+ "strokecolor='*lineStrokecolor*' strokeweight='*lineStrokeweight*' "
				+ "onmouseover='upLine()' onmouseout='downLine()'> \n"
				+ "<v:stroke dashstyle='*strokeDashstyle*' startarrow='*strokeStartarrow*' endarrow='*strokeEndarrow*' /> \n"
				+ "</v:PolyLine>\n"

				+ "<v:Line id='*linkId*' "
				+ "style='Z-INDEX:1;LEFT:*textLeft*px;TOP:*textTop*px;POSITION:absolute;HEIGHT:1px;WIDTH:100px' "
				+ "from='*textFromX*px,*textFromY*px' to='*textToX*px,*textToY*px' "
				+ "strokecolor='#000099' strokeweight='1pt'>\n"
				+ "<v:path textpathok='t' />\n"
				+ "<v:textpath style='font:normal normal normal 9pt Arial' on='t' string ='*textContent*'/> \n"
				+ "</v:Line>\n";

		// PolyLine
		strRt = strRt.replaceAll("\\*linkId\\*", id);
		
		strRt = strRt.replaceAll("\\*polyLineLeft\\*", String.valueOf(position.getX()));
		strRt = strRt.replaceAll("\\*polyLineTop\\*", String.valueOf(position.getY()));

		strRt = strRt.replaceAll("\\*points\\*", points); // 线的坐标

		strRt = strRt.replaceAll("\\*lineStrokecolor\\*", lineStrokecolor); // 线的颜色
		strRt = strRt.replaceAll("\\*lineStrokeweight\\*", lineStrokeweight); // 线的粗细

		strRt = strRt.replaceAll("\\*strokeDashstyle\\*", strokeDashstyle); // 线的类型
		strRt = strRt.replaceAll("\\*strokeStartarrow\\*", strokeStartarrow); // 线开始的时候有圆点
		strRt = strRt.replaceAll("\\*strokeEndarrow\\*", strokeEndarrow); // 线结束的时候有箭头

		// v:line
		strRt = strRt.replaceAll("\\*textLeft\\*", String.valueOf(position.getX()));
		strRt = strRt.replaceAll("\\*textTop\\*", String.valueOf(position.getY()));

		strRt = strRt.replaceAll("\\*textFromX\\*", String.valueOf(textFromPos.getX())); // 线的开始横坐标
		strRt = strRt.replaceAll("\\*textFromY\\*", String.valueOf(textFromPos.getY())); // 线的开始纵坐标

		strRt = strRt.replaceAll("\\*textToX\\*", String.valueOf(textToPos.getX())); // 线的结束横坐标
		strRt = strRt.replaceAll("\\*textToY\\*", String.valueOf(textToPos.getY())); // 线的结束纵坐标

		strRt = strRt.replaceAll("\\*textContent\\*", textContent); // 操作名称

		if (!link.equals("")) {
			strRt += "</a>\n\n";
		}

		return strRt;
	}

	public void setRect(Rect rFrom, Rect rTo) {

		Position rectDefine = rFrom.getRectDefine(); // 矩形的长宽定义
		int rectWidth = rectDefine.getX(); // 矩形的长度
		int rectHeight = rectDefine.getY(); // 矩形的宽度

		Position spaceDefine = rFrom.getSpaceDefine(); // 矩形之间的间距

		Position fromPos = rFrom.getPosition(); // 线的起点
		Position toPos = rTo.getPosition(); // 线的终点

		position.setX(fromPos.getX() + rectWidth); // 左边距 = 起点 + 矩形的宽度
		position.setY(fromPos.getY() + rectHeight / 2); // 顶边距 = 起点 + 矩形的高度的一半

		int x1 = 0;
		int y1 = 0;

		int x4 = toPos.getX() - fromPos.getX() - rectWidth;
		int y4 = toPos.getY() - fromPos.getY();

		int x2 = spaceDefine.getX() / 4;
		int y2 = y1;

		int x3 = x2;
		int y3 = y4;

		points = x1 + "," + y1 + " " + x2 + "," + y2 + " " + x3 + "," + y3
				+ " " + x4 + "," + y4;
		System.out.println(points);

		// 操作说明的起点终点
		textFromPos.setX(x3);
		textFromPos.setY(y3 - 6);

		textToPos.setX(x4);
		textToPos.setY(y4 - 6);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public Position getPosition() {
//		return position;
//	}
//
//	public void setPosition(Position position) {
//		this.position = position;
//	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getLineStrokecolor() {
		return lineStrokecolor;
	}

	public void setLineStrokecolor(String lineStrokecolor) {
		this.lineStrokecolor = lineStrokecolor;
	}

	public String getLineStrokeweight() {
		return lineStrokeweight;
	}

	public void setLineStrokeweight(String lineStrokeweight) {
		this.lineStrokeweight = lineStrokeweight;
	}

	public String getStrokeDashstyle() {
		return strokeDashstyle;
	}

	public void setStrokeDashstyle(String strokeDashstyle) {
		this.strokeDashstyle = strokeDashstyle;
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

//	public Position getTextFromPos() {
//		return textFromPos;
//	}
//
//	public void setTextFromPos(Position textFromPos) {
//		this.textFromPos = textFromPos;
//	}
//
//	public Position getTextToPos() {
//		return textToPos;
//	}
//
//	public void setTextToPos(Position textToPos) {
//		this.textToPos = textToPos;
//	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
