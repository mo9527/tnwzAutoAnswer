# 微信小游戏头脑王者辅助工具

## 1、设置fiddler代理：
 手机和电脑在同一局域网下，设置手机http代理为电脑ip，fiddler的默认端口号是8888
## 2、fiddlerscript中OnBeforeResponse方法里加入代码：
      /**
        头脑王者
        */
        if(oSession.HostnameIs("question.hortor.net") && oSession.PathAndQuery == "/question/bat/findQuiz" ){
            var responseStr = oSession.GetResponseBodyAsString();
            FiddlerObject.log(responseStr);
            oSession.SaveResponseBody("D:/question/test/tnwz.json");
            }
        
## 3、启动Application
## 4、输出项解释：
   正常题目选择等号后面数字最大的选项，如果是否定题目（例如，不包含，不是，不属于等）则选择数字最小的选项
