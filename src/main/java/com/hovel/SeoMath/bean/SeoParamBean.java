package com.hovel.SeoMath.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 数据魔方参数实体类
 */
@Data
public class SeoParamBean extends BaseRowModel {

    //序号
    @ExcelProperty(index = 0)
    private String id;

    //关键词
    @ExcelProperty(index = 1)
    private String keyword;

    //展现量
    @ExcelProperty(index = 2)
    private BigDecimal viewCounts;

    //点击量
    @ExcelProperty(index = 3)
    private BigDecimal chickCounts;

    //转化量
    @ExcelProperty(index = 4)
    private BigDecimal transferCounts;

    //点击率
    @ExcelProperty(index = 5)
    private BigDecimal clickRate;

    //转化率
    @ExcelProperty(index = 6)
    private BigDecimal transferRate;

    //点击花费
    @ExcelProperty(index = 7)
    private BigDecimal clickMoney;

    //平均点击单价
    @ExcelProperty(index = 8)
    private BigDecimal avgClickMoney;

    //搜索相关性
    @ExcelProperty(index = 9)
    private BigDecimal searchCorrelation;

}
