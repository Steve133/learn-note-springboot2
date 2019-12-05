package cn.center;

import java.util.Properties;

public class Test {

	public static void main(String[] args) {
//		//获取所有的属性
//        Properties properties = System.getProperties();
//        //遍历所有的属性
//        for (String key : properties.stringPropertyNames()) {
//            //输出对应的键和值
//            System.out.println(key + "=" + properties.getProperty(key));
//        }
        System.out.println(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\")));
        
	}
}
