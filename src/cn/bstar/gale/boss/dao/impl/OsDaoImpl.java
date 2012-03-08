/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.bstar.gale.boss.dao.impl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.bstar.gale.boss.dao.OsDao;
import cn.bstar.gale.boss.model.OsInfo;
import java.util.List;
/**
 *
 * @author joe
 */
public class OsDaoImpl extends HibernateDaoSupport implements OsDao {
	
	public OsDaoImpl(){
		
	}
	
	@SuppressWarnings("rawtypes")
	public List findAllOsByHql() {
		String hql= "from cn.bstar.gale.boss.model.OsInfo os ";
		List result = this.getHibernateTemplate().find(hql);
		return result;
	}
	
	public void updateOsInfo(OsInfo os) {
		this.getHibernateTemplate().update(os);
	}
}
