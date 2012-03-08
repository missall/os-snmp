package cn.bstar.gale.boss.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import cn.bstar.gale.boss.util.SnmpOp;

public class testSnmp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SnmpOp snmpop = new SnmpOp();
		String name = "lance";
		String pass = "12345678";
		String addr = "192.168.1.35";
		ArrayList<String> l = new ArrayList<String>();
		l.add("sysUpTime");
		l.add("sysDescr");
		l.add("sysUpName");
		l.add("sysUserNums");
		l.add("sysProcesses");
		l.add("memTotalSwap");
		l.add("memAvailSwap");
		l.add("memTotalReal");
		l.add("memAvailReal");
		l.add("memBuffer");
		l.add("memCached");
		l.add("diskTotal");
		l.add("diskAvail");
		HashMap<String, String> map = snmpop.getInfo(name, pass, addr, l);
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}
