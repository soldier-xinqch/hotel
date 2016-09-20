package org.hotel.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 这样此类即完成了PropertyPlaceholderConfigurer的任务，
 * 同时又提供了上下文properties访问的功能。
 * 于是在Spring配置文件中把PropertyPlaceholderConfigurer
 * 改成CustomizedPropertyConfigurer
 * @author xinqch
 */
public class PropertiesUtils extends PropertyPlaceholderConfigurer {
	
	private static Map<String, Object> ctxPropertiesMap;  
	  
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,  
            Properties props)throws BeansException {  
        super.processProperties(beanFactory, props);  
        //load properties to ctxPropertiesMap  
        ctxPropertiesMap = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
    }  
  
    //static method for accessing context properties  
    public static Object getPropertyParams(String name) {  
        return ctxPropertiesMap.get(name);  
    }  
}
