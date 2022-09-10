package com.bp.example.base.prueba;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeDeparmentId
    implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1768211251620874780L;

	@Column(name = "id_employee")
    private Long idEmployee;
 
    @Column(name = "id_department")
    private Long idDepartment;
 
    private EmployeeDeparmentId() {}
 
    public EmployeeDeparmentId(
        Long idEmployee,
        Long idDepartment) {
        this.idEmployee = idEmployee;
        this.idDepartment = idDepartment;
    }
 
    //Getters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EmployeeDeparmentId that = (EmployeeDeparmentId) o;
        return Objects.equals(idEmployee, that.idEmployee) &&
               Objects.equals(idDepartment, that.idDepartment);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, idDepartment);
    }
}