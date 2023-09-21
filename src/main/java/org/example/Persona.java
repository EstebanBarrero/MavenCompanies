package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

class Persona {

    @JsonProperty("id_persona")
    String id_persona;
    @JsonProperty("nombres")
    String nombres;
    @JsonProperty("apellidos")
    private String apellidos;

    //constructor vacío
    public Persona() {
    }

    public Persona(String id_persona, String nombres, String apellidos) {
        this.id_persona = id_persona;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Id Persona: " + id_persona
                + ", Nombres: " + nombres
                + ", Apellidos: " + apellidos;
    }
}
