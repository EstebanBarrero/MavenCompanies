package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty("idPersona")
    String idPersona;
    @JsonProperty("nombreApellidoPersona")
    String nombreApellidoPersona;
    private Boolean isEmployeeLeader;

    // Constructor vacío
    public Person() {
    }

    public Person(String idPersona, String nombreApellidoPersona, Boolean isEmployeeLeader) {
        this.idPersona = idPersona;
        this.nombreApellidoPersona = nombreApellidoPersona;
        this.isEmployeeLeader = isEmployeeLeader;
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

    // Getters y setters para isEmployeeLeader

    public Boolean getIsEmployeeLeader() {
        return isEmployeeLeader;
    }

    public void setIsEmployeeLeader(Boolean isEmployeeLeader) {
        this.isEmployeeLeader = isEmployeeLeader;
    }

    @Override
    public String toString() {
        return "Id Persona: " + idPersona
                + ", Nombres y Apellidos Persona: " + nombreApellidoPersona
                + ", Empleado jefe (S/N): " + isEmployeeLeader;
    }
}

