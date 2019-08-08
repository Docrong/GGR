package com.boco.eoms.infmanage.model;

public class Project {
    private int project_id;
    private int project_code;
    private String project_code_name;
    private String project_level;
    private String project_instancy;
    private String project_state;
    private String project_send_user;
    private String project_exec_time;
    private String project_comp_time;
    private String project_plan;
    private String dept_name;
    private String dept_id;
    private String project_executor;
    private String project_executor_name;
    private String project_name;
    private String project_desc;
    private int project_sign;
    private int project_task_flag;
    private int project_scale;
    private String check_project_group;

    ////子任务
    ////子任务
    private int id;
    private String parent_id;
    private int task_number;
    private String task_name;
    private String task_send_user;
    private String task_exec_time;
    private String task_comp_time;
    private String task_dept_name;
    private String task_dept_id;
    private String task_executor;
    private String task_executor_name;
    private int task_scale;
    private String task_remark;
    private int task_sign;
    private String task_desc;


    private String track_exec_time;
    private String track_info;

    //////审核字段
    private String pro_check_content;
    private String pro_audit;
    private int pro_audit_flag;

    private String task_check_content;
    private String task_audit;
    private String task_audit_flag;


    public int getProject_code() {
        return project_code;
    }

    public String getProject_exec_time() {
        return project_exec_time;
    }

    public String getProject_state() {
        return project_state;
    }

    public String getProject_executor() {
        return project_executor;
    }

    public String getProject_send_user() {
        return project_send_user;
    }

    public int getProject_id() {
        return project_id;
    }


    public String getProject_comp_time() {
        return project_comp_time;
    }

    public String getProject_level() {
        return project_level;
    }

    public int getProject_task_flag() {
        return project_task_flag;
    }

    public String getProject_instancy() {
        return project_instancy;
    }

    public String getProject_desc() {
        return project_desc;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getProject_plan() {
        return project_plan;
    }

    public int getProject_sign() {
        return project_sign;
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public String getProject_code_name() {
        return project_code_name;
    }

    public String getProject_executor_name() {
        return project_executor_name;
    }

    public String getTask_executor() {
        return task_executor;
    }

    public int getTask_number() {
        return task_number;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getTask_comp_time() {
        return task_comp_time;
    }

    public String getTask_send_user() {
        return task_send_user;
    }

    public int getTask_sign() {
        return task_sign;
    }

    public String getTask_remark() {
        return task_remark;
    }

    public int getId() {
        return id;
    }

    public String getTask_exec_time() {
        return task_exec_time;
    }

    public int getTask_scale() {
        return task_scale;
    }

    public String getTask_dept_id() {
        return task_dept_id;
    }

    public String getTask_dept_name() {
        return task_dept_name;
    }

    public int getProject_scale() {
        return project_scale;
    }

    public String getTask_executor_name() {
        return task_executor_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public String getTrack_exec_time() {
        return track_exec_time;
    }

    public String getPro_check_content() {
        return pro_check_content;
    }

    public String getTask_audit() {
        return task_audit;
    }

    public String getTask_check_content() {
        return task_check_content;
    }

    public String getTask_audit_flag() {
        return task_audit_flag;
    }

    public String getPro_audit() {
        return pro_audit;
    }

    public int getPro_audit_flag() {
        return pro_audit_flag;
    }

    public String getTrack_info() {
        return track_info;
    }

    public String getCheck_project_group() {
        return check_project_group;
    }


    public void setProject_code(int project_code) {
        this.project_code = project_code;
    }

    public void setProject_exec_time(String project_exec_time) {
        this.project_exec_time = project_exec_time;
    }

    public void setProject_state(String project_state) {
        this.project_state = project_state;
    }

    public void setProject_executor(String project_executor) {
        this.project_executor = project_executor;
    }

    public void setProject_send_user(String project_send_user) {
        this.project_send_user = project_send_user;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setProject_comp_time(String project_comp_time) {
        this.project_comp_time = project_comp_time;
    }

    public void setProject_level(String project_level) {
        this.project_level = project_level;
    }

    public void setProject_task_flag(int project_task_flag) {
        this.project_task_flag = project_task_flag;
    }

    public void setProject_instancy(String project_instancy) {
        this.project_instancy = project_instancy;
    }

    public void setProject_desc(String project_desc) {
        this.project_desc = project_desc;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setProject_plan(String project_plan) {
        this.project_plan = project_plan;
    }

    public void setProject_sign(int project_sign) {
        this.project_sign = project_sign;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public void setProject_code_name(String project_code_name) {
        this.project_code_name = project_code_name;
    }

    public void setProject_executor_name(String project_executor_name) {
        this.project_executor_name = project_executor_name;
    }

    public void setTask_executor(String task_executor) {
        this.task_executor = task_executor;
    }

    public void setTask_number(int task_number) {
        this.task_number = task_number;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_comp_time(String task_comp_time) {
        this.task_comp_time = task_comp_time;
    }

    public void setTask_send_user(String task_send_user) {
        this.task_send_user = task_send_user;
    }

    public void setTask_sign(int task_sign) {
        this.task_sign = task_sign;
    }

    public void setTask_remark(String task_remark) {
        this.task_remark = task_remark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask_exec_time(String task_exec_time) {
        this.task_exec_time = task_exec_time;
    }

    public void setTask_scale(int task_scale) {
        this.task_scale = task_scale;
    }

    public void setTask_dept_id(String task_dept_id) {
        this.task_dept_id = task_dept_id;
    }

    public void setTask_dept_name(String task_dept_name) {
        this.task_dept_name = task_dept_name;
    }

    public void setProject_scale(int project_scale) {
        this.project_scale = project_scale;
    }

    public void setTask_executor_name(String task_executor_name) {
        this.task_executor_name = task_executor_name;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public void setTrack_exec_time(String track_exec_time) {
        this.track_exec_time = track_exec_time;
    }

    public void setPro_check_content(String pro_check_content) {
        this.pro_check_content = pro_check_content;
    }

    public void setTask_audit(String task_audit) {
        this.task_audit = task_audit;
    }

    public void setTask_check_content(String task_check_content) {
        this.task_check_content = task_check_content;
    }

    public void setTask_audit_flag(String task_audit_flag) {
        this.task_audit_flag = task_audit_flag;
    }

    public void setPro_audit(String pro_audit) {
        this.pro_audit = pro_audit;
    }

    public void setPro_audit_flag(int pro_audit_flag) {
        this.pro_audit_flag = pro_audit_flag;
    }

    public void setTrack_info(String track_info) {
        this.track_info = track_info;
    }

    public void setCheck_project_group(String check_project_group) {
        this.check_project_group = check_project_group;
    }


}
