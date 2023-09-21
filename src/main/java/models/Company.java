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
public class Company {
     private String nameCompany;
    private String codeCompany;
    private List<Campus> campusCompany;

    public Company(String nameCompany, String codeCompany, List<Campus> campusCompany) {
        this.nameCompany = nameCompany;
        this.codeCompany = codeCompany;
        this.campusCompany = campusCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCodeCompany() {
        return codeCompany;
    }

    public void setCodeCompany(String codeCompany) {
        this.codeCompany = codeCompany;
    }

    public List<Campus> getCampusCompany() {
        return campusCompany;
    }

    public void setCampusCompany(List<Campus> campusCompany) {
        this.campusCompany = campusCompany;
    }
}
