package com.nuc.controller;

import com.nuc.entity.*;
import com.nuc.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/15 10:32
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
@RestController
public class ExamController {
    @Autowired
    ExamService examService;

    @GetMapping("/exam/selectExamRatio")
    public List<ExamRatio> selectExamRatio() throws SQLException {
        return examService.selectExamRatio();
    }
    @GetMapping("/exam/selectExamSubjectiveScore")
    List<ExamSubjectiveScore> selectExamSubjectiveScore() throws SQLException{
        return examService.selectExamSubjectiveScore();
    };
    @GetMapping("/exam/selectExamObjectiveScore")
    List<ExamObjectiveScore> selectExamObjectiveScore() throws SQLException{
        return examService.selectExamObjectiveScore();
    };
    @GetMapping("/exam/selectExamScore")
    List<ExamScore> selectExamScore() throws SQLException{
        return examService.selectExamScore();
    }
    @GetMapping("/exam/selectExamCount")
    List<ExamCount> selectExamCount() throws SQLException{
        return examService.selectExamCount();
    }
    @GetMapping("/exam/selectExamCountStudent")
    List<ExamCountStudent> selectExamCountStudent() throws SQLException{
        return examService.selectExamCountStudent();
    }
}
