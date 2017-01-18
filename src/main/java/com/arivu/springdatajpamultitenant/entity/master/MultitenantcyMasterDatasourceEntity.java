package com.arivu.springdatajpamultitenant.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MULTITENANCY_MASTER_DATASOURCE")
public class MultitenantcyMasterDatasourceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "DATASOURCE_KEY")
	private String dataSourceKey;
	
	@Column(name = "DATASOURCE_SCHEMA_NAME")
	private String dataSourceSchemaName;
	
	@Column(name = "DATASOURCE_USERNAME")
	private String dataSourceUserName;
	
	@Column(name = "DATASOURCE_PASSWORD")
	private String dataSourcePassword;
	
	public MultitenantcyMasterDatasourceEntity() {
		
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the dataSourceKey
	 */
	public String getDataSourceKey() {
		return dataSourceKey;
	}
	/**
	 * @param dataSourceKey the dataSourceKey to set
	 */
	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}
	/**
	 * @return the dataSourceSchemaName
	 */
	public String getDataSourceSchemaName() {
		return dataSourceSchemaName;
	}
	/**
	 * @param dataSourceSchemaName the dataSourceSchemaName to set
	 */
	public void setDataSourceSchemaName(String dataSourceSchemaName) {
		this.dataSourceSchemaName = dataSourceSchemaName;
	}
	/**
	 * @return the dataSourceUserName
	 */
	public String getDataSourceUserName() {
		return dataSourceUserName;
	}
	/**
	 * @param dataSourceUserName the dataSourceUserName to set
	 */
	public void setDataSourceUserName(String dataSourceUserName) {
		this.dataSourceUserName = dataSourceUserName;
	}
	/**
	 * @return the dataSourcePassword
	 */
	public String getDataSourcePassword() {
		return dataSourcePassword;
	}
	/**
	 * @param dataSourcePassword the dataSourcePassword to set
	 */
	public void setDataSourcePassword(String dataSourcePassword) {
		this.dataSourcePassword = dataSourcePassword;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataSourceKey == null) ? 0 : dataSourceKey.hashCode());
		result = prime * result + ((dataSourcePassword == null) ? 0 : dataSourcePassword.hashCode());
		result = prime * result + ((dataSourceSchemaName == null) ? 0 : dataSourceSchemaName.hashCode());
		result = prime * result + ((dataSourceUserName == null) ? 0 : dataSourceUserName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MultitenantcyMasterDatasourceEntity other = (MultitenantcyMasterDatasourceEntity) obj;
		isEqual = (isEqual & dataSourceKey != null && other.dataSourceKey != null && dataSourceKey.equals(other.dataSourceKey) ? true : false);
		isEqual = (isEqual & dataSourcePassword != null && other.dataSourcePassword != null && dataSourcePassword.equals(other.dataSourcePassword) ? true : false);
		isEqual = (isEqual & dataSourceSchemaName != null && other.dataSourceSchemaName != null && dataSourceSchemaName.equals(other.dataSourceSchemaName) ? true : false);
		isEqual = (isEqual & dataSourceUserName != null && other.dataSourceUserName != null && dataSourceUserName.equals(other.dataSourceUserName) ? true : false);
		isEqual = (isEqual & id != null && other.id != null && id.equals(other.id) ? true : false);
		return isEqual;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"MultitenantcyMasterDatasourceEntity [id=%s, dataSourceKey=%s, dataSourceSchemaName=%s, dataSourceUserName=%s, dataSourcePassword=%s]",
				id, dataSourceKey, dataSourceSchemaName, dataSourceUserName, dataSourcePassword);
	}
	
}
