package com.dhcc.BaseUtil;

public class StringUtil {
    public static boolean isEmpty(String str){
        if(str.isEmpty()||str==null||str.equals("")){
            return true;
        }else return false;
    }
}
