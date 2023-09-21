/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author USUARIO
 */
public class TypeJob {
          DIRECTIVO("D"), ASISTENCIAL("A"), OPERATIVO("O");
    private final String codigo;

    TypeJob(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
