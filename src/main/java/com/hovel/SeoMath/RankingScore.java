package com.hovel.SeoMath;

import com.hovel.SeoMath.bean.SeoParamBean;
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
     * 获取SEO排名得分（SEO商品排名得分 = SEO关键词排名得分 + 商品绩效得分）
     * @param keywordsScore 关键词排名得分
     * @param commodityPerforScore 商品绩效得分
     * @return SEO商品排名的分
     */
    public BigDecimal getSEORankScore(BigDecimal keywordsScore, BigDecimal commodityPerforScore){
        return keywordsScore.multiply(new BigDecimal(0.4)).add(commodityPerforScore.multiply(new BigDecimal(0.06)));
    }

    /**
     * SEO关键词排名得分
     * @param seoParamBean
     * @return
     */
    public BigDecimal getKeywordsScore(SeoParamBean seoParamBean){
        KeywordsStrategy perfect = new PerfectMatchStrategy();
        return perfect.getSeaechScore(seoParamBean.getSearchCorrelation());
    }

    /**
     * 获取 商品绩效得分
     * @param seoParamBean
     * @param params
     * @return
     */
    public BigDecimal getCommodityPerforScore(SeoParamBean seoParamBean, Map params){
        CommodityScoreStrategy clickRate = new ClickRateStrategy();
        BigDecimal clickRateScore = clickRate.compute(seoParamBean.getClickRate(),(BigDecimal)params.get("averageClickRate"));

        CommodityScoreStrategy clickCount = new ClickCountStrategy();
        BigDecimal clickCountScore = clickCount.compute(seoParamBean.getChickCounts(),(BigDecimal)params.get("averageClickCount"));

        CommodityScoreStrategy conversionRate = new ConversionRateStrategy();
        BigDecimal conversionRateScore = conversionRate.compute(seoParamBean.getTransferRate(),(BigDecimal)params.get("averageConversionRate"));

        CommodityScoreStrategy conversionCount = new ConversionCountStrategy();
        BigDecimal conversionCountScore = conversionCount.compute(seoParamBean.getTransferCounts(),(BigDecimal)params.get("averageConversionCount"));

        GuaranteeStrategy guaranteeStrategy = new GuaranteeStrategy();
        BigDecimal guaranteeScore = guaranteeStrategy.getScore((boolean)params.get("guarantee"));

        RefundRateStrategy refundRateStrategy = new RefundRateStrategy();
        BigDecimal refundRateScore = refundRateStrategy.getScore((BigDecimal)params.get("refundRate"));

        return clickRateScore.add(clickCountScore).add(conversionRateScore).add(conversionCountScore).add(guaranteeScore).add(refundRateScore);
    }
}
