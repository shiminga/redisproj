package com.api.util;

public class HttpContext {
    public static ThreadLocal<String> tl=new ThreadLocal<>();

    public static void setSessionid(String sessionid){
        tl.set(sessionid);
    }

    public static String getSessionid(){
        return tl.get();
    }

    public static void removeSessionid(){
        tl.remove();
    }
}
