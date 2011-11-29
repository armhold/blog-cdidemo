package net.ftlines.blog.cdidemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@GeneratedValue
	@Id
	private Long id;
	@Basic(optional = false)
	private String name;
	@OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
	private List<Employee> members = new ArrayList<Employee>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public List<Employee> getMembers() {
		return members;
	}

}
