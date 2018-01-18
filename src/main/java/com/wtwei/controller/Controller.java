package com.wtwei.controller;

import com.wtwei.JsonUtil;
import com.wtwei.SearchAnswer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wtwei .
 * @date 2018/1/17 .
 * @time 15:32 .
 */
@RestController
public class Controller {
    Log logger = LogFactory.getLog(Controller.class);
    
    @RequestMapping(method = RequestMethod.GET, path = "/tnwz")
    public void tnwz(@RequestParam("question")String param){
        Map map = (Map) com.wtwei.JsonUtil.deserialize(param);
        Map data = (Map) map.get("data");

        String question = (String) data.get("quiz");
        List options = (List) data.get("options");

        SearchAnswer.search(question, options);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/cddh")
    public void cddh(@RequestParam("question")String param){
        Map map = (Map) JsonUtil.deserialize(param);
        Map dataMap = (Map) map.get("data");
        String question = (String) dataMap.get("desc");
        question = question.split(".")[1];
        
        String optionsStr = (String) dataMap.get("options");
        List options = (List) JsonUtil.deserialize(optionsStr);
        
        SearchAnswer.search(question, options);
    }
}
