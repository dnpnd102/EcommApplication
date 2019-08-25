//package com.example.demo.service;
//import com.splunk.*;
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SplunkService {
//
//    private static Service service;
//
//    public SplunkService() {
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
