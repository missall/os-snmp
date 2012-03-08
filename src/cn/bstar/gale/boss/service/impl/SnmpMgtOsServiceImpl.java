/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.bstar.gale.boss.service.impl;
import cn.bstar.gale.boss.dao.OsDao;
import cn.bstar.gale.boss.service.SnmpMgtOsService;
import cn.bstar.gale.boss.model.OsInfo;
import java.util.List;
/**
 *
 * @author joe
 */
public class SnmpMgtOsServiceImpl implements SnmpMgtOsService {
    private OsDao osDao;

	public void setOsDao(OsDao osDao){
		this.osDao = osDao;
	}
	
	public OsDao getOsDao(){
		return this.osDao;
	}
	
	public List findAllOsByHql(){
		return osDao.findAllOsByHql();
	}
	
	public void updateOsInfo(OsInfo os){
		osDao.updateOsInfo(os);
	}

}
