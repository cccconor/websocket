<%-- 
    Document   : main
    Created on : 2018-11-16, 16:06:59
    Author     : ZJX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
      <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
      <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">-->

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
          <script type="application/javascript">
        var str = 'ws://' + window.location.host + '/examples/websocket/chat';
//        Console.log(str);
        var name = getQueryString("name");
        var Chat = {};
        Chat.socket = null;
        Chat.connect = (function(host) {
            if ('WebSocket' in window) {
                Chat.socket = new WebSocket(host);
            } else if ('MozWebSocket' in window) {
                Chat.socket = new MozWebSocket(host);
            } else {
                Console.log('Error: WebSocket is not supported by this browser.');
                return;
            }

            Chat.socket.onopen = function () {
                Console.log('Info: WebSocket connection opened.');
                onlineTip();
                document.getElementById('chat').onkeydown = function(event) {
                    if (event.keyCode == 13) {
                        Chat.sendMessage();
                    }
                };
            };

            Chat.socket.onclose = function () {
                document.getElementById('chat').onkeydown = null;
                Console.log('Info: WebSocket closed.');
            };
            
            
            Chat.socket.onmessage = function (message) {
                var m = JSON.parse(message.data);
                if(parseInt(m.type)==0)
                {
                    addOne(m.from);
                    var msg = m.from.toString() + "上线了";
                    Console.log(msg);
                }else
                    
                if(parseInt(m.type)==-1)
                {
                    var msg = m.from.toString() + "下线了" ;
                    Console.log(msg);
                }else if(parseInt(m.type)==3)
                {
                    var msg = m.from.toString() + "对你说：" + m.content.toString() ;
//                    Console.log(msg);
                    addMsgList(msg);

                }else
                {
                    var msg = m.from.toString() + "对大家说： " + m.content.toString() ;
                    Console.log(msg);
                }
//                alert(m.content);

//                Console.log(message.data);
            };
        });

        Chat.initialize = function() {
            if (window.location.protocol == 'http:') {
                Chat.connect('ws://' + window.location.host + '/websocket/chat');
            } else {
                Chat.connect('wss://' + window.location.host + '/websocket/chat');
            }
        };

        Chat.sendMessage = (function() {            
            var message = document.getElementById('chat').value;
            var msg =JSON.stringify({
              type: 1,
              from: name,
              to: "",
              content: message
            });
            if (message != '') {
                Chat.socket.send(msg);
                document.getElementById('chat').value = '';
            }
        });

        var Console = {};

        Console.log = (function(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.innerHTML = message;
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        });
        
        function addMsgList(msg)
        {
            var console = document.getElementById('console1');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.innerHTML = msg;
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
        }
            

        Chat.initialize();


        document.addEventListener("DOMContentLoaded", function() {
            // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
            var noscripts = document.getElementsByClassName("noscript");
            for (var i = 0; i < noscripts.length; i++) {
                noscripts[i].parentNode.removeChild(noscripts[i]);
            }
        }, false);
        
        onlineTip = function()
        {
            var msg =JSON.stringify({
              type:0,
              from: name,
              to: "",
              content:"online"
            });
            Chat.socket.send(msg);
        }
        
        
        function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}
        function addOne(name)
        {
            var console = document.getElementById('friendlist');
            var p = document.createElement('input');
            var l = document.createElement("label");
            l.style = "display:inline";
            l.name = "'"+name+"'" ;
            l.innerHTML = name + "<br>";
            p.type = "radio";
            p.name = "list";
            p.vaue = "'"+name+"'";
//            p.style.wordWrap = 'break-word';
//            p.innerHTML = name;
            console.appendChild(p);
            console.appendChild(l);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }
        
        function sendone()
        {
//            alert("sendone");
            var toname = check();
            console.log("选中的人："+toname);
//            alert(toname);
            var str = document.getElementById("sendtoone").value;
//            alert(toname+str);
            if(str==null||str=="")
            {
                alert("输入的消息为空");
                return;
            }
            var msg =JSON.stringify({
              type: 3,
              from: name,
              to: toname,
              content: str
            });
            
            console.log("发送的消息："+msg);
            if (str != '') {
                Chat.socket.send(msg);
                document.getElementById('sendtoone').value = '';
            }
            
        }
        
        function check(){
	var radio = document.getElementsByName("list");
	for (i=0; i<radio.length; i++) {
		if (radio[i].checked) {
			return(radio[i].innerHTML)
		}
	}
}
    </script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-center">
				websocket实现的简易聊天系统
			</h3>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<h2>
				私聊消息
			</h2>
			<p>
				这里显示的是私聊消息：
			</p>
			<div id="console-container1">
				<div id="console1">
                                                                            </div>

			</div>

			
                                                        <h3>在线用户：</h3>
                            
			<div id="friendlist">
			</div>
                            
                                <form class="form-search">
                                    <div id="sendToOne">
				<input class="input-medium search-query" type="text" id="sendtoone" /> 
                                <input value="发送消息"  type="button"class="btn" onclick="sendone()"/>
			</div>
				
			</form>
		</div>
		<div class="span6">
			<h2>
				群聊消息
			</h2>
			<p>
				这里显示的是群聊消息
			</p>
			<div id="console-container">
				<div id="console">
                                                                            </div>

			</div>
			<form class="form-search">
				<input class="input-medium search-query" type="text" id="chat" /> 
                                <input type="button" value="发送消息" class="btn" onclick="Chat.sendMessage()"/>
			</form>
		</div>
	</div>
</div>
</body>
</html>
