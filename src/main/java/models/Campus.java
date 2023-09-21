package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

class Campus {

    @JsonProperty("nombre_sede")
    String nombre_sede;
    @JsonProperty("codigo_sede")
    String codigo_sede;
    @JsonProperty("sede_principal")
    String sede_principal;
    @JsonProperty("lista_empleados_sede")
    List<Empleado> lista_empleados_sede;

    //constructor vacío
    public Campus() {
    }

    // Constructor con parámetros
    public Campus(String nombre_sede, String codigo_sede, String sede_principal) {
        this.nombre_sede = nombre_sede;
        this.codigo_sede = codigo_sede;
        this.sede_principal = sede_principal;

        this.lista_empleados_sede = new ArrayList<>(); // Inicialización de la lista
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

    public List<Empleado> getLista_empleados_sede() {
        return lista_empleados_sede;
    }

    public void setLista_empleados_sede(List<Empleado> lista_empleados_sede) {
        this.lista_empleados_sede = lista_empleados_sede;
    }

    //Getters y setters
    public void Lista_empleados_sede(Empleado empleados) {
        lista_empleados_sede.add(empleados);
    }

    @Override
    public String toString() {
        return "Nombre Sede: " + nombre_sede
                + ", Código Sede: " + codigo_sede
                + ", Sede Principal(S/N): " + sede_principal;
    }
}