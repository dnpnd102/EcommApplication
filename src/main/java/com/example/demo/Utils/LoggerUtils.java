//package com.example.demo.Utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class LoggerUtils {
//
//    public static void logMessage(String message, String data){
//        try {
//            Map<String, String> dataMap = new HashMap<>();
//            dataMap.put("data", data);
//            dataMap.put("message", message);
//            SplunkUtils.getReciever().log("EcommApplication", dataMap.toString());
//        }catch (Exception e){
//
//        }
//    }
//}
