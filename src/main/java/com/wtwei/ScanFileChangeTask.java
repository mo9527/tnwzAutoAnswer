package com.wtwei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wtwei .
 * @date 2018/1/17 .
 * @time 17:20 .
 */
public class ScanFileChangeTask extends Thread{
    long tmpTime = 0;
    long tmpTime2 = 0;
    
    @Override
    public void run(){
       
        while (true){
            File file = new File("D:\\question\\test\\tnwz.json");
            if (file == null){
                continue;
            }
            long modifyTime = file.lastModified();
            if (modifyTime != tmpTime){
                tmpTime = modifyTime;
                tnwz(file);
            }

            File file2 = new File("D:\\question\\test\\cddh.json");
            if (file2 == null){
                continue;
            }
            long modifyTime2 = file2.lastModified();
            if (modifyTime2 != tmpTime2){
                tmpTime2 = modifyTime2;
                cddh(file2);
            }
            
        }
        
    }
    
    private void tnwz(File file){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if (line == null){
                return;
            }

            Map map = (Map) com.wtwei.JsonUtil.deserialize(line);
            Map data = (Map) map.get("data");
            if (data == null) {
                return;
            }

            String question = (String) data.get("quiz");
            List options = (List) data.get("options");

            SearchAnswer.search(question, options);

            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void cddh(File file){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if (line == null){
                return;
            }
            
            if(line.length() < 20){
                return;
            }
            
            Map map = (Map) JsonUtil.deserialize(line);
            Map dataMap = (Map) map.get("data");
            if (dataMap == null){
                return;
            }
            Map eventMap = (Map) dataMap.get("event");
            if (eventMap == null){
                return;
            }
            String question = (String) eventMap.get("desc");
            question = question.split("\\.")[1];

            String optionsStr = (String) eventMap.get("options");
            List options = (List) JsonUtil.deserialize(optionsStr);

            SearchAnswer.search(question, options);

            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
