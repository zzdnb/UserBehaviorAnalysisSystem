package com.nuc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 福尔摩东
 * @date 2022/5/15 11:11
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 * 周报与日报比例
 */
@Component
@Data
@NoArgsConstructor
public class DailyRatio {
    private int id;
    private int daily;
    private int week;
    private int sum;
}
