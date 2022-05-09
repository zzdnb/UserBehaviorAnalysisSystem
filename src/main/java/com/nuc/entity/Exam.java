package com.nuc.entity;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author 福尔摩东
 * @date 2022/5/2 13:00
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */

public class Exam implements Serializable, Writable {
    //反序列化保持一致
    private static final long serialVersionUID = 1L;
    //试卷id
    private String paperNo;
    //学生id
    private String studentNo;
    //主观题得分
    private String subjectiveScore;
    //客观题得分
    private String objectiveScore;
    //总得分
    private String score;
    //老师是否批阅 1/2
    private String isRead;
    //是否正常提交
    private String isSubmit;
    //是否正常进入考试  1/2
    private String isEnter;
    //客观题各个题型对应的得分   1单选 2多选 3判断
    private String subjectiveDetails;
    //主观题各个题型对应的得分  4 5 6 7分别代表主观题的题型
    private String objectiveDetails;

    public Exam(String paperNo, String studentNo, String subjectiveScore, String objectiveScore, String score, String isRead, String isSubmit, String isEnter, String subjectiveDetails, String objectiveDetails) {
        this.paperNo = paperNo;
        this.studentNo = studentNo;
        this.subjectiveScore = subjectiveScore;
        this.objectiveScore = objectiveScore;
        this.score = score;
        this.isRead = isRead;
        this.isSubmit = isSubmit;
        this.isEnter = isEnter;
        this.subjectiveDetails = subjectiveDetails;
        this.objectiveDetails = objectiveDetails;
    }

    public Exam() {
    }

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectiveScore() {
        return subjectiveScore;
    }

    public void setSubjectiveScore(String subjectiveScore) {
        this.subjectiveScore = subjectiveScore;
    }

    public String getObjectiveScore() {
        return objectiveScore;
    }

    public void setObjectiveScore(String objectiveScore) {
        this.objectiveScore = objectiveScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getIsEnter() {
        return isEnter;
    }

    public void setIsEnter(String isEnter) {
        this.isEnter = isEnter;
    }

    public String getSubjectiveDetails() {
        return subjectiveDetails;
    }

    public void setSubjectiveDetails(String subjectiveDetails) {
        this.subjectiveDetails = subjectiveDetails;
    }

    public String getObjectiveDetails() {
        return objectiveDetails;
    }

    public void setObjectiveDetails(String objectiveDetails) {
        this.objectiveDetails = objectiveDetails;
    }

    @Override
    public String toString() {
        return paperNo + ',' + studentNo + ',' + subjectiveScore + ',' + objectiveScore + ',' + score + ','
                + isRead + ',' + isSubmit + ',' + isEnter + ',' + subjectiveDetails + ',' + objectiveDetails;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(paperNo);
        dataOutput.writeUTF(studentNo);
        dataOutput.writeUTF(subjectiveScore);
        dataOutput.writeUTF(objectiveScore);
        dataOutput.writeUTF(score);
        dataOutput.writeUTF(isRead);
        dataOutput.writeUTF(isSubmit);
        dataOutput.writeUTF(isEnter);
        dataOutput.writeUTF(subjectiveDetails);
        dataOutput.writeUTF(objectiveDetails);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.paperNo = dataInput.readUTF();
        this.studentNo = dataInput.readUTF();
        this.subjectiveScore = dataInput.readUTF();
        this.objectiveScore = dataInput.readUTF();
        this.score = dataInput.readUTF();
        this.isRead = dataInput.readUTF();
        this.isSubmit = dataInput.readUTF();
        this.isEnter = dataInput.readUTF();
        this.subjectiveDetails = dataInput.readUTF();
        this.objectiveDetails = dataInput.readUTF();

    }
}
