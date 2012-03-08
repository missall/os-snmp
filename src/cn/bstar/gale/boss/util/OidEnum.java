package cn.bstar.gale.boss.util;

public enum OidEnum {
	sysDescr("1.3.6.1.2.1.1.1.0"), sysUpTime("1.3.6.1.2.1.1.3.0"), 
	sysUpName("1.3.6.1.2.1.1.5.0"), sysUserNums("1.3.6.1.2.1.25.1.5.0"), 
	sysProcesses("1.3.6.1.2.1.25.1.6.0"), memTotalSwap("1.3.6.1.4.1.2021.4.3.0"),
	memAvailSwap("1.3.6.1.4.1.2021.4.4.0"), memTotalReal("1.3.6.1.4.1.2021.4.5.0"),
 	memAvailReal("1.3.6.1.4.1.2021.4.6.0"), memBuffer("1.3.6.1.4.1.2021.4.14.0"),
	memCached("1.3.6.1.4.1.2021.4.15.0"), diskTotal("1.3.6.1.4.1.2021.9.1.6.1"), 
	diskAvail("1.3.6.1.4.1.2021.9.1.7.1");

	private String oid;

	OidEnum(String oid) {
		this.oid = oid;
	}

	public String toString() {
		return String.valueOf(this.oid);
	}
}
