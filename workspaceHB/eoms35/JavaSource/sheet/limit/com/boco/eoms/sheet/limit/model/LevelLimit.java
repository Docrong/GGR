/**
 *
 */
package com.boco.eoms.sheet.limit.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * @author Administrator
 *
 */
public class LevelLimit extends BaseObject implements Serializable {
    private String id;
    private String flowName;
    private String level1;
    private String level2;
    private String level3;

    private String specialty1;
    private String specialty2;

    private String specialty3;
    private String specialty4;
    private String specialty5;
    private String specialty6;

    private String specialty7;
    private String specialty8;
    private String acceptLimit;
    private String replyLimit;

    public String getAcceptLimit() {
        return acceptLimit;
    }

    public void setAcceptLimit(String acceptLimit) {
        this.acceptLimit = acceptLimit;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReplyLimit() {
        return replyLimit;
    }

    public void setReplyLimit(String replyLimit) {
        this.replyLimit = replyLimit;
    }

    public String getSpecialty1() {
        return specialty1;
    }

    public void setSpecialty1(String specialty1) {
        this.specialty1 = specialty1;
    }

    public String getSpecialty2() {
        return specialty2;
    }

    public void setSpecialty2(String specialty2) {
        this.specialty2 = specialty2;
    }

    public String getSpecialty3() {
        return specialty3;
    }

    public void setSpecialty3(String specialty3) {
        this.specialty3 = specialty3;
    }

    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getSpecialty4() {
        return specialty4;
    }

    public void setSpecialty4(String specialty4) {
        this.specialty4 = specialty4;
    }

    public String getSpecialty5() {
        return specialty5;
    }

    public void setSpecialty5(String specialty5) {
        this.specialty5 = specialty5;
    }

    public String getSpecialty6() {
        return specialty6;
    }

    public void setSpecialty6(String specialty6) {
        this.specialty6 = specialty6;
    }

    public String getSpecialty7() {
        return specialty7;
    }

    public void setSpecialty7(String specialty7) {
        this.specialty7 = specialty7;
    }

    public String getSpecialty8() {
        return specialty8;
    }

    public void setSpecialty8(String specialty8) {
        this.specialty8 = specialty8;
    }


}
