package com.boco.eoms.sheet.base.flowchar.bias;

import com.boco.eoms.sheet.base.flowchar.Position;
import com.boco.eoms.sheet.base.flowchar.VmlObject;

public class Rect implements VmlObject {

	protected Position p;

	protected int width = 100;

	protected int height = 60;

	protected String content = "";

	protected final static int heightOfLine = 10;

	private String link = "";

	private String colorBegin = "#FF0033";

	private String colorEnd = "#FF6633";

	private boolean isChangeColor = false;

	private final int HORSPACE = 10;

	private final int VERSPACE = 180;

	private int status = 0;// 0,未接单;1,已接单未回复;2,已回复

	public Rect(Position p, int width, int height, String content) {
		this.p = p;
		this.width = width;
		this.height = height;
		this.content = content;
	}

	public Rect(Position p, int width, int height) {
		this.p = p;
		this.width = width;
		this.height = height;
	}

	public Rect(Position p) {
		this.p = p;
	}

	public Rect() {
	}

	public Position getTopMiddle() {
		return new Position(p.getX() + width / 2, p.getY());
	}

	public Position getBottomMiddle() {
		return new Position(p.getX() + width / 2, p.getY() + height);
	}

	public Position getLeftMiddle() {
		return new Position(p.getX(), p.getY() + height / 2);
	}

	public Position getRightMiddle() {
		return new Position(p.getX() + width, p.getY() + height / 2);
	}

	public String getVml() {
		String strRt = "";
		if ("" != link) {
			strRt += "<a href=\"#\" style=\"text-decoration:none\" onclick=\""
					+ link + "\" >";
		}
		strRt += "<v:roundrect style='Z-INDEX: 1001; LEFT: *l*px; TOP: *t*px;WIDTH: *w*px;HEIGHT: *h*px;POSITION: absolute;' arcsize = '4321f' coordsize = '21600,21600' fillcolor = '*colorBegin*'>\n"
				+ "<v:Stroke startarrow = 'none' endarrow = 'none'></v:Stroke>\n"
				+ "<v:shadow on='t' type='single' color='#d7d7d7' offset='3pt,3pt'></v:shadow>\n"
				+ "<v:TextBox inset='2pt,2pt,2pt,2pt' style='font-size:9pt;color:white'>*content*</v:TextBox>\n"
				+ "<v:fill type = 'gradient' color2 = '*colorEnd*'></v:fill>\n"
				+ "</v:roundrect>\n\n\n";
		strRt = strRt.replaceAll("\\*l\\*", String.valueOf(p.getX()));
		strRt = strRt.replaceAll("\\*t\\*", String.valueOf(p.getY()));
		strRt = strRt.replaceAll("\\*w\\*", String.valueOf(width));
		strRt = strRt.replaceAll("\\*h\\*", String.valueOf(height));
		strRt = strRt.replaceAll("\\*content\\*", content);
		strRt = strRt.replaceAll("\\*colorBegin\\*", colorBegin);
		strRt = strRt.replaceAll("\\*colorEnd\\*", colorEnd);
		if ("" != link) {
			strRt += "</a>";
		}
		return strRt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addContent(String content) {
		if (!(null == content)) {
			if (!content.equals("")) {
				if (content.equals("")) {
					this.content += content;
				} else {
					this.content += "<br>" + content;
				}
				setHeight(getHeight() + heightOfLine);
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Position getP() {
		return p;
	}

	public void setP(Position p) {
		this.p = p;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getColorBegin() {
		return colorBegin;
	}

	public void setColorBegin(String colorBegin) {
		this.colorBegin = colorBegin;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getColorEnd() {
		return colorEnd;
	}

	public void setColorEnd(String colorEnd) {
		this.colorEnd = colorEnd;
	}

	public boolean getIsChangeColor() {
		return isChangeColor;
	}

	public void setIsChangeColor(boolean isChangeColor) {
		this.isChangeColor = isChangeColor;
	}

}
