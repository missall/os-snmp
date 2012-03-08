package cn.bstar.gale.boss.util;
    
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class OsBeanFactory {
	public static Object getBean(String name) { 
		String[] configLocations = new String[] {
				"config/beanRefContext.xml"};
		FileSystemXmlApplicationContext beanFactory = 
				new FileSystemXmlApplicationContext(configLocations);
		return beanFactory.getBean(name);
	}
}
