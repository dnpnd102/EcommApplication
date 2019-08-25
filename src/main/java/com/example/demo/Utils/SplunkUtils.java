//package com.example.demo.Utils;
//
//import com.splunk.Receiver;
//import com.splunk.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SplunkUtils {
//
//    private static Service service;
//
//    public SplunkUtils() {
//
//        Map<String, Object> connectionArgs = new HashMap<String, Object>();
//        connectionArgs.put("host", "localhost");
//        connectionArgs.put("username", "admin");
//        connectionArgs.put("password", "changeme");
//        connectionArgs.put("port", 8000);
//        connectionArgs.put("scheme", "https");
//        service = Service.connect(connectionArgs);
//    }
//
//    public static Receiver getReciever(){
//        return  service.getReceiver();
//    }
//}
