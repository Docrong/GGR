package com.boco.eoms.sheet.base.flowchar.bias;

import com.boco.eoms.sheet.base.flowchar.Position;
import com.boco.eoms.sheet.base.flowchar.VmlObject;

public class Line implements VmlObject {
	private Position from;

	private Position to;

	private String content = "";

	private String color = "";

	private String link = "";

	private String flagStartArrow;

	public Line(Position from, Position to) {
		setPosition(from, to);
	}

	public Line(Position from, Position to, String content) {
		setPosition(from, to);
		this.content = content;
	}

	public Line(Rect rFrom, Rect rTo) {
		setRect(rFrom, rTo);
	}

	public Line(Rect rFrom, Rect rTo, String content) {
		setRect(rFrom, rTo);
		this.content = content;
	}

	public Line() {

	}

	public void setRect(Rect rFrom, Rect rTo) {
		if (rFrom.getP().getY() == rTo.getP().getY()) {
			if (rFrom.getP().getX() <= rTo.getP().getX()) {
				from = rFrom.getRightMiddle();
				to = rTo.getLeftMiddle();
			} else {
				from = rFrom.getLeftMiddle();
				to = rTo.getRightMiddle();
			}

			from = rFrom.getBottomMiddle();
			to = rTo.getBottomMiddle();

		} else if (rFrom.getBottomMiddle().getY() > rTo.getTopMiddle().getY()) {
			from = rFrom.getTopMiddle();
			to = rTo.getBottomMiddle();
		} else {
			from = rFrom.getBottomMiddle();
			to = rTo.getTopMiddle();
		}
	}

	public void setPosition(Position from, Position to) {
		this.from = from;
		this.to = to;
	}

	public String getVml() {
		String strRt = "";
		if ("" != link) {
			strRt += "<a href=\"" + link + "\" >";
		}
		if (from.getY() == to.getY()) {

			strRt += "<v:PolyLine filled=false style='Z-INDEX: 1001;position:absolute;' Points='*p*'>\n"
					+ "<v:Stroke startarrow = '*flag*' endarrow = 'classic'></v:Stroke>\n"
					+ "</v:PolyLine>\n";

			String p = from.getX() + "," + from.getY();
			p += "," + (from.getX() + to.getX()) / 2 + "," + (from.getY() + 80);
			p += "," + to.getX() + "," + to.getY();
			strRt = strRt.replaceAll("\\*p\\*", p);
			strRt = strRt.replaceAll("\\*flag\\*", flagStartArrow);
		} else {
			strRt += "<v:line style='Z-INDEX: 1001;  LEFT: *l*px; TOP: *t*px;POSITION: absolute;;WIDTH:100px' *from* = '*ffx*px,0px' *to* = '*ftx*px,*ty*px' strokecolor = '#000099' strokeweight = '1pt'>\n"
					+ "<v:path textpathok = 't'></v:path>\n"
					+ "<v:textpath style='font:normal normal normal 10pt Arial' on = 't' string ='*content*'/>"
					+ "</v:line>\n"
					+ "<v:line style='Z-INDEX: 1001;  LEFT: *l*px; TOP: *t*px;POSITION: absolute;' from = '0px,0px' to = '*tx*px,*ty*px' strokecolor = '#006699' strokeweight = '1pt'>\n"
					+ "<v:Stroke startarrow = '*flag*' endarrow = 'classic'></v:Stroke>\n"
					+ "</v:line>\n\n";

			double relativeY = to.getY() - from.getY();
			double relativeX = to.getX() - from.getX();

			double exY = 0;
			double exX = 0;
			exY = Math.abs(relativeY / 2);
			exX = Math.abs(exY * (relativeX / relativeY));

			if (relativeX >= 0) {
				strRt = strRt.replaceAll("\\*from\\*", "from");
				strRt = strRt.replaceAll("\\*to\\*", "to");
				strRt = strRt.replaceAll("\\*ftx\\*", String
						.valueOf(relativeX + 12));
				strRt = strRt.replaceAll("\\*ffx\\*", "12");
			} else {
				strRt = strRt.replaceAll("\\*to\\*", "from");
				strRt = strRt.replaceAll("\\*from\\*", "to");
				strRt = strRt.replaceAll("\\*ftx\\*", String
						.valueOf(relativeX - 17));
				strRt = strRt.replaceAll("\\*ffx\\*", "-17");
			}

			strRt = strRt.replaceAll("\\*l\\*", String.valueOf(from.getX()));
			strRt = strRt.replaceAll("\\*t\\*", String.valueOf(from.getY()));
			strRt = strRt.replaceAll("\\*tx\\*", String.valueOf(relativeX));
			strRt = strRt.replaceAll("\\*ty\\*", String.valueOf(relativeY));

			strRt = strRt.replaceAll("\\*txtLeft\\*", String.valueOf(exX));
			strRt = strRt.replaceAll("\\*txtTop\\*", String.valueOf(exY));

			strRt = strRt.replaceAll("\\*content\\*", content);
			strRt = strRt.replaceAll("\\*flag\\*", flagStartArrow);
		}
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

	public Position getFrom() {
		return from;
	}

	public void setFrom(Position from) {
		this.from = from;
	}

	public Position getTo() {
		return to;
	}

	public void setTo(Position to) {
		this.to = to;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFlagStartArrow() {
		return flagStartArrow;
	}

	public void setFlagStartArrow(String flagStartArrow) {
		this.flagStartArrow = flagStartArrow.equals("true") ? "classic"
				: "none";
	}

}
