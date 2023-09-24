package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty("idPersona")
    String idPersona;
    @JsonProperty("nombreApellidoPersona")
    String nombreApellidoPersona;
    @JsonProperty("isEmployeeLeader")
    private Boolean isEmployeeLeader;

    //constructor vacío
    public Person() {
    }

    public Person(String idPersona, String nombreApellidoPersona, Boolean empleado_jefe) {
        this.idPersona = idPersona;
        this.nombreApellidoPersona = nombreApellidoPersona;
        this.isEmployeeLeader = empleado_jefe;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreApellidoPersona() {
        return nombreApellidoPersona;
    }

    public void setNombreApellidoPersona(String nombreApellidoPersona) {
        this.nombreApellidoPersona = nombreApellidoPersona;
    }

    public Boolean getEmployeeLeader() {
        return isEmployeeLeader;
    }

    public void setEmployeeLeader(Boolean employeeLeader) {
        isEmployeeLeader = employeeLeader;
    }

    @Override
    public String toString() {
        return "Id Persona: " + idPersona
                + ", Nombres y Apellidos Persona: " + nombreApellidoPersona
                + ", Empleado jefe (S/N): " + isEmployeeLeader;
    }
}
