package com.kang.test.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class SysModules implements java.io.Serializable {
	
	
	private String moduleId;
	private String moduleName;
	private String moduleDesc;
	private String moduleType;
	private String parent;
	private String moduleUrl;
	private Integer ILevel;
	private Integer leaf;
	private String application;
	private String controller;
	private Integer enable;
	private Integer priority;
	private Set<SysResources> sysResourceses = new HashSet<SysResources>(0);
	private Set<SysRolesMoudles> sysRolesMoudleses = new HashSet<SysRolesMoudles>(
			0);

	// Constructors

	/** default constructor */
	public SysModules() {
	}

	/** minimal constructor */
	public SysModules(String moduleName) {
		this.moduleName = moduleName;
	}

	/** full constructor */
	public SysModules(String moduleName, String moduleDesc, String moduleType,
			String parent, String moduleUrl, Integer ILevel, Integer leaf,
			String application, String controller, Integer enable,
			Integer priority, Set<SysResources> sysResourceses,
			Set<SysRolesMoudles> sysRolesMoudleses) {
		this.moduleName = moduleName;
		this.moduleDesc = moduleDesc;
		this.moduleType = moduleType;
		this.parent = parent;
		this.moduleUrl = moduleUrl;
		this.ILevel = ILevel;
		this.leaf = leaf;
		this.application = application;
		this.controller = controller;
		this.enable = enable;
		this.priority = priority;
		this.sysResourceses = sysResourceses;
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Integer getILevel() {
		return ILevel;
	}

	public void setILevel(Integer iLevel) {
		ILevel = iLevel;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Set<SysResources> getSysResourceses() {
		return sysResourceses;
	}

	public void setSysResourceses(Set<SysResources> sysResourceses) {
		this.sysResourceses = sysResourceses;
	}

	public Set<SysRolesMoudles> getSysRolesMoudleses() {
		return sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesMoudles> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}
	
	
	
	
}