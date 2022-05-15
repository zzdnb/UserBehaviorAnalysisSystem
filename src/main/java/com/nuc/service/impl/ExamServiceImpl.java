package com.nuc.service.impl;

import com.nuc.entity.*;
import com.nuc.mapper.ExamMapper;
import com.nuc.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/12 19:45
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamMapper examMapper;
    @Override
    public List<ExamRatio> selectExamRatio() throws SQLException {
        return examMapper.selectExamRatio();
    }

    @Override
    public List<ExamSubjectiveScore> selectExamSubjectiveScore() throws SQLException {
        return examMapper.selectExamSubjectiveScore();
    }

    @Override
    public List<ExamObjectiveScore> selectExamObjectiveScore() throws SQLException {
        return examMapper.selectExamObjectiveScore();
    }

    @Override
    public List<ExamScore> selectExamScore() throws SQLException {
        return examMapper.selectExamScore();
    }

    @Override
    public List<ExamCount> selectExamCount() throws SQLException {
        return examMapper.selectExamCount();
    }

    @Override
    public List<ExamCountStudent> selectExamCountStudent() throws SQLException {
        return examMapper.selectExamCountStudent();
    }
}
