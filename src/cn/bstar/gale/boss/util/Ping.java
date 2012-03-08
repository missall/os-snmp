package cn.bstar.gale.boss.util;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.io.LineNumberReader;  
import java.text.MessageFormat;

public class Ping {
	
	private static Logger logger = Logger.getLogger(Ping.class);
	
	private static final int TEST_PING_MAX_COUNT = 3;
	
	public static Boolean validatePing(String destIp) {  
	    LineNumberReader input = null;  
	    try {  
	        String osName = System.getProperties().getProperty("os.name");
	        logger.info(osName);  
	        String pingCmd = null;  
	        if (osName.startsWith("Windows")) {  
	            pingCmd = "cmd /c ping -n {0} {1}";  
	            pingCmd = MessageFormat.format(pingCmd, TEST_PING_MAX_COUNT, destIp);  
	        } else if (osName.startsWith("Linux") || osName.startsWith("Mac OS X")) {  
	            pingCmd = "ping -c {0} {1}";  
	            pingCmd = MessageFormat.format(pingCmd, TEST_PING_MAX_COUNT, destIp);  
	        } else {  
	            logger.error("not support OS");  
	            return null;  
	        }  
	
	        Process process = Runtime.getRuntime().exec(pingCmd);
	  
	        InputStreamReader ir = new InputStreamReader(process  
	                .getInputStream());  
	        input = new LineNumberReader(ir);  
	        String line;  
	        List<String> reponse = new ArrayList<String>();  
    		StringBuffer sb = new StringBuffer();
	        while ((line = input.readLine()) != null) {  
	            if (!"".equals(line)) {  
	                reponse.add(line);  
	                logger.debug("====:" + line);
	  				sb.append(line+"\n");
	            }  
	        }  
			logger.debug("ping response snapshot"+sb.toString());
	        if (osName.startsWith("Windows")) {  
	            return parseWindowsMsg(reponse);  
	        } else if (osName.startsWith("Linux") || osName.startsWith("Mac OS X")) {  
	            return parseLinuxMsg(reponse);  
	        }  
    
	    } catch (IOException e) {  
	        logger.error("IOException" + e.getMessage());    
	    } finally {  
	        if (null != input) {  
	            try {  
	                input.close();  
	            } catch (IOException ex) {  
	                logger.error("close error:" + ex.getMessage());  
    
	            }  
	        }  
	    }  
	    return null;  
	}  
    
	private static Boolean parseWindowsMsg(List<String> reponse) {  
	    boolean responseStatus = false;   
	    for (String str : reponse) {  
	        if (str.startsWith("来自") || str.startsWith("Reply from")) {  
	            responseStatus = true;  
	        }   
	    }  
	    return responseStatus;  
	}  
    
	private static Boolean parseLinuxMsg(List<String> reponse) {  
	    boolean responseStatus = false;  
	    for (String str : reponse) {  
	        if (str.contains("bytes from") && str.contains("icmp_seq=")) {  
	            responseStatus = true;  
	        }  
	    }  
	    return responseStatus;  
	}
	
}