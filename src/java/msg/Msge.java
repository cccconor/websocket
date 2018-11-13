/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msg;
import test.chat;

/**
 *
 * @author ZJX
 */
public class Msge {
    private int type;
    private String from;
    private String to;
    private String content;
    private chat client;
    
    public Msge()
    {
        this.type = 0;
        this.from = "";
        this.to = "";
        this.content = "";
        this.client = null;
    }
    
    public void setType(int i)
    {
        this.type = i;
    }
    public void setFrom(String s)
    {
        this.from = s;
    }
    public void setTo(String s)
    {
        this.to = s;
        
    }
    public void setContent(String s)
    {
        this.content = s;
    }
    public void setClient(chat c)
    {
        this.client = c;
    }
    
    
    
    public int getType()
    {
        return type;
    }
    public String getFrom()
    {
        return from;
    }
    public String getTo()
    {
        return to; 
    }
    public String getContent()
    {
        return content;
    }
    public chat getClient()
    {
        return client;
    }
}
