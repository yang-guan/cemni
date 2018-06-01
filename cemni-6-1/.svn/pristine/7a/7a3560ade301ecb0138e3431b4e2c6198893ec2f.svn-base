package com.huiju.actment.activity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.huiju.console.org.entity.Org;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ACTIVITY_SCOPE")
public class Scope extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_activity_scope")
	@SequenceGenerator(name = "SEQ_activity_scope", sequenceName = "SEQ_activity_scope", allocationSize = 1)
	private Long scopeId;

	@ManyToOne
	@JoinColumn(name = "activityId", referencedColumnName = "activityId")
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "orgId", referencedColumnName = "orgId")
	private Org org;

	private String orgCode;

	public Long getScopeId() {
		return scopeId;
	}

	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
