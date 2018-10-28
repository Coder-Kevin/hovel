package com.hovel.SeoMath;

import com.hovel.SeoMath.impl.*;
import com.hovel.SeoMath.seokeywords.KeywordsStrategy;
import com.hovel.SeoMath.seokeywords.impl.PerfectMatchStrategy;

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
    public double getSEORankScore(double keywordsScore, double commodityPerforScore){
        return keywordsScore*0.4 + commodityPerforScore*0.06;
    }

    /**
     * SEO关键词排名得分
     * @param params
     * @return
     */
    public double getKeywordsScore(Map params){
        KeywordsStrategy perfect = new PerfectMatchStrategy();
        return perfect.getSeaechScore((double)params.get("ratio"));
    }

    /**
     * 获取 商品绩效得分
     * @param params
     * @return
     */
    public double getCommodityPerforScore(Map params){
        CommodityScoreStrategy clickRate = new ClickRateStrategy();
        double clickRateScore = clickRate.compute((double)params.get("clickRateParam"),(double)params.get("averageClickRate"));

        CommodityScoreStrategy clickCount = new ClickCountStrategy();
        double clickCountScore = clickCount.compute((double)params.get("clickCountParam"),(double)params.get("averageclickCount"));

        CommodityScoreStrategy conversionRate = new ConversionRateStrategy();
        double conversionRateScore = conversionRate.compute((double)params.get("conversionRateParam"),(double)params.get("averageConversionRate"));

        CommodityScoreStrategy conversionCount = new ConversionCountStrategy();
        double conversionCountScore = conversionCount.compute((double)params.get("conversionCountParam"),(double)params.get("averageConversionCount"));

        GuaranteeStrategy guaranteeStrategy = new GuaranteeStrategy();
        double guaranteeScore = guaranteeStrategy.getScore((boolean)params.get("guarantee"));

        RefundRateStrategy refundRateStrategy = new RefundRateStrategy();
        double refundRateScore = refundRateStrategy.getScore((double)params.get("refundRate"));

        return clickRateScore + clickCountScore + conversionRateScore + conversionCountScore + guaranteeScore + refundRateScore;
    }
}
