package com.nuc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 福尔摩东
 * @date 2022/5/15 11:18
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 *  提交周报次数最多的班级：
 */
@Component
@Data
@NoArgsConstructor
public class WeekCount {
    private int id;
    private int classNo;
    private int count;
}
