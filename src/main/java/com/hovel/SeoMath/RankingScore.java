package com.hovel.SeoMath;

import com.hovel.SeoMath.impl.*;
import com.hovel.SeoMath.seokeywords.KeywordsStrategy;
import com.hovel.SeoMath.seokeywords.impl.PerfectMatchStrategy;

import java.math.BigDecimal;
import java.util.Map;

/**
 * SEO商品排名得分
 */
public class RankingScore {

    /**
     *
     * @param keywordsScore 关键词排名得分
     * @param commodityPerforScore 商品绩效得分
     * @return SEO商品排名的分
     */
    public BigDecimal getSEORankScore(BigDecimal keywordsScore, BigDecimal commodityPerforScore){
        return keywordsScore.multiply(new BigDecimal(0.4)).add(commodityPerforScore.multiply(new BigDecimal(0.06)));
    }

    /**
     * SEO关键词排名得分
     * @param params
     * @return
     */
    public BigDecimal getKeywordsScore(Map params){
        KeywordsStrategy perfect = new PerfectMatchStrategy();
        return perfect.getSeaechScore((BigDecimal)params.get("ratio"));
    }

    /**
     * 获取 商品绩效得分
     * @param params
     * @return
     */
    public BigDecimal getCommodityPerforScore(Map params){
        CommodityScoreStrategy clickRate = new ClickRateStrategy();
        BigDecimal clickRateScore = clickRate.compute((BigDecimal)params.get("clickRateParam"),(BigDecimal)params.get("averageClickRate"));

        CommodityScoreStrategy clickCount = new ClickCountStrategy();
        BigDecimal clickCountScore = clickCount.compute((BigDecimal)params.get("clickCountParam"),(BigDecimal)params.get("averageclickCount"));

        CommodityScoreStrategy conversionRate = new ConversionRateStrategy();
        BigDecimal conversionRateScore = conversionRate.compute((BigDecimal)params.get("conversionRateParam"),(BigDecimal)params.get("averageConversionRate"));

        CommodityScoreStrategy conversionCount = new ConversionCountStrategy();
        BigDecimal conversionCountScore = conversionCount.compute((BigDecimal)params.get("conversionCountParam"),(BigDecimal)params.get("averageConversionCount"));

        GuaranteeStrategy guaranteeStrategy = new GuaranteeStrategy();
        BigDecimal guaranteeScore = guaranteeStrategy.getScore((boolean)params.get("guarantee"));

        RefundRateStrategy refundRateStrategy = new RefundRateStrategy();
        BigDecimal refundRateScore = refundRateStrategy.getScore((BigDecimal)params.get("refundRate"));

        return clickRateScore.add(clickCountScore).add(conversionRateScore).add(conversionCountScore).add(guaranteeScore).add(refundRateScore);
    }
}
