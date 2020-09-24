package com.tan.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class SessionUtils {

    public static void setAttribute(HttpServletRequest req,String sessionName,String val){
        req.getSession().setAttribute(sessionName,val);
    }

    public static boolean hasAttribute(HttpServletRequest req,String sessionName){
        Object attribute = req.getSession().getAttribute(sessionName);
        return attribute != null;
    }

    public static Object getVal(HttpServletRequest req,String sessionName){
        if (hasAttribute(req,sessionName)){
            return req.getSession().getAttribute(sessionName);
        }
        return null;
    }

    public static void removeAttribute(HttpServletRequest req,String sessionName){
        req.getSession().removeAttribute(sessionName);
    }

    public static void removeAll(HttpServletRequest req){
        Enumeration<String> names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()){
            String s = names.nextElement();
            req.getSession().removeAttribute(s);
        }
    }
}
