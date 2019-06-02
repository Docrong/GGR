package com.boco.eoms.commons.file.excel;

import java.sql.Timestamp;
import java.util.Date;

import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 29, 2007 4:14:52 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class JxlExcelUtil {

	/**
	 * 通过类型取cell
	 * 
	 * @param obj
	 *            被转换成cell的对象
	 * @param format
	 *            格式化
	 * @param col
	 *            要写入的列
	 * @param row
	 *            要写入的行
	 * @return
	 */
	public static WritableCell getCellByType(Object obj,
			WritableCellFormat format, Integer col, Integer row) {
		// xml excel所配的type被忽略
		// 若为date或timestamp类型，则返回DateTime cell对象
		if (obj instanceof String) {
			if (format == null) {
				return new Label(col.intValue(), row.intValue(), (String) obj);
			}
			return new Label(col.intValue(), row.intValue(), (String) obj,
					format);
		} else if (obj instanceof Date || obj instanceof Timestamp) {
			if (format == null) {
				return new DateTime(col.intValue(), row.intValue(), (Date) obj);
			}
			return new DateTime(col.intValue(), row.intValue(), (Date) obj,
					format);
		}// 返回double cell对象
		else if (obj instanceof Double || obj instanceof Integer
				|| obj instanceof Long) {
			if (format == null) {
				return new Number(col.intValue(), row.intValue(),
						((Double) obj).doubleValue());
			}
			return new Number(col.intValue(), row.intValue(), ((Double) obj)
					.doubleValue(), format);
		}

		return new Label(col.intValue(), row.intValue(), (String) obj);
	}
}
