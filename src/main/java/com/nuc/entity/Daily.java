package com.nuc.entity;


import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author 福尔摩东
 * @date 2022/5/2 8:57
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 * 日报实体类
 */

public class Daily implements Serializable, Writable {
    //反序列化保持一致
    private static final long serialVersionUID = 1L;
    //周报id编号
    private String id;
    //学生编号
    private String studentNo;
    //班级编号
    private String classNo;
    //所属日期
    private String time;
    //座右铭
    private String motto;
    //工作内容
    private String workContent;
    //完成率
    private String completion;
    // 备注
    private String note;
    //日报添加时间
    private String addTime;
    //日报/周报 0/1
    private String isDaily;

    public Daily(String id, String studentNo, String classNo, String time, String motto, String workContent, String completion, String note, String addTime, String isDaily) {
        this.id = id;
        this.studentNo = studentNo;
        this.classNo = classNo;
        this.time = time;
        this.motto = motto;
        this.workContent = workContent;
        this.completion = completion;
        this.note = note;
        this.addTime = addTime;
        this.isDaily = isDaily;
    }

    public Daily() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getIsDaily() {
        return isDaily;
    }

    public void setIsDaily(String isDaily) {
        this.isDaily = isDaily;
    }

    @Override
    public String toString() {
        return id + ',' + studentNo + ',' + classNo + ',' + time + ',' + motto + ',' + workContent + ',' + completion + ',' + note + ',' + addTime + ',' + isDaily;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(id);
        dataOutput.writeUTF(studentNo);
        dataOutput.writeUTF(classNo);
        dataOutput.writeUTF(time);
        dataOutput.writeUTF(motto);
        dataOutput.writeUTF(workContent);
        dataOutput.writeUTF(completion);
        dataOutput.writeUTF(note);
        dataOutput.writeUTF(addTime);
        dataOutput.writeUTF(isDaily);
    }


    @Override
    public void readFields(DataInput dataInput) throws IOException {

        this.id = dataInput.readUTF();
        this.studentNo = dataInput.readUTF();
        this.classNo = dataInput.readUTF();
        this.time = dataInput.readUTF();
        this.motto = dataInput.readUTF();
        this.workContent = dataInput.readUTF();
        this.completion = dataInput.readUTF();
        this.note = dataInput.readUTF();
        this.addTime = dataInput.readUTF();
        this.isDaily = dataInput.readUTF();
    }
}
