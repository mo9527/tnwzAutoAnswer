package com.wtwei;


import com.google.gson.internal.LinkedHashTreeMap;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wtwei .
 * @date 2018/1/17 .
 * @time 17:36 .
 */
public class SearchAnswer {
    
    

    public static String search(String question, List daixuan) {
        try{
            StringBuffer sb = new StringBuffer();
            for (Object object : daixuan) {
                sb.append(" ").append(object);
            }
            
            String url = "https://m.sogou.com/web/searchList.jsp?keyword=" + question;
            OkHttpClient okHttpClient = new OkHttpClient();
            String html = okHttpClient.newCall(new Request.Builder()
                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                    .url(url).get().build()).execute().body().string();
            Document doc = Jsoup.parse(html);

            String result = doc.text();
            System.out.println("---------------------------我是分割线----------------------");

            System.out.println("问题： " + question);
            
            int index = 0;
            int count = 0;
            int tmp = 0;
            for (int i = 0; i < daixuan.size(); i++) {
                String option = daixuan.get(i).toString();
                
                count = wordCount(result, option);
//                if (count >= tmp){
//                    index = i;
//                }
//                resultMap.put(option, count);

                System.out.println(option + "="+ count);
            }

//            int tmpIndex = 0;
//            for (int i = 0; i < daixuan.size(); i++) {
//                String option = daixuan.get(i).toString();
//                Integer countValue = resultMap.get(option);
//
//                tmpIndex ++;
//                if (tmpIndex == index){
//                    System.err.println((i + 1) + " : "+option);
//                }else {
//                    System.out.println((i + 1) + " : "+option);
//                }
//            }
            
            
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
    
    private static int wordCount(String source, String keyWord){
        Pattern p = Pattern.compile(keyWord);
        //使用Matcher进行各种查找替换操作  
        Matcher m = p.matcher(source);
        int i = 0;
        while(m.find()){
            i++;
        }

        return i;
    }

    public static void main(String[] args) {
        wordCount(null, null);
    }

}
