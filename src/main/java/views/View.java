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

    public void messageSaveDate(){
        System.out.println("Guardando datos antes de salir...");
    }

    public void messageNotValidated(){
        System.out.println("Opción no válida. Intente nuevamente.");
    }
    public void errorMesageXMl(){
        System.out.println("Error al cargar el menú desde el archivo XML.");
    }

    public void errorSaveDateJson(){
        System.out.println("Error al guardar los datos de Empresa en el archivo JSON.");
    }

    public void dateArchiveCompany(){
        System.out.println("Datos de Empresa guardados en el archivo JSON.");
    }

    public void loadArchiveCompany(){
        System.out.println("Datos de Empresa cargados desde el archivo JSON.");
    }

    public void errorLoadArchiveCompany(){
        System.out.println("No se pudo cargar los datos de empresas desde el archivo JSON.");
    }

    public void notFoundArchiveCompany(){
        System.out.println("El archivo 'empresas.json' no existe.");
    }

    public void notRegisterCompany(){
        System.out.println("No hay empresas registradas");
    }
    public void companyRegister(){
        System.out.println("=== Empresas Registrados ===");
    }
    public void registerCompany(){
        System.out.println("=== Registrar Empresa ===");
    }

    public void messageNameCompany(){
        System.out.println("nombre empresa: ");
    }

    public void messageCompanyRegister(){
        System.out.println("la empresa con este dato ya está registrada.");
    }

    public void messageCodeCompany(){
        System.out.println("código empresa: ");
    }

    public void messageRegisterTrue(){
        System.out.println("Empresa registrada exitosamente.");
    }

    public void messageModifyCompany(){
        System.out.println("=== Modificar Registro de Empresa ===");
    }

    public void messageIndexCompany(){
        System.out.println("Ingrese el índice de la empresa que desea modificar: ");
    }

    public void notCamposVacio(){
        System.out.println("No se permiten campos vacíos. Intente nuevamente.");
    }

    public void modifyCompanyTrue(){
        System.out.println("Empresa modificado exitosamente.");
    }

    public void messageDeleteCompany(){
        System.out.println("=== Eliminar Registro de Empresa ===");
    }

    public void messageIndexCompanyDelete(){
        System.out.println("Ingrese el índice de la empresa que desea eliminar: ");
    }

    public void companyDeleteTrue(){
        System.out.println("empresa eliminada exitosamente.");
    }

    public void notCampusRegister(){
        System.out.println("No hay sedes registradas.");
    }

    public void messageRegisterCampus(){
        System.out.println("=== sedes Registradas ===");
    }

    public void campusRegister(){
        System.out.println("=== Registrar sede ===");
    }

    public void messageCampusRegister(){
        System.out.println("la sede con este dato ya está registrado.");
    }
}
