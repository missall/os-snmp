/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.bstar.gale.boss.dao;
import cn.bstar.gale.boss.model.OsInfo;
import java.util.List;
/**
 *
 * @author joe
 */
public interface OsDao {
	public List findAllOsByHql();
	public void updateOsInfo(OsInfo os);
}
