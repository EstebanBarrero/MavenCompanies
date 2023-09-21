package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Company {

    @JsonProperty("nombre_empresa")
    String nombre_empresa;
    @JsonProperty("codigo_empresa")
    String codigo_empresa;
    @JsonProperty("lista_sedes_empresa")
    List<Campus> lista_sedes_empresa;

    public Company() {
    }

    public Company(String nombre_empresa, String codigo_empresa) {
        this.nombre_empresa = nombre_empresa;
        this.codigo_empresa = codigo_empresa;
        this.lista_sedes_empresa = new ArrayList<>(); // Inicialización de la lista
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getCodigo_empresa() {
        return codigo_empresa;
    }

    public void setCodigo_empresa(String codigo_empresa) {
        this.codigo_empresa = codigo_empresa;
    }

    public List<Campus> getLista_sedes_empresa() {
        return lista_sedes_empresa;
    }

    public void setLista_sedes_empresa(List<Campus> lista_sedes_empresa) {
        this.lista_sedes_empresa = lista_sedes_empresa;
    }

    ///
    public void Lista_sedes_empresa(Campus campus) {
        lista_sedes_empresa.add(campus);
    }

    @Override
    public String toString() {
        return "Nombre Empresa: " + nombre_empresa
                + ", Código Empresa: " + codigo_empresa;
    }
}
