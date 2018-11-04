package com.hovel.SeoMath;

import java.math.BigDecimal;

/**
 * 商品绩效得分接口
 */
public interface CommodityScoreStrategy {
    /**
     *
     * @param param 参数
     * @param average 平均值
     * @return 得分
     */
    BigDecimal compute(BigDecimal param, BigDecimal average);
}
