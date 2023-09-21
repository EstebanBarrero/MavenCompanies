/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

/**
 *
 * @author USUARIO
 */
public class View {
     public static final String MAIN_MENU = "=== MENÚ - Sistema ERP - Organizacion ===\n1. PERSONA\n2. EMPLEADO\n3. SEDE\n4. EMPRESA\n0. Salir";
    public static final String LOAD_EXIT = "Guardando datos antes de salir...";
    public static final String BYE = "¡Hasta luego!";
    public static final String MENU_PERSON = "=== MENÚ - ESTUDIANTES ===\n1. Ver personas registradas"
            + "\n2. Registrar una nueva persona\n3. Modificar registro de persona\n4. Eliminar registro de persona\n0. Salir";
    public static final String MENU_EMPLOYEE = "=== MENÚ - EMPLEADO ===\n1. Ver empleados registrados\n2. Registrar un nuevo empleado" +
            "\n3. Modificar registro empleado\n4. Eliminar registro empleado\n0. Salir";

    public static final String MENU_CAMPUS = "=== MENÚ - SEDE ===\n1. Ver sedes registrados \n2. Ver sedes principales \n" +
            "\n3. Registrar una nueva sede" +
            "\n4. Modificar registro sede\n5. Eliminar registro sede\n0. Salir";
    public static final String NOT_REGISTERED_PERSON = "No hay Personas registradas.";

    public static final String MENU_COMPANY = "=== MENÚ - EMPRESA ===\n1. Ver empresas registradas\n2. Registrar una nueva empresa" +
            "\n3. Modificar registro empresa\n4. Eliminar registro empresa\n0. Salir";
    public static final String NOT_REGISTERED_EMPLOYEE= "No hay empleados registradas.";


    public void showMenu() {// mostrar opciones menú
        System.out.println(MAIN_MENU);
    }

    public void showMenuPerson(){
        System.out.println(MENU_PERSON);
    }
    public void showMenuEmployees(){
        System.out.println(MENU_EMPLOYEE);
    }

    public void showMenuCampus(){
        System.out.println(MENU_CAMPUS);
    }

    public void showMenuCompany(){
        System.out.println(MENU_COMPANY);
    }
    public void enterOption(){
        System.out.print("Ingrese una opción: ");
    }

    public void noRegisteredPerson(){
        System.out.println(NOT_REGISTERED_PERSON);
    }

    public void noRegisteredEmployee(){
        System.out.println(NOT_REGISTERED_EMPLOYEE);
    }
    public void showBye(){
        System.out.println(BYE);
    }
    public void showInvalidateOption() {
        System.out.println("Opción no válida. Intente nuevamente.");
    }
}
