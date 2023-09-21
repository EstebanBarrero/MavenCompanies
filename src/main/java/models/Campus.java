/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Campus {
       private String nameCampus;
    private String codeCampus;
    private boolean centralCampus;
    private List<Employee> employeeCampus;

    public Campus(String nameCampus, String codeCampus, boolean centralCampus, List<Employee> employeeCampus) {
        this.centralCampus = centralCampus;
        this.nameCampus = nameCampus;
        this.codeCampus = codeCampus;
        this.employeeCampus = employeeCampus; // new list, no recibirla por parametro
        // hay que validar empleados existentes antes de agregar?
        // los metodos de crud para la lista empleados van aqui
    }

    public void employeeCampus(Employee employee){
        employeeCampus.add(employee);
    }

    public String getNameCampus() {
        return nameCampus;
    }

    public String getCodeCampus() {
        return codeCampus;
    }

    public boolean isCentralCampus() {
        return centralCampus;
    }

    public void setNameCampus(String nameCampus) {
        this.nameCampus = nameCampus;
    }

    public void setCodeCampus(String codeCampus) {
        this.codeCampus = codeCampus;
    }

    public void setCentralCampus(boolean centralCampus) {
        this.centralCampus = centralCampus;
    }

    public void setEmployeeCampus(List<Employee> employeeCampus) {
        this.employeeCampus = employeeCampus;
    }

    public List<Employee> getEmployeeCampus() {
        return employeeCampus;
    }

}
