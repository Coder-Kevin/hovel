package com.hovel.SeoMath;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SeoScoreMain {
    public static void main(String[] args){
        Map params = new HashMap();

        params.put("word","组合床");
        params.put("clickRateParam",new BigDecimal(0.2339));
        params.put("clickCountParam",new BigDecimal(586));
        params.put("conversionRateParam",new BigDecimal(0.1092));
        params.put("conversionCountParam",new BigDecimal(64));
        //TODO 参数待补充
        params.put("guarantee",true);
        params.put("refundRate",new BigDecimal(0.2));
        params.put("averageClickRate",new BigDecimal(1.2));
        params.put("averageclickCount",new BigDecimal(1.2));
        params.put("averageConversionRate",new BigDecimal(1.2));
        params.put("averageConversionCount",new BigDecimal(1.2));
        params.put("ratio",new BigDecimal(5.20));

        RankingScore rankingScore = new RankingScore();
        BigDecimal value = rankingScore.getSEORankScore(rankingScore.getKeywordsScore(params),rankingScore.getCommodityPerforScore(params));
        value.setScale(6,BigDecimal.ROUND_HALF_UP);
        System.out.println("关键词："+params.get("word")+"，SEO商品排名得分:"+value.setScale(6,BigDecimal.ROUND_HALF_UP));
    }
}
