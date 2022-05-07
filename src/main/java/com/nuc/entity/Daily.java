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
 * @date 2022/5/2 8:57
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 * 日报实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
