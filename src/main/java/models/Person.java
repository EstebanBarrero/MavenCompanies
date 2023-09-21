/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author USUARIO
 */
public class Person {
        private String id_person;
    private String fistName;
    private String lastName;

    public Person(String id_person, String fistName, String lastName) {
        this.id_person = id_person;
        this.fistName = fistName;
        this.lastName = lastName;
    }

    public String getId_person() {
        return id_person;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Id_persona: " + id_person +
                ", Nombre: " + fistName +
                ", Apellido: " + lastName;
    }
}
