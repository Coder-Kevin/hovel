package com.hovel.SeoMath;

import java.util.HashMap;
import java.util.Map;

public class SeoScoreMain {
    public static void main(String[] args){
        Map params = new HashMap();

        params.put("word","组合床");
        params.put("clickRateParam",0.2339);
        params.put("clickCountParam",586);
        params.put("conversionRateParam",0.1092);
        params.put("conversionCountParam",64);
        //TODO 参数待补充
//        params.put("guarantee","组合床");
//        params.put("refundRate","组合床");
//        params.put("averageClickRate","组合床");
//        params.put("averageclickCount","组合床");
//        params.put("averageConversionRate","组合床");
//        params.put("averageConversionCount","组合床");
        params.put("ratio",5.20);

        RankingScore rankingScore = new RankingScore();
        double value = rankingScore.getSEORankScore(rankingScore.getKeywordsScore(params),rankingScore.getCommodityPerforScore(params));
        System.out.println("关键词："+params.get("word")+"，SEO商品排名得分:"+value);
    }
}
