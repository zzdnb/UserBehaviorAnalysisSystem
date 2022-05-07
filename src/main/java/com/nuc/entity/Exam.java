package com.nuc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam implements Serializable, Writable {
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
