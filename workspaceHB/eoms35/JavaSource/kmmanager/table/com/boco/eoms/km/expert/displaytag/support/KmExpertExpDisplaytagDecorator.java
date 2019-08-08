package com.boco.eoms.km.expert.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.km.expert.model.KmExpertExp;

/**
 * <p>
 * Title:tawDutyEventList.jsp页面分页显示checkbox
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-03-26
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertExpDisplaytagDecorator extends TableDecorator {
    /**
     * id属性的checkbox
     *
     * @return 一个带有checkbox的属性
     */
    public String getId() {
        KmExpertExp kmExpertExp = (KmExpertExp) getCurrentRowObject();
        return "<input type='checkbox' id='" + kmExpertExp.getId()
                + "' name='ids' value='" + kmExpertExp.getId() + "'>";
    }
}
