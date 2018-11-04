package com.hovel.basetest.testutil;

import com.hovel.SeoMath.bean.SeoParamBean;
import com.hovel.excel.ExcelUtil;

import java.util.List;

public class TestExcelUtil {
    public static void main(String[] args){
        String path = "C:\\Users\\Kevin\\Desktop\\数据魔方.xlsx";
        List<SeoParamBean> result = ExcelUtil.read(path);
        System.out.println(result.size());
    }
}
