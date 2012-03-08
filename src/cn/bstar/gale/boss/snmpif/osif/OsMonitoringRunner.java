package cn.bstar.gale.boss.snmpif.osif;
  
import cn.bstar.gale.boss.util.OsBeanFactory;
import cn.bstar.gale.boss.snmpif.osif.OsMonitoringServer;

public class OsMonitoringRunner {
	
	public static void main(String[] args) {
		OsMonitoringServer server = (OsMonitoringServer) OsBeanFactory
				.getBean("osMonitoringServer");
		server.startListener();
	} 

}
