# tnwzAutoAnswer

## 1、设置fiddler代理：
 手机和电脑在同一局域网下，设置手机wifi代理为电脑ip，端口号默认是8888
## 1、fiddlerscript中加入代码：
      /**
        头脑王者
        */
        if(oSession.HostnameIs("question.hortor.net") && oSession.PathAndQuery == "/question/bat/findQuiz" ){
            var responseStr = oSession.GetResponseBodyAsString();
            FiddlerObject.log(responseStr);
            
            oSession.SaveResponseBody("D:/question/test/tnwz.json");
            
            //var client = new WebClient();
            
            //client.DownloadString("http://localhost:8765/tnwz?question="+responseStr);
        
        }
