# EDU-AMILOSH
Alexander Milosh educational project.

### 3. Add checkstyle
Download checkstyle.xml from Internet. toolVersion is here: https://github.com/checkstyle/checkstyle/releases

### 4. Connect to PostgreSQL
Download PostgreSQL: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads (12.6)
Install PostgreSQL (5433, postgres/postgres). Open PgAdmin. Create the schema 'edu'.

### 6-1. OneToOne relationship.
1) 
```
employee.setWorkPlace(workplace);
employeeRepo.save(employee);
```
Employee is saved. WorkPlace is saved without employee_id.

2)
```
workPlace.setEmployee(employee);
employee.setWorkPlace(workplace);
employeeRepo.save(employee);
```
Employee is saved. WorkPlace is saved with employee_id.

3)
```
employee.setWorkPlace(null);
newWorkPlace.setEmployee(employee);
employee.setWorkPlace(newWorkPlace);
employeeRepo.save(employee);
```
Old workPlace remained without changes.

4)
```
oldWorkSpace.setEmployee(null);
employee.setWorkPlace(null);
newWorkPlace.setEmployee(employee);
employee.setWorkPlace(newWorkPlace);
employeeRepo.save(employee);
```
Old workPlace remained with employee_id = null.

5) 
```
oldWorkSpace.setEmployee(null);
employee.setWorkPlace(null);
newWorkPlace.setEmployee(employee);
employee.setWorkPlace(newWorkPlace);
employeeRepo.save(employee);
```
With Cascade All nad orphanRemoval. WorkPlace is deleted. Computer is deleted.