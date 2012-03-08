/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.bstar.gale.boss.service;
import cn.bstar.gale.boss.model.OsInfo;
import java.util.List;
/**
 *
 * @author joe
 */
public interface SnmpMgtOsService {
	public List findAllOsByHql();
	public void updateOsInfo(OsInfo os);
}
