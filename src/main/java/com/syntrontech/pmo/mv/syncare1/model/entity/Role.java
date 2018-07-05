package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.FunctionTO;
import com.syntrontech.syncare.model.transfer.RoleTO;

@Entity
@Table(name="role")
public class Role implements ObjectConverter<RoleTO> {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY)
	private List<RoleFunction> functions;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<RoleFunction> getFunctions() {
		return functions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFunctions(List<RoleFunction> functions) {
		this.functions = functions;
	}

	@Override
	public RoleTO convert(boolean relation) {
		RoleTO to = new RoleTO();
		to.setId(getId());
		to.setName(getName());
		if(relation){
			List<FunctionTO> functions = getFunctions().stream()
				.map(function -> function.getFunction().convert(relation))
				.collect(Collectors.toList());
			to.setFunctions(functions);
		}
		return to;
	}

}
