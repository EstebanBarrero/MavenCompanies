package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Campus {

    @JsonProperty("nombre_sede")
    String nombre_sede;
    @JsonProperty("codigo_sede")
    String codigo_sede;
    @JsonProperty("sede_principal")
    String sede_principal;
    @JsonProperty("lista_empleados_sede")
    List<Employee> employeeList;

    //constructor vacío
    public Campus() {
    }

    // Constructor con parámetros
    public Campus(String nombre_sede, String codigo_sede, String sede_principal, List<Employee> employeeList) {
        this.nombre_sede = nombre_sede;
        this.codigo_sede = codigo_sede;
        this.sede_principal = sede_principal;
        this.employeeList = employeeList;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    public String getCodigo_sede() {
        return codigo_sede;
    }

    public void setCodigo_sede(String codigo_sede) {
        this.codigo_sede = codigo_sede;
    }

    public String getSede_principal() {
        return sede_principal;
    }

    public void setSede_principal(String sede_principal) {
        this.sede_principal = sede_principal;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    //Getters y setters
    public void Lista_empleados_sede(Employee empleados) {
        employeeList.add(empleados);
    }

    @Override
    public String toString() {
        return "Nombre Sede: " + nombre_sede
                + ", Código Sede: " + codigo_sede
                + ", Sede Principal(S/N): " + sede_principal
                + ", Empleados: " + employeeList;
    }
}