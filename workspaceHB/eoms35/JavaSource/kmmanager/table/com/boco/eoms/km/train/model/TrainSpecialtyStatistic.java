package com.boco.eoms.km.train.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:报名信息
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainSpecialtyStatistic extends BaseObject {

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 专业名字
     */
    private String trainSpeciality;

    /**
     * 培训次数
     */
    private Integer trainCount;

    /**
     * 培训天数
     */
    private Integer timeCount;

    public boolean equals(Object o) {
        if (o instanceof TrainSpecialtyStatistic) {
            TrainSpecialtyStatistic trainEnter = (TrainSpecialtyStatistic) o;
            if (this.id != null || this.id.equals(trainEnter.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getTrainSpeciality() {
        return trainSpeciality;
    }

    public void setTrainSpeciality(String trainSpeciality) {
        this.trainSpeciality = trainSpeciality;
    }

    public Integer getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(Integer trainCount) {
        this.trainCount = trainCount;
    }

    public Integer getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Integer timeCount) {
        this.timeCount = timeCount;
    }
}