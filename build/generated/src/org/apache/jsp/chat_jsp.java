package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class chat_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!--\n");
      out.write("  Licensed to the Apache Software Foundation (ASF) under one or more\n");
      out.write("  contributor license agreements.  See the NOTICE file distributed with\n");
      out.write("  this work for additional information regarding copyright ownership.\n");
      out.write("  The ASF licenses this file to You under the Apache License, Version 2.0\n");
      out.write("  (the \"License\"); you may not use this file except in compliance with\n");
      out.write("  the License.  You may obtain a copy of the License at\n");
      out.write("\n");
      out.write("      http://www.apache.org/licenses/LICENSE-2.0\n");
      out.write("\n");
      out.write("  Unless required by applicable law or agreed to in writing, software\n");
      out.write("  distributed under the License is distributed on an \"AS IS\" BASIS,\n");
      out.write("  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n");
      out.write("  See the License for the specific language governing permissions and\n");
      out.write("  limitations under the License.\n");
      out.write("-->\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <title>Apache Tomcat WebSocket Examples: Chat</title>\n");
      out.write("    <script src=\"js/jquery.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script src=\"js/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script src=\"/js/layer.js\" type=\"text/javascript\"></script>\n");
      out.write("    <style type=\"/text/css\"><![CDATA[\n");
      out.write("        input#chat {\n");
      out.write("            width: 410px\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #console-container {\n");
      out.write("            width: 400px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #console {\n");
      out.write("            border: 1px solid #CCCCCC;\n");
      out.write("            border-right-color: #999999;\n");
      out.write("            border-bottom-color: #999999;\n");
      out.write("            height: 170px;\n");
      out.write("            overflow-y: scroll;\n");
      out.write("            padding: 5px;\n");
      out.write("            width: 100%;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #console p {\n");
      out.write("            padding: 0;\n");
      out.write("            margin: 0;\n");
      out.write("        }\n");
      out.write("    ]]></style>\n");
      out.write("    <script type=\"application/javascript\">\n");
      out.write("        var str = 'ws://' + window.location.host + '/examples/websocket/chat';\n");
      out.write("        Console.log(str);\n");
      out.write("\n");
      out.write("        var Chat = {};\n");
      out.write("        Chat.socket = null;\n");
      out.write("        Chat.connect = (function(host) {\n");
      out.write("            if ('WebSocket' in window) {\n");
      out.write("                Chat.socket = new WebSocket(host);\n");
      out.write("            } else if ('MozWebSocket' in window) {\n");
      out.write("                Chat.socket = new MozWebSocket(host);\n");
      out.write("            } else {\n");
      out.write("                Console.log('Error: WebSocket is not supported by this browser.');\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            Chat.socket.onopen = function () {\n");
      out.write("                Console.log('Info: WebSocket connection opened.');\n");
      out.write("                document.getElementById('chat').onkeydown = function(event) {\n");
      out.write("                    if (event.keyCode == 13) {\n");
      out.write("                        Chat.sendMessage();\n");
      out.write("                    }\n");
      out.write("                };\n");
      out.write("            };\n");
      out.write("\n");
      out.write("            Chat.socket.onclose = function () {\n");
      out.write("                document.getElementById('chat').onkeydown = null;\n");
      out.write("                Console.log('Info: WebSocket closed.');\n");
      out.write("            };\n");
      out.write("            Chat.socket.onmessage = function (message) {\n");
      out.write("//                var m = JSON.parse(message.data);\n");
      out.write("//                alert(m.content);\n");
      out.write("                Console.log(message.data);\n");
      out.write("            };\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        Chat.initialize = function() {\n");
      out.write("            if (window.location.protocol == 'http:') {\n");
      out.write("                Chat.connect('ws://' + window.location.host + '/examples/websocket/chat');\n");
      out.write("            } else {\n");
      out.write("                Chat.connect('wss://' + window.location.host + '/examples/websocket/chat');\n");
      out.write("            }\n");
      out.write("        };\n");
      out.write("\n");
      out.write("        Chat.sendMessage = (function() {\n");
      out.write("            var name = getQueryString(\"name\");\n");
      out.write("            var message = document.getElementById('chat').value;\n");
      out.write("            var msg =JSON.stringify({\n");
      out.write("              type: 1,\n");
      out.write("              from: name,\n");
      out.write("              to: \"\",\n");
      out.write("              str: message\n");
      out.write("            });\n");
      out.write("            if (message != '') {\n");
      out.write("                Chat.socket.send(msg);\n");
      out.write("                document.getElementById('chat').value = '';\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        var Console = {};\n");
      out.write("\n");
      out.write("        Console.log = (function(message) {\n");
      out.write("            var console = document.getElementById('console');\n");
      out.write("            var p = document.createElement('p');\n");
      out.write("            p.style.wordWrap = 'break-word';\n");
      out.write("            p.innerHTML = message;\n");
      out.write("            console.appendChild(p);\n");
      out.write("            while (console.childNodes.length > 25) {\n");
      out.write("                console.removeChild(console.firstChild);\n");
      out.write("            }\n");
      out.write("            console.scrollTop = console.scrollHeight;\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        Chat.initialize();\n");
      out.write("\n");
      out.write("\n");
      out.write("        document.addEventListener(\"DOMContentLoaded\", function() {\n");
      out.write("            // Remove elements with \"noscript\" class - <noscript> is not allowed in XHTML\n");
      out.write("            var noscripts = document.getElementsByClassName(\"noscript\");\n");
      out.write("            for (var i = 0; i < noscripts.length; i++) {\n");
      out.write("                noscripts[i].parentNode.removeChild(noscripts[i]);\n");
      out.write("            }\n");
      out.write("        }, false);\n");
      out.write("        \n");
      out.write("        function getQueryString(name) {\n");
      out.write("    var result = window.location.search.match(new RegExp(\"[\\?\\&]\" + name + \"=([^\\&]+)\", \"i\"));\n");
      out.write("    if (result == null || result.length < 1) {\n");
      out.write("        return \"\";\n");
      out.write("    }\n");
      out.write("    return result[1];\n");
      out.write("}\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"noscript\"><h2 style=\"color: #ff0000\">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable\n");
      out.write("    Javascript and reload this page!</h2></div>\n");
      out.write("<div>\n");
      out.write("    <p>\n");
      out.write("        <input type=\"text\" placeholder=\"type and press enter to chat\" id=\"chat\" />\n");
      out.write("    </p>\n");
      out.write("    <div id=\"console-container\">\n");
      out.write("        <div id=\"console\"/>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}