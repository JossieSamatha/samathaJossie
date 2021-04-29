package com.tutor.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

@Controller
@ConfigurationProperties(value = "jdbc")
public class SellApi {
    private String username;
    private String password;
    public String getMsgValue() {
        return username+"|"+password;
    }

    public String index(){
        return"redirect:/index";
    }

    /**
     * restful风格
     * @param moduleName
     * @return String
     */
    public String getView(  String moduleName){
        return moduleName;
    }

}
