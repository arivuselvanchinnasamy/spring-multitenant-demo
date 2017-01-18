# spring-multitenant-demo

 A simple multi-tenant web demo application using Spring Boot + Spring Data JPA + Hibernate + MySQL + Thymeleaf with support of addition/deletion of datasources dynamically.

##**Environement Setup**
   
   Before running the application, you should load the setup script (“Multitenancy.sql”) under database directory. 
   
   This script will create a primary database with name “multitenancy_master” and 3 initial tenants with names “tenant1”, “tenant2” and “tenant3” respectively. 
   
   The primary database has the table called “MULTITENANCY_MASTER_DATASOURCE” for all tenants. Each tenant has its own database with a table called “EMPLOYEE” to maintain their own employee information. 
  
