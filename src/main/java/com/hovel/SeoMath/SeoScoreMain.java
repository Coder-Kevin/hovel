package com.hovel.SeoMath;

import com.hovel.SeoMath.bean.SeoParamBean;
import com.hovel.SeoMath.bean.SeoResultBean;
import com.hovel.excel.ExcelUtil;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

public class SeoScoreMain {
    public static void main(String[] args) {

        List<SeoResultBean> result = new ArrayList<>();

        Map params = new HashMap();
        //TODO 不确定参数待补充
        params.put("guarantee", true);//保修
        params.put("refundRate", new BigDecimal(0.2));//退单率

        //需要从excel数据中算取以下参数
        params.put("averageClickRate", new BigDecimal(1.2));//平均点击率，数据可以算取
        params.put("averageClickCount", new BigDecimal(1.2));//平均点击量，数据可以算取
        params.put("averageConversionRate", new BigDecimal(1.2));//平均转化率，数据可以算取
        params.put("averageConversionCount", new BigDecimal(1.2));//平均转化量，数据可以算取

        String excelPath = "C:\\Users\\Kevin\\Desktop\\数据魔方.xlsx";
        List<SeoParamBean> data = ExcelUtil.read(excelPath);
        if (!CollectionUtils.isEmpty(data)) {
            for (SeoParamBean bean : data) {
                RankingScore rankingScore = new RankingScore();
                BigDecimal value = rankingScore.getSEORankScore(rankingScore.getKeywordsScore(bean),
                        rankingScore.getCommodityPerforScore(bean, params));
                value.setScale(6, BigDecimal.ROUND_HALF_UP);

                SeoResultBean resultBean = new SeoResultBean();
                resultBean.setKeywords(bean.getKeyword());
                resultBean.setPoint(value.setScale(6, BigDecimal.ROUND_HALF_UP));
                result.add(resultBean);
            }
        }

        //进行倒序
        result.sort((o1, o2) -> o1.getPoint().compareTo(o2.getPoint()) ==1 ? -1 : 1);
        if(!CollectionUtils.isEmpty(result)){
            for(int i = 0;i<result.size();i++){
                SeoResultBean seoResultBean = result.get(i);
                System.out.println("分数名次:"+(i+1)+",关键词：" + seoResultBean.getKeywords() + "，SEO商品排名得分:" + seoResultBean.getPoint());
            }
        }
    }

}
