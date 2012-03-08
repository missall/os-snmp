package cn.bstar.gale.boss.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpOp {

	private Logger logger = Logger.getLogger(SnmpOp.class);
	
	//被管理主机snmp用户名
	private String securityName;

	//被管理主机snmp密码
	private String authPassword;

	//被管理主机ip地址
	private String address;

	//OidEnum数组，存放所有已定义的OidEnum
	private static OidEnum[] oidenum = OidEnum.values();

	//OidEnum集合，存放所有已匹配的OidEnum
	private List<OidEnum> oid;

	public SnmpOp() {
		oid = new ArrayList<OidEnum>();
	}

	/**
	 * 将用户动态添加的查询项与OidEnum枚举类中的进行匹配 匹配成功取出oid，存入oid中。遇到匹配失败的不添加。
	 * @param variable
	 * List集合，存放想要查询的项目，例如内存memAvailReal、硬盘diskTotal等等 由用户调用函数时动态添加。
	 */
	public void StringtoOidEnum(ArrayList<String> variable) {
		boolean flag;
		//遍历用户输入的查询项集合variable与oidenum做匹配
		for (String var : variable) {
			flag = false;
			for (int i = 0; i < oidenum.length && !flag; i++) {
				if (var.equals(oidenum[i].name())) {
					oid.add(oidenum[i]);
					flag = true;
				}
			}
			if (!flag)
				logger.warn("所匹配项 " + var + " 目前没有与之对应的oid");
		}
	}

	//创建被管理端对象
	private Target createV3Target(Snmp snmp, String agentIp, int agentPort) {
		UserTarget userTarget = new UserTarget();
		userTarget.setVersion(SnmpConstants.version3);
		userTarget.setSecurityLevel(SecurityLevel.AUTH_NOPRIV);
		userTarget.setSecurityName(new OctetString(securityName));
		userTarget.setRetries(1);
		userTarget.setTimeout(5000);
		userTarget.setAddress(GenericAddress.parse("udp:" + agentIp + "/"
				+ agentPort));
		return userTarget;
	}

	public LinkedHashMap<String, String> getInfo(String name, String pass,
			String addr, ArrayList<String> l) {
		StringtoOidEnum(l);
		this.securityName = name;
		this.authPassword = pass;
		this.address = addr;
		LinkedHashMap<String, String> map = null;
		TransportMapping<?> transportMapping = null;
		Snmp snmp = null;
		try {
			transportMapping = new DefaultUdpTransportMapping();
			snmp = new Snmp(transportMapping);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//设置USM-用户安全模型
		MPv3 mpv3 = (MPv3) snmp.getMessageProcessingModel(MessageProcessingModel.MPv3);
		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
				mpv3.getLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		
		//添加用户
		UsmUser usmUser = new UsmUser(new OctetString(securityName),
				AuthMD5.ID, new OctetString(authPassword), null, null);
	
		//将新增用户添加至USM中
		snmp.getUSM().addUser(usmUser);

		//创建被管理端对象
		Target agent = createV3Target(snmp, address, 161);// Agent
		
		//创建V3版本的PDU
		ScopedPDU pduV3 = new ScopedPDU();
		pduV3.setType(PDU.GET);
		
		//ContextName用于标识一个管理信息集合， 如果agent本地有多块网卡，那么一般来说就有多个context 暂时不用
		//pduV3.setContextName(new OctetString("abc"));

		//将匹配成功的oid添加到pduV3对象中
		for (int i = 0; i < oid.size(); i++) {
			pduV3.add(new VariableBinding(new OID((String) oid.get(i).toString())));
		}
					
		try {
			//开始监听，创建UDP连接
			transportMapping.listen(); 
			ResponseEvent response = snmp.send(pduV3, agent);
			
			if (response == null || response.getResponse() == null) {
				logger.warn("SNMP request timed out");
			}

			if (response != null) {

				PDU responsePDU = response.getResponse();
				//显示出错报文信息，此处有问题：无论如何都是sussess
				logger.info("SNMP Connect to "+addr+" response status: "+responsePDU.getErrorStatusText());
				if (responsePDU.getErrorStatus() == PDU.noError) {
					map = new LinkedHashMap<String, String>();
					logger.info("responsePDU.size():"+responsePDU.size());
					//responsePDU.size的值会随着查询次数的增加而累计
					for (int k = 0; k < responsePDU.size() && k < l.size(); k++) {
						VariableBinding vb = responsePDU.get(k);
						if (vb != null) {
							map.put(oid.get(k).name(), vb.getVariable().toString());

							logger.debug("The index is "+k+" "+oid.get(k).name() + "-" + vb.getVariable().toString());
						} else {
							logger.error("SNMP Error:" + responsePDU.getErrorStatusText());
						}
					 }
				 }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}