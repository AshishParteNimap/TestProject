package com.JobPortal.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Permission")
@org.springframework.transaction.annotation.Transactional
public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String permissionName;

//	@JsonIgnore
//	@ManyToMany(mappedBy = "permissions",cascade = CascadeType.MERGE)
//	private Set<Role> roles;


	public Permission(Long id, String permissionName) {
		super();
		this.id = id;
		this.permissionName = permissionName;
		//this.roles = roles;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}
