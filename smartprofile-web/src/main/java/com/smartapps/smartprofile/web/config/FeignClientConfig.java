package com.smartapps.smartprofile.web.config;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.smartapps.smartlib.util.SmartHttpUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FeignClientConfig implements RequestInterceptor{

	@Override
	public void apply(RequestTemplate template) {
        try {
            Map<String,String> headers = getHeaders();
            System.out.println(">>> headers: "+ headers.toString() );
            for(String headerName : headers.keySet()){
            	template.header(headerName, headers.get(headerName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
    private Map<String, String> getHeaders(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toUpperCase();
            String value = request.getHeader(key);
//            if ("content-length".equals(key)) {
//                continue;
//            }
            if(SmartHttpUtil.APP_ID_HEADER.equals(key) 
            		|| SmartHttpUtil.USER_ID_HEADER.equals(key)
            		|| SmartHttpUtil.USER_GROUPS_HEADER.equals(key)
            		|| SmartHttpUtil.AUTH_HEADER.equals(key)) {
            	map.put(key, value);
            }
            //map.put(key, value);
        }
        return map;
    }

}
