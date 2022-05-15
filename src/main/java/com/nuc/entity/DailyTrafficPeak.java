package com.nuc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 福尔摩东
 * @date 2022/5/15 11:02
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 * 日报访问高峰期
 */
@Component
@Data
@NoArgsConstructor
public class DailyTrafficPeak {
    private int id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private int hour;
    private int count;
}
