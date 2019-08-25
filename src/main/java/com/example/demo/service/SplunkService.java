package com.example.demo.service;
import com.splunk.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SplunkService {

    public void service(){

        /*
        Map<String, Object> connectionArgs = new HashMap<String, Object>();
        connectionArgs.put("host","localhost");
        connectionArgs.put("username","admin");
        connectionArgs.put("password","changeme");
        connectionArgs.put("port",8000);
        connectionArgs.put("scheme","https");
        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
        try {
            Service service = Service.connect(connectionArgs);
            for (Application app : service.getApplications().values()) {
                System.out.println(app.getName());
            }
        }catch (Exception e){
            System.currentTimeMillis();
        }
        */

        /*
        Service service = new Service("localhost", 8000);
        String credentials = "admin:Dina1996@";
        String basicAuthHeader = Base64.encode(credentials.getBytes());
        service.setToken("Basic " + basicAuthHeader);

        // Print the session token
        System.out.println("Your session token: " + service.getToken());

        // Print installed apps to the console to verify login
        for (Application app : service.getApplications().values()) {
            System.out.println(app.getName());
        } */
        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
        ServiceArgs loginArgs = new ServiceArgs();
        loginArgs.setUsername("admin");
        loginArgs.setPassword("changeme");
        loginArgs.setHost("localhost");
        loginArgs.setPort(8000);
        loginArgs.setScheme("https");
        //loginArgs.
        try {
            Service service = Service.connect(loginArgs);
            for (Application app : service.getApplications().values()) {
                System.out.println(app.getName());
            }
        }catch (Exception e){
            System.currentTimeMillis();
        }

    }
}
