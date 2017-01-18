package com.arivu.springdatajpamultitenant.entity.tenant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "DEPARTMENT")
	private String department;
	
	@Column(name = "OFFICE")
	private String office;
	
	public EmployeeEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = (this == obj ? true : false);
		isEqual = (isEqual & obj  != null ? true : false);
		isEqual = (isEqual & getClass() == obj.getClass() ? true : false);
		EmployeeEntity other = (EmployeeEntity) obj;
		isEqual = (isEqual & department != null && other.department != null && department.equals(other.department) ? true : false);
		isEqual = (isEqual & email != null && other.email != null && email.equals(other.email) ? true : false);
		isEqual = (isEqual & firstName != null && other.firstName != null && firstName.equals(other.firstName) ? true : false);
		isEqual = (isEqual & lastName != null && other.lastName != null && lastName.equals(other.lastName) ? true : false);
		isEqual = (isEqual & office != null && other.office != null && office.equals(other.office) ? true : false);
		isEqual = (isEqual & id != null && other.id != null && id.equals(other.id) ? true : false);
		return isEqual;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("EmployeeEntity [id=%s, firstName=%s, lastName=%s, email=%s, department=%s, office=%s]",
				id, firstName, lastName, email, department, office);
	}
}