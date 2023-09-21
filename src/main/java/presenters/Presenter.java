/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Presenter {
     private static List<Person> personList;
    private static List<Employee> employeeList;
    private static List<Campus> campusList;

    private static List<Company> companyList;
    private static BufferedReader reader;
    private View view;


    public Presenter() {
        this.view = new View();
        personList = new ArrayList<>();
        employeeList = new ArrayList<>();
        campusList = new ArrayList<>();
        companyList = new ArrayList<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void menu() throws IOException {
        int opcionMainMenu, opcionSubMenu;

        do {
            view.showMenu();
            opcionMainMenu = leerOpcion();


            switch (opcionMainMenu) {
                case 1 -> {
                    do{
                        view.showMenuPerson();
                        opcionSubMenu = leerOpcion();
                        switch(opcionSubMenu) {
                            case 1 -> showRegisterPerson();
                            case 2 -> registerPerson();
                            case 3 -> changePerson();
                            case 4 -> deletePerson();
                            case 0 -> view.showBye();
                            default -> view.showInvalidateOption();
                        }
                    }while (opcionSubMenu != 0);
                }
                case 2 -> {
                    do {
                        view.showMenuEmployees();
                        opcionSubMenu = leerOpcion();
                        switch (opcionSubMenu) {
                            case 1 -> showRegisterEmployees();
                            case 2 -> matricularEstudiantePrograma();
                            case 3 -> changeEmployee();
                            case 4 -> deleteEmployee();
                            case 0 -> view.showBye();
                            default -> view.showInvalidateOption();
                        }
                    }while(opcionSubMenu != 0);
                }
                case 3 -> {
                    do {
                        view.showMenuCampus();
                        opcionSubMenu = leerOpcion();
                        switch (opcionSubMenu) {
                            case 1 -> showRegisterCampus();
                            case 2 -> showCampusCentral();
                            case 3 -> registerCampus();
                            case 4 -> modifyCampus();
                            case 5 -> deleteRegisterCampus();
                            case 6 -> addEmployee();
                            case 0 -> view.showBye();
                            default -> view.showInvalidateOption();
                        }
                    }while(opcionSubMenu != 0);
                }
                case 4 -> {
                    do {
                        view.showMenuCompany();
                        opcionSubMenu = leerOpcion();
                        switch (opcionSubMenu) {
                            case 1 -> showRegisterCompany();
                            case 2 -> registerCompany();
                            case 3 -> modifyCompany();
                            case 4 -> deleteRegisterCaompany(); //Falta ver si la empresa esta asociada a una sede o viceversa no se
                            //puede eliminar tan facilmente y habria que desvincularla primero de la sede
                            case 0 -> view.showBye();
                            default -> view.showInvalidateOption();
                        }
                    }while(opcionSubMenu != 0);
                }
                case 0 ->
                        view.showBye();
                default ->
                        view.showInvalidateOption();
                // Guardar los datos antes de salir

            }
        } while (opcionMainMenu != 0);
    }

    private void addEmployee() {
        String id_empleado = (leerCodigoNumerico());
        String id_campus = (leerCodigoNumerico());
        Campus campus = buscarCampus(id_campus);
        if (campus != null){
            Employee employee = buscarEmpleado(id_empleado);
            if (employee != null){
                campus.employeeCampus(employee);
            }else {
                System.out.println("El empleado con id " + id_empleado + " No fue encontrado");
            }
        }else {
            System.out.println("El campus con id " + id_campus + " No fue encontrado" );
        }

    }

    private Employee buscarEmpleado(String id){
        for (Employee e: employeeList) {
            if (e.getPerson().getId_person().equals(id)){
                return e;
            }
        }return null;
    }

    private Campus buscarCampus(String id){
        for (Campus c: campusList) {
            if (c.getCodeCampus().equals(id)){
                return c;
            }
        }return null;
    }

    private static int leerOpcion() throws IOException {
        int opcion;
        while (true) {
            try {
                System.out.print("Ingrese una opción: ");
                String input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    opcion = Integer.parseInt(input);
                    if (opcion >= 0 && opcion <= 18) {
                        break;
                    } else {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("No se permiten campos vacíos. Intente nuevamente.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return opcion;
    }

    public void showRegisterPerson() {                       //método para leer lista de personas
        if (personList.isEmpty()) {                                        //Verifica si la lista de personas está vacía
            view.noRegisteredPerson();
        } else {
            System.out.println("=== Personas Registradas ===");
            int index = 0;
            for (Person person : personList) {                     //bucle for-each que recorre la lista de personas
                System.out.println("Índice " + index + ": " + person);
                index++;
            }
        }
    }


    private static void registerPerson() {
        System.out.println("=== Registrar Personas ===");

        // Declaración de variables para almacenar los datos del estudiante
        String id_person = null;
        String fistName = null;
        String lastName = null;

        // Ciclo para validar y registrar los datos del estudiante
        while (true) {
            // Si la identificación del estudiante aún no se ha ingresado
            if (id_person == null) {
                System.out.print("Id Persona: ");
                id_person = leerCadenaNoVacia();

                // Validar si el estudiante ya está registrado por identificación
                for (Person person : personList) {
                    if (person.getId_person().equalsIgnoreCase(id_person)) {
                        System.out.println("Ya se encuentra una persona con esta identificación ya registrada.");
                        id_person = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si los nombres y apellidos del estudiante aún no se han ingresado
            else if (fistName == null) {
                System.out.print("Nombres: ");
                fistName = leerCadenaNoVaciaTexto();
            } // Si el correo electrónico del estudiante aún no se ha ingresado
            else if (lastName == null) {
                System.out.print("Apellidos: ");
                lastName = leerCadenaNoVaciaTexto();
            }

            // Si se han ingresado todos los datos requeridos, registrar la persona
            if (id_person != null && fistName != null && lastName != null) {
                personList.add(new Person(id_person, fistName, lastName));
                System.out.println("Persona registrado exitosamente.");
                //guardarEstudiantesEnArchivo();
                break; // Salir del bucle en caso de éxito
            }
        }
    }
    public void changePerson() {
        System.out.println("=== Modificar Registro de Estudiante ===");

        // Verificar si hay estudiantes registrados
        if (personList.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        // Mostrar la lista de personas registradas
        showRegisterPerson();

        // Solicitar al usuario que ingrese el índice del estudiante que desea modificar
        System.out.print("Ingrese el índice del estudiante que desea modificar: ");
        int indice = leerIndiceValido(personList.size());
        Person personaSeleccionada = personList.get(indice);

        // Inicializar variables con los valores actuales del estudiante seleccionado
        String nuevoIdPersona = personaSeleccionada.getId_person();
        String nuevoNombrePersona = personaSeleccionada.getFistName();
        String nuevoApellidoPersona = personaSeleccionada.getLastName();

        // Solicitar al usuario que ingrese el nuevo código de estudiante
        while (true) {
            System.out.print("Nuevo ID de la Persona (" + nuevoIdPersona + "): ");
            String input = leerCodigoNumerico();
            boolean idPersonaRegistrada = false;
            if (!input.isEmpty()) {
                for (Person person : personList) {
                    if (person.getId_person().equals(input) && !person.getId_person().equals(nuevoIdPersona)) {
                        System.out.println("La persona con este código ya está registrado.");
                        idPersonaRegistrada = true;
                        break;
                    }
                }
                if (!idPersonaRegistrada) {
                    nuevoIdPersona = input;
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            }
        }

        // Solicitar al usuario que ingrese el nuevo nombre
        while (true) {
            System.out.print("Nuevo Nombres (" + nuevoNombrePersona + "): ");
            String input = leerCadenaNoVaciaTexto();
            if (!input.isEmpty()) {
                nuevoNombrePersona = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            }
        }
        // Solicitar al usuario que ingrese el nuevo apellido
        while (true) {
            System.out.print("Nuevo Nombres (" + nuevoApellidoPersona + "): ");
            String input = leerCadenaNoVaciaTexto();
            if (!input.isEmpty()) {
                nuevoApellidoPersona = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            }
        }

        // Actualizar los datos del estudiante con los valores nuevos
        personaSeleccionada.setId_person(nuevoIdPersona);
        personaSeleccionada.setFistName(nuevoNombrePersona);
        personaSeleccionada.setLastName(nuevoApellidoPersona);

        System.out.println("Estudiante modificado exitosamente.");
        //guardarEstudiantesEnArchivo();
    }

    public void deletePerson() {                                      //método para eliminar estudiantes registrados de la lista estudiantes
        System.out.println("=== Eliminar Registro de Estudiante ===");
        if (personList.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        showRegisterPerson();
        System.out.print("Ingrese el índice del estudiante que desea eliminar: ");
        int indice = leerIndiceValido(personList.size());
        personList.remove(indice);
        System.out.println("Estudiante eliminado exitosamente.");
        //guardarEstudiantesEnArchivo();
    }

    private static void showRegisterEmployees() {
        System.out.println("=== Empleados Registrados ===");
        System.out.println(employeeList.size());
        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            int index = 0;
            for (Employee employee : employeeList) {
                System.out.println("Índice: " + index + ", Cargo: " + employee.getJobTitle().getNameJobTitle() + ", Id_persona: " + employee.getPerson().getId_person()
                        + ", Nombre_persona: " + employee.getPerson().getFistName() +", Apellido_persona: " + employee.getPerson().getLastName());
                index++;
            }
        }
    }


    public void matricularEstudiantePrograma() {
        System.out.println("=== EMPLEADO - PERSONA ===");

        if (personList.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }

        showRegisterPerson();

        System.out.print("Ingrese el índice del estudiante que desea matricular: ");
        int indexPerson = leerIndiceValido(personList.size());

        Person person = personList.get(indexPerson);

        System.out.println("=== CARGOS DISPONIBLES ===\n1. " + TypeJob.DIRECTIVO + "(D)" + "\n2. " + TypeJob.ASISTENCIAL + "(A)" + "\n3. " + TypeJob.OPERATIVO + "(O)" +
                "\nIngrese el número del cargo en el que desea registrar a la persona: ");
        int indexJobTitle = leerIndiceValido(TypeJob.values().length);
        String jobTitle = "";
        switch (indexJobTitle){
            case 1 -> jobTitle = String.valueOf(TypeJob.DIRECTIVO);
            case 2 -> jobTitle = String.valueOf(TypeJob.OPERATIVO);
            case 3 -> jobTitle = String.valueOf(TypeJob.ASISTENCIAL);
            default -> System.out.println("Por favor ingrese un valor valido (1, 2, 3)");
        }

        employeeList.add(new Employee(new JobTitle(jobTitle), person));
        // Validar si el estudiante ya está matriculado en el programa
        /*boolean estudianteMatriculado = selectEmployee.getPerson_employees().contains(selectEmployee);

        if (estudianteMatriculado) {
            System.out.println("El estudiante ya está matriculado en este programa.");
        } else {
            selectEmployee.person_assigned_employee(selectEmployee);
            System.out.println("Estudiante matriculado exitosamente en el programa académico.");
        }*/
    }

    public String jobTitles(){
        System.out.println("=== CARGOS DISPONIBLES ===\n1. " + TypeJob.DIRECTIVO + "(D)" + "\n2. " + TypeJob.ASISTENCIAL + "(A)" + "\n3. " + TypeJob.OPERATIVO + "(O)" +
                "\nIngrese el número del cargo en el que desea registrar a la persona: ");
        int indexJobTitle = leerIndiceValido(TypeJob.values().length);
        String jobTitle = "";
        switch (indexJobTitle){
            case 1 -> jobTitle = String.valueOf(TypeJob.DIRECTIVO);
            case 2 -> jobTitle = String.valueOf(TypeJob.OPERATIVO);
            case 3 -> jobTitle = String.valueOf(TypeJob.ASISTENCIAL);
            default -> System.out.println("Por favor ingrese un valor valido (1, 2, 3)");
        }
        return jobTitle;
    }

    public void changeEmployee() {
        System.out.println("=== Modificar Registro de empleado ===");

        // Verificar si hay estudiantes registrados
        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        // Mostrar la lista de personas registradas
        showRegisterEmployees();

        // Solicitar al usuario que ingrese el índice del estudiante que desea modificar
        System.out.print("Ingrese el índice del estudiante que desea modificar: ");
        int indice = leerIndiceValido(personList.size());
        Employee selectEmployees = employeeList.get(indice);

        // Inicializar variables con los valores actuales del estudiante seleccionado
        String jobTitle = selectEmployees.getJobTitle().getNameJobTitle();
        String idPerson = selectEmployees.getPerson().getId_person();
        String firstnamePerson = selectEmployees.getPerson().getFistName();
        String lastnamePerson = selectEmployees.getPerson().getLastName();

        // Solicitar al usuario que ingrese el cargo del empleado
        while (true) {
            System.out.print("Nuevo Cargo (" + jobTitle + "): ");
            System.out.println("=== CARGOS DISPONIBLES ===\n1. " + TypeJob.DIRECTIVO + "(D)" + "\n2. " + TypeJob.ASISTENCIAL + "(A)" + "\n3. " + TypeJob.OPERATIVO + "(O)" +
                    "\nIngrese el número del cargo en el que desea registrar a la persona: ");
            int indexJobTitle = leerIndiceValido(TypeJob.values().length);
            String jobTitleCase = "";
            if (indexJobTitle >= 1 && indexJobTitle <=3) {
                switch (indexJobTitle){
                    case 1 -> jobTitleCase = String.valueOf(TypeJob.DIRECTIVO);
                    case 2 -> jobTitleCase = String.valueOf(TypeJob.OPERATIVO);
                    case 3 -> jobTitleCase = String.valueOf(TypeJob.ASISTENCIAL);
                    default -> System.out.println("Por favor ingrese un valor valido (1, 2, 3)");
                }
                jobTitle = jobTitleCase;
                break;
            } else {
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            }
        }

        // Solicitar al usuario que ingrese el nuevo código de estudiante
        while (true) {
            System.out.print("Nuevo ID de la Persona (" + idPerson + "): ");
            String input = leerCodigoNumerico();
            boolean idPersonaRegistrada = false;
            if (!input.isEmpty()) {
                for (Person person : personList) {
                    if (person.getId_person().equals(input) && !person.getId_person().equals(idPerson)) {
                        System.out.println("La persona con este código ya está registrado.");
                        idPersonaRegistrada = true;
                        break;
                    }
                }
                if (!idPersonaRegistrada) {
                    idPerson = input;
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            }
        }

        // Solicitar al usuario que ingrese el nuevo nombre
        while (true) {
            System.out.print("Nuevo Nombres (" + firstnamePerson + "): ");
            String input = leerCadenaNoVaciaTexto();
            if (!input.isEmpty()) {
                firstnamePerson = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            }
        }
        // Solicitar al usuario que ingrese el nuevo apellido
        while (true) {
            System.out.print("Nuevo Nombres (" + lastnamePerson + "): ");
            String input = leerCadenaNoVaciaTexto();
            if (!input.isEmpty()) {
                lastnamePerson = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            }
        }
        // Actualizar los datos del empleado con los valores nuevos
        selectEmployees.setJobTitle(new JobTitle(jobTitle));
        selectEmployees.setPerson(new Person(idPerson,firstnamePerson,lastnamePerson));
        System.out.println("Empleado modificado exitosamente.");
        //guardarEstudiantesEnArchivo();
    }

    public void deleteEmployee(){
        System.out.println("=== Eliminar Registro de Empleado ===");
        if (employeeList.isEmpty()) {
            System.out.println("No hay Empleados registrados.");
            return;
        }
        showRegisterEmployees();
        System.out.print("Ingrese el índice del estudiante que desea eliminar: ");
        int indice = leerIndiceValido(employeeList.size());
        employeeList.remove(indice);
        System.out.println("Empleado eliminado exitosamente.");
        //guardarEstudiantesEnArchivo();
    }


    public void registerEmployeeCampus() {
        System.out.println("=== EMPLEADO - SEDE ===");
        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registradas.");
            return;
        }

        showRegisterEmployees();

        System.out.print("Ingrese el índice de la sede en la que desee inscribir el empleado: ");
        int indexCampus = leerIndiceValido(campusList.size());

        Campus campusSelected = campusList.get(indexCampus);

        if(campusList.isEmpty()){
            System.out.println("No hay Sedes creadas ");
            return;
        }

        showRegisterCampus();

        System.out.println("Ingrese el indice del empleado que desea inscribir en la sede");
        int indexEmployee = leerIndiceValido(employeeList.size());

        Employee employeSelected = employeeList.get(indexEmployee);

        boolean checkEmployeeSelected= employeSelected.getPersonEmployees().contains(employeSelected);

        if (checkEmployeeSelected) {
            System.out.println("El empleado ya esta registrado en esta sede");
        } else {
            campusSelected.employeeCampus(employeSelected);
            System.out.println("Empleado registrado en la sede exitosamente");
        }
    }



    private static void showRegisterCampus() {
        System.out.println("=== Sedes Registradas ===");

        if (campusList.isEmpty()) {
            System.out.println("No hay sedes registradas.");
        } else {
            int index = 0;
            for (Campus campus : campusList) {
                System.out.println("Índice " + index + ": " + campus);
                index++;
            }
        }
    }

    private static void registerCampus() {
        System.out.println("=== Registrar Sedes ===");

        String nameCampus= null;
        String codeCampus = null;
        boolean centralCampus = false;

        // Ciclo para validar y registrar los datos del estudiante
        while (true) {
            // Si el codigo del campus no se ha registrado
            if (codeCampus == null) {
                System.out.print("Id Sede: ");
                codeCampus = leerCadenaNoVacia();

                // Validar si el campus ya esta registrado codeCampus
                for (Campus campus : campusList) {
                    if (campus.getCodeCampus().equalsIgnoreCase(codeCampus)) {
                        System.out.println("Ya se encuentra una sede con el codigo proporcionado");
                        codeCampus= null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si el nombre de la sede no se ha ingresado
            else if (nameCampus == null) {
                System.out.print("Nombres: ");
                nameCampus = leerCadenaNoVaciaTexto();
            } // Validar si es la sede principal
            else if(centralCampus == false){
                String auxCode = null;
                System.out.println("Este es el campus central (SI/NO):");
                auxCode = leerCadenaNoVacia();
                if (auxCode.equalsIgnoreCase("si")) {
                    centralCampus = true;
                }else{
                    centralCampus = false;
                }
            }

            // Si se han ingresado todos los datos requeridos, registrar la persona
            if (codeCampus != null && nameCampus!= null) {
                campusList.add(new Campus(nameCampus, codeCampus, centralCampus, employeeList));
                System.out.println("Sede registrada exitosamente");
                //guardarCampusEnArchivo();
                break; // Salir del bucle en caso de éxito
            }
        }
    }
    private static void showCampusCentral(){
        System.out.println("=== Sedes principales ===");
        if(campusList.isEmpty()) {
            System.out.println("No hay sedes registradas");
        }else{
            int index = 0;
            for (Campus campus : campusList) {
                if(campus.isCentralCampus() == true) {
                    System.out.println("Índice " + index + ": " + campus);
                    index++;
                }else{
                    System.out.println("No hay Sedes principales registradas");
                }
            }
        }
    }

    private static void modifyCampus() {
        System.out.println("=== Modificar SEDE ===");

        if (campusList.isEmpty()) {
            System.out.println("No hay sedes registradas.");
            return;
        }

       showRegisterCampus();

        System.out.print("Ingrese el índice de la sede que desea modificar: ");
        int indexCampus = leerIndiceValido(campusList.size());

        Campus campusSelected = campusList.get(indexCampus);

        String newNameCampus = campusSelected.getNameCampus();
        String newCodeCampus = campusSelected.getCodeCampus();
        boolean newCentralCampus = campusSelected.isCentralCampus();


        while (true) {
            System.out.print("Nuevo Nombre de la sede (" + newNameCampus + "): ");
            String input = leerCadenaNoVaciaTextoPunto();
            if (!input.isEmpty()) {
                newNameCampus = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (texto y puntos). Intente nuevamente.");
            }
        }

        while (true) {
            System.out.print("Nuevo Código de la sede (" + newCodeCampus + "): ");
            String input = leerCadenaNoVaciaTexto();
            boolean codeCampus = false;
            if (!input.isEmpty()) {
                if (!input.equalsIgnoreCase(campusSelected.getCodeCampus())) {
                    for (Campus campus : campusList) {
                        if (campus.getCodeCampus().equalsIgnoreCase(input)) {
                            System.out.println("La sede con este codigo ya esta registrada");
                            codeCampus = true;
                            break;
                        }
                    }
                    if (!codeCampus) {
                        newCodeCampus = input;
                        break;
                    }
                } else {
                    System.out.println("El nuevo código es igual al actual.");
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            }
        }

        campusSelected.setNameCampus(newNameCampus);
        campusSelected.setCodeCampus(newCodeCampus);
        campusSelected.setCentralCampus(newCentralCampus);

        System.out.println("Sede modificada exitosamente");
    }

    private static void deleteRegisterCampus() {                                      //método para eliminar campus registrados
        System.out.println("=== Eliminar Registro de Sedes ===");
        if (campusList.isEmpty()) {
            System.out.println("No hay sedes registradas.");
            return;
        }
        showRegisterCampus();
        System.out.print("Ingrese el índice de la sede que desea eliminar: ");
        int indice = leerIndiceValido(campusList.size());
        campusList.remove(indice);
        System.out.println("Sede eliminada exitosamente.");
    }

    private static void showRegisterCompany() {
        System.out.println("=== Empresas Registradas ===");

        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
        } else {
            int index = 0;
            for (Company company : companyList) {
                System.out.println("Índice " + index + ": " + company);
                index++;
            }
        }
    }
    private static void registerCompany() {
        System.out.println("=== Registrar Empresa ===");

        String nameCompany= null;
        String codeCompany = null;

        // Ciclo para validar y registrar los datos del estudiante
        while (true) {
            // Si el codigo del campus no se ha registrado
            if (codeCompany == null) {
                System.out.print("Id Empresa: ");
                codeCompany = leerCadenaNoVacia();

                // Validar si el campus ya esta registrado codeCampus
                for (Company company : companyList) {
                    if (company.getCodeCompany().equalsIgnoreCase(codeCompany)) {
                        System.out.println("Ya se encuentra una empresa con el codigo proporcionado");
                        codeCompany= null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si el nombre de la sede no se ha ingresado
            else if (nameCompany == null) {
                System.out.print("Nombre: ");
                nameCompany = leerCadenaNoVaciaTexto();
            } // Validar si es la sede principal


            // Si se han ingresado todos los datos requeridos, registrar la persona
            if (codeCompany != null && nameCompany!= null) {
                companyList.add(new Company(nameCompany, codeCompany, campusList));
                System.out.println("Empresa registrada exitosamente");
                //guardarCampusEnArchivo();
                break; // Salir del bucle en caso de éxito
            }
        }
    }

    private static void modifyCompany() {
        System.out.println("=== Modificar  Empresa ===");

        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }

        showRegisterCompany();

        System.out.print("Ingrese el índice de la empresa que desea modificar: ");
        int indexCompany= leerIndiceValido(companyList.size());

        Company companySelected = companyList.get(indexCompany);

        String newNameCompany = companySelected.getNameCompany();
        String newCodeCompany = companySelected.getCodeCompany();


        while (true) {
            System.out.print("Nuevo Nombre de la empresa (" + newNameCompany + "): ");
            String input = leerCadenaNoVaciaTextoPunto();
            if (!input.isEmpty()) {
                newNameCompany = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (texto y puntos). Intente nuevamente.");
            }
        }

        while (true) {
            System.out.print("Nuevo Código de la empresa (" + newCodeCompany + "): ");
            String input = leerCadenaNoVaciaTexto();
            boolean codeCampus = false;
            if (!input.isEmpty()) {
                if (!input.equalsIgnoreCase(companySelected.getCodeCompany())) {
                    for (Company company : companyList) {
                        if (company.getCodeCompany().equalsIgnoreCase(input)) {
                            System.out.println("La empresa con este codigo ya esta registrada");
                            codeCampus = true;
                            break;
                        }
                    }
                    if (!codeCampus) {
                        newCodeCompany = input;
                        break;
                    }
                } else {
                    System.out.println("El nuevo código es igual al actual.");
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            }
        }

        companySelected.setNameCompany(newNameCompany);
        companySelected.setCodeCompany(newCodeCompany);

        System.out.println("Empresa modificada exitosamente");
    }

    private static void deleteRegisterCaompany() {                                      //método para eliminar campus registrados
        System.out.println("=== Eliminar Registro de la Empresa ===");
        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }
        showRegisterCampus();
        System.out.print("Ingrese el índice de la empresa que desea eliminar: ");
        int indexCompany = leerIndiceValido(companyList.size());
        companyList.remove(indexCompany);
        System.out.println("Empresa eliminada exitosamente.");
    }

    private static String leerCadenaNoVacia() {
        String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }
    private static String leerCadenaNoVaciaTexto() {
        String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z\\s]+$")) {
                    return input;
                }
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }

    private static int leerIndiceValido(int maximo) {
        int indice;
        while (true) {
            try {
                System.out.print("Ingrese un índice válido: ");
                String input = reader.readLine().trim();
                indice = Integer.parseInt(input);
                if (indice >= 0 && indice <= maximo) {
                    break;
                }
                System.out.println("Índice no válido. Intente nuevamente.");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return indice;
    }

    private static String leerCodigoNumerico() {
        String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[0-9]+$")) { // Verifica que la entrada contenga solo números
                    return input;
                }
                System.out.println("Ingrese un valor válido (solo números). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }

    private static String leerCadenaNoVaciaTextoPunto() {
        String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z.\\s]+$")) {
                    return input;
                }
                System.out.println("Ingrese un valor válido (texto y puntos). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }
}
