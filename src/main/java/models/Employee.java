package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.TypeJob;

import java.util.List;

class Empleado {

    @JsonProperty("cargo")
    private TypeJob typeJob;
    @JsonProperty("lista_personas_cargo")
    private List<Person> lista_personas_cargo;

    public Empleado() {
    }

    public Empleado(TypeJob typeJob, List<Person> lista_personas_cargo) {
        this.typeJob = typeJob;
        this.lista_personas_cargo = lista_personas_cargo;
    }

    public TypeJob getTypeJob() {
        return typeJob;
    }

    public void setTypeJob(TypeJob typeJob) {
        this.typeJob = typeJob;
    }

    public List<Person> getLista_personas_cargo() {
        return lista_personas_cargo;
    }

    public void setLista_personas_cargo(List<Person> lista_personas_cargo) {
        this.lista_personas_cargo = lista_personas_cargo;
    }



    public void Lista_personas_cargo(Person persona) {
        lista_personas_cargo.add(persona);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cargo: ").append(typeJob);

        builder.append("Personas asociadas al cargo: \n");
        for (Person persona : lista_personas_cargo) {
            builder.append(persona.toString()).append(", ");
        }
        return builder.toString();
    }

}
