package cn.bstar.gale.boss.model;

// default package
// Generated 2011-12-22 9:41:03 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ServerManageInfo generated by hbm2java
 */
public class ServerManageInfo implements java.io.Serializable {

	private long serverId;
	private Domain domain;
	private Long storeOrderId;
	private String model;
	private String assctId;
	private String serverLabel;
	private Long nicNum;
	private String memSize;
	private String cpuCs;
	private Long cpuCoreNum;
	private String cpuType;
	private String hdSize;
	private Long hdRaidType;
	private Byte haveCdRom;
	private Byte haveStandbyPower;
	private Date dateCreated;
	private Long createdBy;
	private Date dateModified;
	private Long modifiedBy;
	private Set<OsInfo> osInfos = new HashSet<OsInfo>(0);

	public ServerManageInfo() {
	}

	public ServerManageInfo(long serverId) {
		this.serverId = serverId;
	}

	public long getServerId() {
		return this.serverId;
	}

	public void setServerId(long serverId) {
		this.serverId = serverId;
	}

	public Domain getDomain() {
		return this.domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Long getStoreOrderId() {
		return this.storeOrderId;
	}

	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}


	public String getAssctId() {
		return this.assctId;
	}

	public void setAssctId(String assctId) {
		this.assctId = assctId;
	}

	public String getServerLabel() {
		return this.serverLabel;
	}

	public void setServerLabel(String serverLabel) {
		this.serverLabel = serverLabel;
	}

	public Long getNicNum() {
		return this.nicNum;
	}

	public void setNicNum(Long nicNum) {
		this.nicNum = nicNum;
	}

	public String getMemSize() {
		return this.memSize;
	}

	public void setMemSize(String memSize) {
		this.memSize = memSize;
	}

	public String getCpuCs() {
		return this.cpuCs;
	}

	public void setCpuCs(String cpuCs) {
		this.cpuCs = cpuCs;
	}

	public Long getCpuCoreNum() {
		return this.cpuCoreNum;
	}

	public void setCpuCoreNum(Long cpuCoreNum) {
		this.cpuCoreNum = cpuCoreNum;
	}

	public String getCpuType() {
		return this.cpuType;
	}

	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}

	public String getHdSize() {
		return this.hdSize;
	}

	public void setHdSize(String hdSize) {
		this.hdSize = hdSize;
	}

	public Long getHdRaidType() {
		return this.hdRaidType;
	}

	public void setHdRaidType(Long hdRaidType) {
		this.hdRaidType = hdRaidType;
	}

	public Byte getHaveCdRom() {
		return this.haveCdRom;
	}

	public void setHaveCdRom(Byte haveCdRom) {
		this.haveCdRom = haveCdRom;
	}

	public Byte getHaveStandbyPower() {
		return this.haveStandbyPower;
	}

	public void setHaveStandbyPower(Byte haveStandbyPower) {
		this.haveStandbyPower = haveStandbyPower;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set<OsInfo> getOsInfos() {
		return this.osInfos;
	}

	public void setOsInfos(Set<OsInfo> osInfos) {
		this.osInfos = osInfos;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}