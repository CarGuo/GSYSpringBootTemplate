package com.shuyu.spring.template.module.user.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日期数据统计
 */
@Data
public class UserDateStatistics implements Serializable {

    private int count;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date day;

}
