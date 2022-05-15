package com.nuc.service;

import com.nuc.entity.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/12 19:44
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */

public interface ExamService {
    List<ExamRatio> selectExamRatio() throws SQLException;
    List<ExamSubjectiveScore> selectExamSubjectiveScore() throws SQLException;
    List<ExamObjectiveScore> selectExamObjectiveScore() throws SQLException;
    List<ExamScore> selectExamScore() throws SQLException;
    List<ExamCount> selectExamCount() throws SQLException;
    List<ExamCountStudent> selectExamCountStudent() throws SQLException;

}
