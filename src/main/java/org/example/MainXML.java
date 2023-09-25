package org.example;

import java.io.BufferedReader;    // Importa la clase BufferedReader, que se utiliza para leer texto de un flujo de entrada
import java.io.FileWriter;        // Importa la clase FileWriter, que se utiliza para escribir caracteres en un archivo de texto
import java.io.IOException;       // Importa la clase IOException, que se utiliza para manejar excepciones relacionadas con entrada/salida
import java.io.InputStreamReader; // Importa la clase InputStreamReader, que se utiliza para leer caracteres desde un flujo de entrada
import java.nio.file.Files;  // Importa la clase Files de java.nio.file para trabajar con operaciones relacionadas con archivos y directorios.
import java.nio.file.Paths;  // Importa la clase Paths de java.nio.file para gestionar rutas de archivos y directorios de manera eficiente.
import java.util.*;
import java.nio.file.Path;        // Importación necesaria para trabajar con rutas de archivos.
import com.fasterxml.jackson.core.type.TypeReference;// Importación necesaria para manejar tipos genéricos al deserializar JSON con Jackson.
import com.fasterxml.jackson.databind.ObjectMapper; // Importación necesaria para utilizar ObjectMapper de Jackson para la deserialización y serialización de JSON.
import java.io.File; // Importa la clase File, que se utiliza para trabajar con archivos en el sistema de archivos.

import javax.xml.parsers.ParserConfigurationException; // Importa la clase ParserConfigurationException, que se utiliza para manejar excepciones relacionadas con la configuración del analizador XML.
import javax.xml.transform.TransformerException; // Importa la clase TransformerException, que se utiliza para manejar excepciones relacionadas con la transformación de XML.

import enums.TypeJob;
import models.Campus;
import models.Company;
import models.Employee;
import models.Person;
import org.w3c.dom.DOMException; // Importa la clase DOMException, que se utiliza para manejar excepciones relacionadas con el Document Object Model (DOM) de XML.
import javax.xml.transform.Transformer; // Importa la clase Transformer, que se utiliza para transformar documentos XML.
import javax.xml.transform.TransformerFactory; // Importa la clase TransformerFactory, que se utiliza para crear instancias de Transformer.
import javax.xml.transform.OutputKeys; // Importa la clase OutputKeys, que contiene constantes para configurar la salida de la transformación XML.
import javax.xml.transform.dom.DOMSource; // Importa la clase DOMSource, que se utiliza como fuente de datos para la transformación XML.
import javax.xml.transform.stream.StreamResult; // Importa la clase StreamResult, que se utiliza como destino de datos para la transformación XML.
import javax.xml.parsers.DocumentBuilderFactory; // Importa la clase DocumentBuilderFactory, que se utiliza para crear instancias de DocumentBuilder para analizar documentos XML.
import javax.xml.parsers.DocumentBuilder; // Importa la clase DocumentBuilder, que se utiliza para analizar documentos XML.

import org.w3c.dom.Document; // Importa la clase Document, que representa un documento XML en el DOM.
import org.w3c.dom.Element; // Importa la clase Element, que representa un elemento XML en el DOM.
import org.w3c.dom.NodeList; // Importa la clase NodeList, que se utiliza para trabajar con listas de nodos en el DOM XML.
import views.View;
//Document Object Model (Modelo de Objetos de Documento)

public class MainXML {

    private static List<Person> personList = new ArrayList<>();
    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Campus> campusList = new ArrayList<>();
    private static List<Company> companyList = new ArrayList<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static View view = new View();

    public static void main(String[] args) {

        // llamado a los metodos para persistir los archivos txt y XML
        cargarPersonasDesdeArchivo_json();
        cargarEmpresasDesdeArchivo_json();
        cargarSedesDesdeArchivo_json();
        cargarPersonasDesdeArchivo_json();
        cargarEmpleadosDesdeArchivo_json();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarEmpresasEnArchivo_json();
            guardarSedesEnArchivo_json();
            guardarPersonasEnArchivo_json();
            guardarEmpleadosEnArchivo_json();
            view.messageSaveDate();
        }));

        int opcion, opcionSub;
        do{
            showMenu();
            opcion = leerOpcion();
            String typeMenu;
            switch (opcion){
                case 1 -> {
                    do{
                        typeMenu = "registerPerson.xml";
                        cargarMenuDesdeXML(typeMenu);
                        opcionSub = leerOpcion();
                        switch (opcionSub) {
                            case 1 -> ver_personas_registradas(); //Ver personas registradas
                            case 2 -> registrarPersona();  //Registrar_Persona
                            case 3 -> modificar_Persona();//Modificar_Persona
                            case 4 -> eliminar_Persona(); //Eliminar_Persona
                            case 0 -> view.showBye();
                            default -> System.out.println("Opción no válida. Intente nuevamente");
                        }
                    }while (opcionSub != 0);
                }
                //TODO CARGAR LOS DATOS DE ORCAR
                case 2 -> {
                    do{
                        typeMenu = "registerEmployee.xml";
                        cargarMenuDesdeXML(typeMenu);
                        opcionSub = leerOpcion();
                        switch (opcionSub) {
                            case 1 -> verEmpleadosRegistrados(); //Ver personas registradas
                            case 2 -> registrarEmpleados();  //Registrar_Empleado
                            case 3 -> modificarEmpleado();//Modificar_Persona
                            case 4 -> desasociarEmpleadoPersona();
                            case 5 -> eliminarEmpleado(); //Eliminar_Persona
                            case 0 -> view.showBye();
                            default -> view.messageNotValidated();
                        }
                    }while (opcionSub != 0);
                }
                case 3 -> {
                    do{
                        typeMenu = "registerCampus.xml";
                        cargarMenuDesdeXML(typeMenu);
                        opcionSub = leerOpcion();
                        switch (opcionSub) {
                            case 1 -> verSedesRegistradas(); //Ver personas registradas
                            case 2 -> registrarSede();  //Registrar_Persona
                            case 3 -> modificar_sede_principal_empresa();//Modificar_Persona
                            case 4 -> eliminarSede(); //Eliminar_Persona
                            case 0 -> view.showBye();
                            default -> view.messageNotValidated();
                        }
                    }while (opcionSub != 0);
                }

                case 4 -> {
                    do{
                        typeMenu = "registerCompany.xml";
                        cargarMenuDesdeXML(typeMenu);
                        opcionSub = leerOpcion();
                        switch (opcionSub) {
                            case 0 -> view.showBye();
                            case 1 -> verEmpresasRegistradas(); //Ver personas registradas
                            case 2 -> registrarEmpresa();  //Registrar_Empleado
                            case 3 -> modificarEmpresa();//Modificar_Persona
                            case 4 -> eliminarEmpresa(); //Eliminar_Persona
                            case 5 -> asociarSedeEmpresa();
                            case 6 -> desasociarSedeEmpresa();
                            case 7 -> ver__Sedes_Empresa();
                            case 8 -> elegir_sede_principal_empresa();
                            default -> view.messageNotValidated();
                        }
                    }while (opcionSub != 0);
                }
                case 0 ->
                        view.showBye();
                default ->
                        view.messageNotValidated();

            }
        }while (opcion != 0);
        guardarEmpresasEnArchivo_json();
        guardarSedesEnArchivo_json();
        guardarPersonasEnArchivo_json();
// Llamado a los métodos para guardar los datos antes de salir


    }
    /**
     * Carga y muestra el menú de opciones desde un archivo XML. El método lee
     * el archivo "menu.xml", analiza su contenido y muestra las opciones en la
     * consola. Si se produce un error durante el proceso de lectura o análisis,
     * muestra un mensaje de error.
     */
    private static void showMenu() {
        try {
// Crear un objeto File que representa el archivo "menu.xml"
            File menuFile = new File("mainMenu.xml");
// Crear una instancia de DocumentBuilderFactory para configurar el analizador XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
// Crear un objeto DocumentBuilder para analizar el contenido XML
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
// Analizar el contenido del archivo XML y crear un documento DOM: Document Object Model (Modelo de Objetos de Documento)
            Document doc = dBuilder.parse(menuFile);
// Obtener la lista de nodos "option" del documento XML
            NodeList options = doc.getElementsByTagName("option");
// Mostrar el menú en la consola
            System.out.println("=== MENÚ EN/ES ===");
            for (int i = 0; i < options.getLength(); i++) {
                Element option = (Element) options.item(i);
                System.out.println((i) + ". " + option.getTextContent());
            }
        } catch (Exception e) {
            view.errorMesageXMl();
        }
    }

    ////////////////////

    /**
     * Carga y muestra el menú de opciones desde un archivo XML. El método lee
     * el archivo "menu.xml", analiza su contenido y muestra las opciones en la
     * consola. Si se produce un error durante el proceso de lectura o análisis,
     * muestra un mensaje de error.
     */
    private static void cargarMenuDesdeXML(String pathname) {
        try {
// Crear un objeto File que representa el archivo "menu.xml"
            File menuFile = new File(pathname);
// Crear una instancia de DocumentBuilderFactory para configurar el analizador XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
// Crear un objeto DocumentBuilder para analizar el contenido XML
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
// Analizar el contenido del archivo XML y crear un documento DOM: Document Object Model (Modelo de Objetos de Documento)
            Document doc = dBuilder.parse(menuFile);
// Obtener la lista de nodos "option" del documento XML
            NodeList options = doc.getElementsByTagName("option");
// Mostrar el menú en la consola
            System.out.println("=== MENÚ EN/ES ===");
            for (int i = 0; i < options.getLength(); i++) {
                Element option = (Element) options.item(i);
                System.out.println((i) + ". " + option.getTextContent());
            }
        } catch (Exception e) {
           view.errorMesageXMl();
        }
    }

    /**
     * Este método se utiliza para guardar la lista de empresas en un archivo
     * JSON. Utiliza la biblioteca Jackson ObjectMapper para serializar la lista
     * de empresas en formato JSON y escribirlo en el archivo "empresas.json".
     * Si el archivo no existe, se crea. El JSON resultante se formatea con
     * saltos de línea y sangría para una mejor legibilidad.
     */
    private static void guardarEmpresasEnArchivo_json() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empresas.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de departamentos en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), companyList);
            view.dateArchiveCompany(); // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
           view.errorSaveDateJson();
        }
    }

    private static void cargarEmpresasDesdeArchivo_json() {
        companyList.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empresas.json");

            if (Files.exists(filePath)) {
                List<Company> empresasCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Company>>() {
                });

                // Iteramos sobre los departamentos cargados y añadimos cada uno a la lista de departamentos actual.
                for (Company empresa : empresasCargadas) {
                    // Verificamos si el departamento ya existe en la lista actual.
                    boolean existe = false;
                    for (Company d : companyList) {
                        if (d.getCodigo_empresa().equals(empresa.getCodigo_empresa())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        companyList.add(empresa);
                    }
                }

                view.loadArchiveCompany();
            } else {
                view.notFoundArchiveCompany();
            }
        } catch (IOException e) {
           view.errorLoadArchiveCompany();
        }
    }

    private static void verEmpresasRegistradas() {
        if (companyList.isEmpty()) {                                        //Verifica si la lista está vacía
           view.notRegisterCompany();
        } else {
            view.companyRegister();
            int index = 0;
            for (Company empresa : companyList) {                     //bucle for-each que recorre la lista
                System.out.println("Índice " + index + ": " + empresa);
                index++;
            }
        }
    }

    private static void registrarEmpresa() {
        view.registerCompany();

        // Declaración de variables para almacenar los datos del país
        String nombre_empresa = null;
        String codigo_empresa = null;
        // Ciclo para validar y registrar los datos
        while (true) {
            if (nombre_empresa == null) {
                view.messageNameCompany();
                nombre_empresa = leerCadenaNoVaciaTexto().toUpperCase();

                // Validar si el pais ya está registrado por nombre
                for (Company empresa : companyList) {
                    if (empresa.getNombre_empresa().equalsIgnoreCase(nombre_empresa)) {
                        view.messageCompanyRegister();
                        nombre_empresa = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si el codigo del pais aún no se ha ingresado
            else if (codigo_empresa == null) {
                view.messageCodeCompany();
                codigo_empresa = leerCodigoNumerico();

                // Validar si el pais ya está registrado por código
                for (Company empresa : companyList) {
                    if (empresa.getCodigo_empresa().equals(codigo_empresa)) {
                        view.messageCompanyRegister();
                        codigo_empresa = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            }
            // Si se han ingresado todos los datos requeridos, registrar el país
            if (nombre_empresa != null && codigo_empresa != null) {
                companyList.add(new Company(nombre_empresa, codigo_empresa));
                view.messageRegisterTrue();
                guardarEmpresasEnArchivo_json();
                break; // Salir del bucle en caso de éxito
            }
        }
    }

    private static void modificarEmpresa() {
        view.messageModifyCompany();

        // Verificar si hay empresas registrados
        if (companyList.isEmpty()) {
            view.notRegisterCompany();
            return;
        }

        // Mostrar la lista de empresas registrados
        verEmpresasRegistradas();

        view.messageIndexCompany();
        int indice = leerIndiceValido(companyList.size());
        Company empresaSeleccionada = companyList.get(indice);

        // Inicializar variables con los valores actuales de la empresa seleccionada
        String nuevoNombreEmpresa = empresaSeleccionada.getNombre_empresa();
        String nuevoCodigoEmpresa = empresaSeleccionada.getCodigo_empresa();

        // Solicitar al usuario que ingrese el nuevo nombre de empresa
        while (true) {
            System.out.print("Nuevo nombre de empresa(" + nuevoNombreEmpresa + "): ");
            String input = leerCadenaNoVaciaTexto().toUpperCase();
            boolean nombreEmpresaRegistrada = false;
            if (!input.isEmpty()) {
                for (Company empresa : companyList) {
                    if (empresa.getNombre_empresa().equals(input) && !empresa.getNombre_empresa().equals(nuevoNombreEmpresa)) {
                        view.messageCompanyRegister();
                        nombreEmpresaRegistrada = true;
                        break;
                    }
                }
                if (!nombreEmpresaRegistrada) {
                    nuevoNombreEmpresa = input;
                    break;
                }
            } else {
                view.notCamposVacio();
            }
        }

        // Solicitar al usuario que ingrese el nuevo código de empresa
        while (true) {
            System.out.print("Nuevo código de la empresa(" + nuevoCodigoEmpresa + "): ");
            String input = leerCodigoNumerico();
            boolean codigoEmpresaRegistrada = false;
            if (!input.isEmpty()) {
                for (Company empresa : companyList) {
                    if (empresa.getCodigo_empresa().equals(input) && !empresa.getCodigo_empresa().equals(nuevoCodigoEmpresa)) {
                     view.messageCompanyRegister();
                        codigoEmpresaRegistrada = true;
                        break;
                    }
                }
                if (!codigoEmpresaRegistrada) {
                    nuevoCodigoEmpresa = input;
                    break;
                }
            } else {
             view.notCamposVacio();
            }
        }
        empresaSeleccionada.setNombre_empresa(nuevoNombreEmpresa);
        empresaSeleccionada.setCodigo_empresa(nuevoCodigoEmpresa);
        view.modifyCompanyTrue();
        guardarEmpresasEnArchivo_json();

    }

    private static void eliminarEmpresa() {
        view.messageDeleteCompany();
        if (companyList.isEmpty()) {
           view.notRegisterCompany();
            return;
        }
        verEmpresasRegistradas();
        view.messageIndexCompanyDelete();
        int indice = leerIndiceValido(companyList.size());
        companyList.remove(indice);
        view.companyDeleteTrue();
        guardarEmpresasEnArchivo_json();
    }

    private static void verSedesRegistradas() {
        if (campusList.isEmpty()) {
          view.notCampusRegister();
        } else {
           view.messageRegisterCampus();
            int index = 0;
            for (Campus sede : campusList) {                     //bucle for-each que recorre la lista
                System.out.println("Índice " + index + ": " + sede);
                index++;
            }
        }
    }

    private static void registrarSede() {
        view.campusRegister();

        // Declaración de variables para almacenar los datos
        String nombre_sede = null;
        String codigo_sede = null;

        // Ciclo para validar y registrar los datos
        while (true) {
            if (nombre_sede == null) {
                System.out.print("nombre sede: ");
                nombre_sede = leerCadenaNoVaciaTexto().toUpperCase();

                // Validar si el  ya está registrado por nombre
                for (Campus sede : campusList) {
                    if (sede.getNombre_sede().equalsIgnoreCase(nombre_sede)) {
                        view.messageCampusRegister();
                        nombre_sede = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si el codigo de la sede aún no se ha ingresado
            else if (codigo_sede == null) {
                System.out.print("codigo sede: ");
                codigo_sede = leerCodigoNumerico();

                // Validar si la sede ya está registrado por código
                for (Campus sede : campusList) {
                    if (sede.getCodigo_sede().equals(codigo_sede)) {
                        view.messageCampusRegister();
                        codigo_sede = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            }

            // Si se han ingresado todos los datos requeridos, registrar la SEDE
            if (nombre_sede != null && codigo_sede != null) {
                campusList.add(new Campus(nombre_sede, codigo_sede, "N"));
                System.out.println("sede registrada exitosamente.");
                guardarSedesEnArchivo_json();
                break; // Salir del bucle en caso de éxito
            }
        }
    }

    private static void eliminarSede() {
        System.out.println("=== Eliminar Registro de sedes ===");
        if (campusList.isEmpty()) {
            view.notCampusRegister();
            return;
        }
        verSedesRegistradas();
        System.out.print("Ingrese el índice de la sede que desea eliminar: ");
        int indice = leerIndiceValido(campusList.size());
        campusList.remove(indice);
        System.out.println("sede eliminada exitosamente.");
        guardarSedesEnArchivo_json();
    }

    //asociar sede a empresa
    private static void asociarSedeEmpresa() {
        cargarEmpresasDesdeArchivo_json(); // Cargar las empresa registradas desde el archivo JSON

        System.out.println("=== ASOCIAR SEDE A EMPRESA===");

        if (campusList.isEmpty()) {
            System.out.println("No hay sedes registradas.");
            return;
        }
        verSedesRegistradas();

        System.out.print("Ingrese el índice de la sede que desea asociar a la empresa: ");
        int indiceSede = leerIndiceValido(campusList.size());

        Campus sedeSeleccionada = campusList.get(indiceSede);

        // Verificar si la sede ya está asociada a alguna empresa
        for (Company empresa : companyList) {
            for (Campus sede : empresa.getLista_sedes_empresa()) {
                if (sede.getCodigo_sede().equals(sedeSeleccionada.getCodigo_sede())) {
                    System.out.println("La sede ya está asociada a una Empresa.");
                    return;
                }
            }
        }

        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }
        verEmpresasRegistradas();

        System.out.print("Ingrese el índice de la empresa para la que desea asociar la sede: ");
        int indiceEmpresa = leerIndiceValido(companyList.size());

        Company empresaSeleccionada = companyList.get(indiceEmpresa);

        // Verificar si la sede ya está asociada a la empresa seleccionada
        for (Campus sede : empresaSeleccionada.getLista_sedes_empresa()) {
            if (sede.getCodigo_sede().equals(sedeSeleccionada.getCodigo_sede())) {
                System.out.println("La sede ya está asociada a la empresa.");
                return;
            }
        }

        empresaSeleccionada.getLista_sedes_empresa().add(sedeSeleccionada);
        System.out.println("Sede asociada exitosamente a la Empresa");
        guardarEmpresasEnArchivo_json();
    }

    private static void desasociarSedeEmpresa() {
        System.out.println("=== Desasociar sede de empresa===");

        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }

        // Mostrar la lista de empresas
        verEmpresasRegistradas();
        cargarEmpresasDesdeArchivo_json();

        System.out.print("Ingrese el índice de la empresa de la que desea desasociar una sede: ");
        int indiceEmpresa = leerIndiceValido(companyList.size());

        Company empresaSeleccionada = companyList.get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
            System.out.println("No hay sedes asociadas a esta empresa.");
            return;
        }

        // Mostrar la lista de sedes de la empresa seleccionado
        System.out.println("Sedes asociadas a la empresa" + empresaSeleccionada.getLista_sedes_empresa() + ":");
        for (int i = 0; i < empresaSeleccionada.getLista_sedes_empresa().size(); i++) {
            Campus sede = empresaSeleccionada.getLista_sedes_empresa().get(i);
            System.out.println(i + ": " + sede.getNombre_sede());
        }

        System.out.print("Ingrese el índice de la sede que desea desasociar de la empresa: ");
        int indiceSede = leerIndiceValido(empresaSeleccionada.getLista_sedes_empresa().size());

        Campus sedeSeleccionada = empresaSeleccionada.getLista_sedes_empresa().get(indiceSede);

        // Desasociar la sede de la empresa
        empresaSeleccionada.getLista_sedes_empresa().remove(sedeSeleccionada);
        System.out.println("Sede desasociada exitosamente de la empresa");
        guardarEmpresasEnArchivo_json();
    }

    private static void ver__Sedes_Empresa() {
        System.out.println("=== Sedes de la Empresa ===");

        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }
        verEmpresasRegistradas();
        cargarEmpresasDesdeArchivo_json();

        System.out.print("Ingrese el índice de la Empresa de la cual desea ver las sedes registradas: ");
        int indiceEmpresa = leerIndiceValido(companyList.size());

        Company empresaSeleccionada = companyList.get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
            System.out.println("No hay sedes en esta Empresa");
        } else {
            System.out.println("Sedes asociadas a " + empresaSeleccionada.getNombre_empresa() + ":");
            for (Campus sede : empresaSeleccionada.getLista_sedes_empresa()) {
                System.out.println("- " + sede.getNombre_sede() + " (" + sede.getCodigo_sede() + ")" + " ( Sede Principal: " + sede.getSede_principal() + ")");
            }
        }
    }

    private static void elegir_sede_principal_empresa() {
        System.out.println("=== ELEGIR SEDE PRINCIPAL DE LA EMPRESA===");

        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }
        verEmpresasRegistradas();

        System.out.print("Ingrese el índice de la empresa de la que desea seleccionar la sede principal: ");
        int indiceEmpresa = leerIndiceValido(companyList.size());

        Company empresaSeleccionada = companyList.get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
            System.out.println("La empresa seleccionada no tiene sedes asociadas.");
            return;
        }

        System.out.println("sedes de " + empresaSeleccionada.getNombre_empresa() + ":");
        for (int i = 0; i < empresaSeleccionada.getLista_sedes_empresa().size(); i++) {
            System.out.println(i + ". " + empresaSeleccionada.getLista_sedes_empresa().get(i).getNombre_sede());
        }

        System.out.print("Ingrese el índice de la sede que desea elegir como sede principal de la empresa: ");
        int indiceSede = leerIndiceValido(empresaSeleccionada.getLista_sedes_empresa().size());

        Campus sedeSeleccionada = empresaSeleccionada.getLista_sedes_empresa().get(indiceSede);

        if ("S".equals(sedeSeleccionada.getCodigo_sede())) {
            System.out.println("La sede seleccionada ya es sede principal de una empresa");
        } else {

            // Verificar si otra sede ya es la principal de la empresa
            boolean sedePrincipalYaElegida = false;
            for (Campus sede : empresaSeleccionada.getLista_sedes_empresa()) {
                if (!sede.equals(sedeSeleccionada) && "S".equals(sede.getSede_principal())) {
                    sedePrincipalYaElegida = true;
                    break;
                }
            }

            if (sedePrincipalYaElegida) {
                System.out.println("Ya se ha elegido otra sede principal para la empresa seleccionada");
            } else {
                sedeSeleccionada.setSede_principal("S");
                System.out.println("sede seleccionada como sede ppal de la empresa");
                guardarEmpresasEnArchivo_json();

            }
        }
    }

    private static void modificar_sede_principal_empresa() {
        System.out.println("=== MODIFICAR SEDE PRINCIPAL DE LA EMPRESA ===");

        if (campusList.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }

        // Mostrar la lista de empresas
        verEmpresasRegistradas();

        System.out.print("Ingrese el índice de la empresa para la que desea modificar la sede principal: ");
        int indiceEmpresa = leerIndiceValido(companyList.size());

        Company empresaSeleccionada = companyList.get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
            System.out.println("La empresa seleccionada no tiene sedes asociadas");
            return;
        }

        System.out.println("Sedes de " + empresaSeleccionada.getNombre_empresa() + ":");
        for (int i = 0; i < empresaSeleccionada.getLista_sedes_empresa().size(); i++) {
            System.out.println(i + ". " + empresaSeleccionada.getLista_sedes_empresa().get(i).getNombre_sede());
        }

        System.out.print("Ingrese el índice de la sedes que desea elegir como nueva sede principal de la empresa: ");
        int indiceSede = leerIndiceValido(empresaSeleccionada.getLista_sedes_empresa().size());

        Campus nuevaSedePrincipal = empresaSeleccionada.getLista_sedes_empresa().get(indiceSede);

        // Cambiar- actualizar la sede pincipal de la empresa
        for (Campus sede : empresaSeleccionada.getLista_sedes_empresa()) {
            if ("S".equals(sede.getSede_principal())) {
                sede.setNombre_sede("N");
                break;
            }
        }
        nuevaSedePrincipal.setSede_principal("S");
        System.out.println("Sede actualizada/ seleccionada como sede principal de la empresa.");
        guardarEmpresasEnArchivo_json();

    }


    private static void guardarSedesEnArchivo_json() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("sedes.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), campusList);
            System.out.println("Datos de Sede guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de Sede en el archivo JSON.");
        }
    }

    private static void cargarSedesDesdeArchivo_json() {
        campusList.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("sedes.json");

            if (Files.exists(filePath)) {
                List<Campus> sedesCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Campus>>() {
                });

                // Iteramos sobre los departamentos cargados y añadimos cada uno a la lista de departamentos actual.
                for (Campus sede : sedesCargadas) {
                    // Verificamos si el departamento ya existe en la lista actual.
                    boolean existe = false;
                    for (Campus d : campusList) {
                        if (d.getCodigo_sede().equals(sede.getCodigo_sede())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        campusList.add(sede);
                    }
                }

                System.out.println("Datos de Sede cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'sedes.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de sedes desde el archivo JSON.");
        }
    }

    private static void guardarEmpleadosEnArchivo_json() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empleados.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), employeeList);
            System.out.println("Datos de empleado guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de empleado en el archivo JSON.");
        }
    }

    private static void cargarEmpleadosDesdeArchivo_json() {
        employeeList.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empleados.json");

            if (Files.exists(filePath)) {
                List<Employee> empleadosCargados = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Employee>>() {
                });

                // Iteramos sobre las personas cargadas y añadimos cada uno a la lista de personas actual.
                for (Employee empleado : empleadosCargados) {
                    // Verificamos si la persona ya existe en la lista actual.
                    boolean existe = false;
                    for (Employee d : employeeList) {
                        if (d.getTypeJob().equals(empleado.getTypeJob())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        employeeList.add(empleado);
                    }
                }

                System.out.println("Datos de empleados cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'empleados.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de empleados desde el archivo JSON.");
        }
    }

    ///////////////////
    private static void guardarPersonasEnArchivo_json() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("persona.json");
            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), personList);
            System.out.println("Datos de persona guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de persona en el archivo JSON.");
        }
    }

    private static void cargarPersonasDesdeArchivo_json() {
        personList.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("persona.json");

            if (Files.exists(filePath)) {
                List<Person> personasCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Person>>() {
                });
                // Iteramos sobre las personas cargadas y añadimos cada uno a la lista de personas actual.
                for (Person persona : personasCargadas) {
                    // Verificamos si la persona ya existe en la lista actual.
                    boolean existe = false;
                    for (Person d : personList) {
                        if (d.getIdPersona().equals(persona.getIdPersona())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        personList.add(persona);
                    }
                }
                System.out.println("Datos de persona cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'persona.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de personas desde el archivo JSON.");
        }
    }



    private static boolean validarRegistroEmpleado(Person persona, TypeJob cargo) {
        for (Employee empleado : employeeList) {
            if (empleado.getTypeJob() == cargo) {
                System.out.println("Esta persona ya pertenece al cargo seleccionado.");
                return false;// No se permite el registro.
            }
        }
            for (Employee empleado: employeeList) {
            if (empleado.getTypeJob() != null) {
                System.out.println("El empleado pertenece a otro cargo");
                return false;
            }
        }
        return true;
    }



    private static void registrarEmpleados() {
        System.out.println("=== Registrar Empleado ===");

        // Verificar si hay personas registradas
        if (personList.isEmpty()) {
            System.out.println("No hay personas registradas. Registre al menos una persona antes de crear un empleado.");
            return;
        }

        ver_personas_registradasFor_Employees();

        // Obtener la selección de la persona asociada al cargo
        System.out.print("Seleccione una persona de la lista (1-" + personList.size()+ "): ");
        int opcionPersona = leerIndicePersona();

        // Validar la selección de persona
        if (opcionPersona < 1 || opcionPersona > personList.size()) {
            System.out.println("Selección de persona no válida. Por favor, seleccione una persona válida.");
            return;
        }

        // Obtener la persona seleccionada
        Person personaSeleccionada = personList.get(opcionPersona - 1);
        // Después de agregar el empleado exitosamente


        // Verificar si ya existe un empleado con el mismo cargo
        TypeJob cargoSeleccionado = TypeJob.valueOf(jobTitles());
        boolean empleadoExistente = false;
        if(validarRegistroEmpleado(personaSeleccionada, cargoSeleccionado) == false){
            return;
        }
        for (Employee empleado : employeeList) {
            if (empleado.getTypeJob() == cargoSeleccionado) {
                empleado.getLista_personas_cargo().add(personaSeleccionada);
                System.out.println("Persona registrada exitosamente como empleado.");
                guardarEmpleadosEnArchivo_json();
                empleadoExistente = true;
                break; // Salir del bucle una vez que la persona haya sido asociada al cargo.
            }
        }


        // Si no existe un empleado con el mismo cargo, se crea uno nuevo
        if (!empleadoExistente) {
            Employee nuevoEmpleado = new Employee(cargoSeleccionado, new ArrayList<>());
            nuevoEmpleado.getLista_personas_cargo().add(personaSeleccionada);
            employeeList.add(nuevoEmpleado);
            System.out.println("Empleado registrado exitosamente.");
            System.out.println(employeeList.size());
            guardarEmpleadosEnArchivo_json();
        }
    }



    private static void verEmpleadosRegistrados () {
        System.out.println("=== Empleados Registrados ===");

        // Verificar si hay empleados registrados
        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        // Recorrer la lista de empleados y mostrar sus detalles
        for (int i = 0; i < employeeList.size(); i++) {
            Employee empleado = employeeList.get(i);
            System.out.println("Empleado :");
            System.out.println("Cargo: " + empleado.getTypeJob());

            List<Person> personasAsociadas = empleado.getLista_personas_cargo();
            System.out.println("Personas asociadas al cargo:");
            for (int j = 0; j < personasAsociadas.size(); j++) {
                Person persona = personasAsociadas.get(j);
                System.out.println("  " + (j + 1) + ". ID Persona: " + persona.getIdPersona());
                System.out.println("     Nombre y Apellido: " + persona.getNombreApellidoPersona());
                System.out.println("     Empleado Jefe (S/N): " + persona.getIsEmployeeLeader());
            }

            System.out.println();
        }
    }

    private static void verEmpleadosAux() {
        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("=== empleados registrados ===");
            int index = 0;
            for (Employee empleado : employeeList) {                     //bucle for-each que recorre la lista
                System.out.println("Índice " + index + ": " + empleado);
                index++;
            }
        }
    }
    private static void modificarEmpleado () {
        System.out.println("=== Modificar Empleado ===");

        for (int i = 0; i < employeeList.size(); i++) {
            Employee empleado = employeeList.get(i);
            System.out.println("Empleado :");
            System.out.println("Cargo: " + empleado.getTypeJob());

            List<Person> personasAsociadas = empleado.getLista_personas_cargo();
            System.out.println("Personas asociadas al cargo:");
            for (int j = 0; j < personasAsociadas.size(); j++) {
                Person persona = personasAsociadas.get(j);
                System.out.println("  " + (j + 1) + ". ID Persona: " + persona.getIdPersona());
                System.out.println("     Nombre y Apellido: " + persona.getNombreApellidoPersona());
                System.out.println("     Empleado Jefe (S/N): " + persona.getIsEmployeeLeader());
            }
            System.out.println();
        }

        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.print("Ingrese el índice del empleado que desea modificar: ");
        int indiceSeleccionado = leerIndiceValido(personList.size());
        System.out.println(indiceSeleccionado);
        System.out.println(employeeList.size());
        // Verificar que el índice esté dentro del rango válido
        if (indiceSeleccionado >= 0 && indiceSeleccionado < employeeList.size()) {
            Employee empleadoAModificar = employeeList.get(indiceSeleccionado);

            System.out.println("Empleado seleccionado:");
            System.out.println("Cargo actual: " + empleadoAModificar.getTypeJob());
            System.out.println("Nombre del empleado actual: " + empleadoAModificar.getLista_personas_cargo().get(0).getNombreApellidoPersona());

            System.out.print("Nuevo cargo: ");
            String nuevoCargo = leerCadenaNoVaciaTexto();
            if (!nuevoCargo.isEmpty()) {
                empleadoAModificar.setTypeJob(TypeJob.valueOf(nuevoCargo));
            }

            System.out.println("Cargo de los empleados modificado exitosamente.");

            // Llamar a la función para guardar los empleados en un archivo JSON después de la modificación
            guardarEmpleadosEnArchivo_json();
        } else {
            System.out.println("Índice no válido. No se realizó ninguna modificación.");
        }
    }

    private static void modificarCargo () {
        System.out.println("=== Modificar Cargo ===");

        for (int i = 0; i < employeeList.size(); i++) {
            Employee empleado = employeeList.get(i);
            System.out.println("Cargo: " + empleado.getTypeJob());

            List<Person> personasAsociadas = empleado.getLista_personas_cargo();
            System.out.println("Personas asociadas al cargo:");
            for (int j = 0; j < personasAsociadas.size(); j++) {
                Person persona = personasAsociadas.get(j);
                System.out.println("  " + (j + 1) + ". ID Persona: " + persona.getIdPersona());
                System.out.println("     Nombre y Apellido: " + persona.getNombreApellidoPersona());
                System.out.println("     Empleado Jefe (S/N): " + persona.getIsEmployeeLeader());
            }
            System.out.println();
        }

        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }


        System.out.print("Ingrese el índice del cargo que desea modificar: ");
        int indiceSeleccionado = leerIndiceValido(employeeList.size());
        // Verificar que el índice esté dentro del rango válido
        if (indiceSeleccionado >= 0 && indiceSeleccionado < employeeList.size()) {
            Employee empleadoAModificar = employeeList.get(indiceSeleccionado);

            System.out.println("Cargo actual seleccionado: " + empleadoAModificar.getTypeJob());
            System.out.println("Nombre del empleado actual: " + empleadoAModificar.getLista_personas_cargo().get(0).getNombreApellidoPersona());

            System.out.print("Nuevo cargo: ");
            String nuevoCargo = leerCadenaNoVaciaTexto();
            if (!nuevoCargo.isEmpty()) {
                empleadoAModificar.setTypeJob(TypeJob.valueOf(nuevoCargo));
            }

            System.out.println("Cargo modificado exitosamente.");

            // Llamar a la función para guardar los empleados en un archivo JSON después de la modificación
            guardarEmpleadosEnArchivo_json();
        } else {
            System.out.println("Índice no válido. No se realizó ninguna modificación.");
        }
    }

    private static void desasociarEmpleadoPersona() {
        System.out.println("=== Desasociar persona de empleado ===");

        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        // Mostrar la lista de empresas
        verEmpleadosAux();
       cargarEmpleadosDesdeArchivo_json();

        System.out.print("Ingrese el índice del empleado del que desea desasociar una persona: ");
        int indiceEmpleado = leerIndiceValido(employeeList.size());

        Employee empleadoSeleccionado = employeeList.get(indiceEmpleado);

        if (empleadoSeleccionado.getLista_personas_cargo().isEmpty()) {
            System.out.println("No hay personas asociadas a un empleado");
            return;
        }

        // Mostrar la lista de sedes de la empresa seleccionado
        System.out.println("Personas asociadas a empleado" + empleadoSeleccionado.getLista_personas_cargo() + ":");
        for (int i = 0; i < empleadoSeleccionado.getLista_personas_cargo().size(); i++) {
            Person person = empleadoSeleccionado.getLista_personas_cargo().get(i);
            System.out.println(i + ": " + person.getNombreApellidoPersona());
        }

        System.out.print("Ingrese el índice de la persona que desea desasociar de empleados: ");
        int indicePersona = leerIndiceValido(empleadoSeleccionado.getLista_personas_cargo().size());

        Person personaSeleccionada = empleadoSeleccionado.getLista_personas_cargo().get(indicePersona);

        // Desasociar la sede de la empresa
        empleadoSeleccionado.getLista_personas_cargo().remove(personaSeleccionada);
        System.out.println("Persona desasociada exitosamente de empleados");
        guardarEmpleadosEnArchivo_json();
    }

    private static void eliminarEmpleado() {
        System.out.println("=== Eliminar Registro de empleados ===");
        if (employeeList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        verEmpleadosRegistrados();
        System.out.print("Ingrese el índice del empleado que desea eliminar: ");
        int indice = leerIndiceValido(employeeList.size());
        employeeList.remove(indice);
        System.out.println("empleado eliminado exitosamente.");
        guardarEmpleadosEnArchivo_json();
    }

    public static String jobTitles () {
        System.out.println("=== CARGOS DISPONIBLES ===\n1. " + TypeJob.DIRECTIVO + "(D)" + "\n2. " + TypeJob.ASISTENCIAL + "(A)" + "\n3. " + TypeJob.OPERATIVO + "(O)" +
                "\nIngrese el número del cargo en el que desea registrar a la persona: ");
        int indexJobTitle = leerIndiceValido(TypeJob.values().length + 1);
        String jobTitle = "";
        switch (indexJobTitle) {
            case 1 -> jobTitle = String.valueOf(TypeJob.DIRECTIVO);
            case 2 -> jobTitle = String.valueOf(TypeJob.ASISTENCIAL);
            case 3 -> jobTitle = String.valueOf(TypeJob.OPERATIVO);
            default -> System.out.println("Por favor ingrese un valor valido (1, 2, 3)");
        }
        return jobTitle;
    }

    /*private static void mostrarMenu() {                         // mostrar opciones menú
        System.out.println("=== MENÚ - REGISTRO DE PERSONAS EN/ES===");
        System.out.println("1. Ver personas registradas");
        System.out.println("2. Registrar Persona");
        System.out.println("3. Modificar Persona");
        System.out.println("4. Eliminar Persona");
        System.out.println("0. Salir");
    }*/
            private static int leerOpcion () {
                int opcion;
                while (true) {
                    try {
                        System.out.print("Ingrese una opción: ");
                        String input = reader.readLine().trim();
                        if (!input.isEmpty()) {
                            opcion = Integer.parseInt(input);
                            if (opcion >= 0 && opcion <= 8) {
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

            private static String leerCodigoNumerico () {
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

            private static int leerIndiceValido ( int maximo){
                int indice;
                while (true) {
                    try {
                        System.out.print("Ingrese un índice válido: ");
                        String input = reader.readLine().trim();
                        indice = Integer.parseInt(input);
                        if (indice >= 0 && indice < maximo) {
                            break;
                        }
                        System.out.println("Índice no válido. Intente nuevamente.");
                    } catch (NumberFormatException | IOException e) {
                        System.out.println("Error: Ingrese un número válido.");
                    }
                }
                return indice;
            }

            private static String leerCadenaNoVaciaTexto () {
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
    private static void registrarPersona() {
        System.out.println("=== Registrar Persona ===");

        // Ciclo para validar y registrar los datos de la persona
        while (true) {
            // Declaración de variables para almacenar los datos de la persona
            String idPersona = null;
            String nombrePersona = null;
            Boolean isEmployeeLeader = false;

            if (nombrePersona == null) {
                System.out.print("Nombre y apellido persona: ");
                nombrePersona = leerCadenaNoVaciaTexto().toUpperCase();
            } else {
                System.out.println("Por favor ingrese el nombre y apellido");
            }
            if (idPersona == null) {
                System.out.print("Número ID persona: ");
                idPersona = leerCodigoNumerico();

                // Validar si la persona ya está registrada por id
                boolean idRepetido = false;
                for (Person persona : personList) {
                    if (persona.getIdPersona().equals(idPersona)) {
                        System.out.println("La persona con este id ya está registrada.");
                        idPersona = null; // Reiniciar para volver a pedir el dato
                        idRepetido = true;
                        break;
                    }
                }
                if (idRepetido) {
                    continue; // Volver al inicio del ciclo si el id está repetido
                }
            }

            // Si se han ingresado todos los datos requeridos, registrar la persona
            if (nombrePersona != null && idPersona != null) {
                personList.add(new Person(idPersona, nombrePersona, isEmployeeLeader));
                //personList.add(new Person(id_persona, nombrePersona, isEmployeeLeader));
                System.out.println("Persona registrada exitosamente.");

                // Llamar a la función para guardar las personas en un archivo JSON
                guardarPersonasEnArchivo_json();
                guardarPersonasEnArchivo_xml();
                break; // Salir del bucle en caso de éxito
            }
        }
    }

            /*private static void registrarPersona() {
                System.out.println("=== Registrar Persona ===");

                // Declaración de variables para almacenar los datos de la persona
                String idPersona = null;
                String nombrePersona = null;
                Boolean isEmployeeLeader = false;

                // Ciclo para validar y registrar los datos de la persona
                while (true) {
                    if (nombrePersona == null) {
                        System.out.print("Nombre y apellido persona: ");
                        nombrePersona = leerCadenaNoVaciaTexto().toUpperCase();
                    }else {
                        System.out.println("Por favor ingrese el nombre y apellido");
                    }
                    if (idPersona == null) {
                        System.out.print("Número ID persona: ");
                        idPersona = leerCodigoNumerico();

                        // Validar si la persona ya está registrada por id
                        boolean idRepetido = false;
                        for (Person persona : personList) {
                            if (persona.getIdPersona().equals(idPersona)) {
                                System.out.println("La persona con este id ya está registrada.");
                                idPersona = null; // Reiniciar para volver a pedir el dato
                                idRepetido = true;
                                break;
                            }
                        }
                        if (idRepetido) {
                            continue; // Volver al inicio del ciclo si el id está repetido
                        }
                    }

                    // Si se han ingresado todos los datos requeridos, registrar la persona
                    if (nombrePersona != null && idPersona != null) {
                        personList.add(new Person(idPersona, nombrePersona, isEmployeeLeader));
                        //personList.add(new Person(id_persona, nombrePersona, isEmployeeLeader));
                        System.out.println("Persona registrada exitosamente.");

                        // Llamar a la función para guardar las personas en un archivo JSON
                        guardarPersonasEnArchivo_json();
                        guardarPersonasEnArchivo_xml();
                        break; // Salir del bucle en caso de éxito
                    }
                }
            }*/

            private static void ver_personas_registradas () {
                System.out.println("=== Personas Registradas ===");

                // Verificar si hay personas registradas
                if (personList.isEmpty()) {
                    System.out.println("No hay personas registradas.");
                } else {
                    int index = 0;
                    for (Person persona : personList) {
                        System.out.println("Índice " + index + ": " + persona);
                        index++;
                    }
                }
            }

    private static void ver_personas_registradasFor_Employees () {
        System.out.println("=== Personas Registradas ===");

        // Verificar si hay personas registradas
        if (personList.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            int index = 1;
            for (Person persona : personList) {
                System.out.println("Índice " + index + ": " + persona);
                index++;
            }
        }
    }

    private static void modificar_Persona() {
        System.out.println("=== Modificar Persona ===");

        // Mostrar la lista de personas registradas con índices
        for (int i = 0; i < personList.size(); i++) {
            Person persona = personList.get(i);
            System.out.println("Índice: " + i);
            System.out.println("ID: " + persona.getIdPersona());
            System.out.println("Nombre: " + persona.getNombreApellidoPersona());
            //System.out.println("Apellido: " + persona.getApellidos());
            System.out.println();
        }

        if (personList.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }

        System.out.print("Ingrese el índice de la persona que desea modificar: ");
        int indiceSeleccionado = leerIndicePersona();

        // Verificar que el índice esté dentro del rango válido
        if (indiceSeleccionado >= 0 && indiceSeleccionado < personList.size()) {
            Person personaAModificar = personList.get(indiceSeleccionado);

            System.out.println("Persona seleccionada:");
            System.out.println("ID: " + personaAModificar.getIdPersona());
            System.out.println("Nombre actual: " + personaAModificar.getNombreApellidoPersona());
            //System.out.println("Apellido actual: " + personaAModificar.getApellidos());

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = leerCadenaNoVaciaTexto();
            if (!nuevoNombre.isEmpty()) {
                personaAModificar.setNombreApellidoPersona(nuevoNombre.toUpperCase());
            }

            /*System.out.print("Nuevo apellido: ");
            String nuevoApellido = leerCadenaNoVaciaTexto();
            if (!nuevoApellido.isEmpty()) {
                personaAModificar.setApellidos(nuevoApellido.toUpperCase());
            }*/

            System.out.println("Persona modificada exitosamente.");

            // Llamar a la función para guardar las personas en un archivo JSON después de la modificación
            guardarPersonasEnArchivo_json();
            guardarPersonasEnArchivo_xml();
        } else {
            System.out.println("Índice no válido. No se realizó ninguna modificación.");
        }
    }

            private static void eliminar_Persona () {
                System.out.println("=== Eliminar Registro de personas ===");
                if (personList.isEmpty()) {
                    System.out.println("No hay personas registradas.");
                    return;
                }
                ver_personas_registradas();
                System.out.print("Ingrese el índice de la persona que desea eliminar: ");
                int indice = leerIndiceValido(personList.size());
                personList.remove(indice);
                System.out.println("persona eliminada exitosamente.");
                guardarPersonasEnArchivo_json();
                guardarPersonasEnArchivo_xml();
            }

            private static int leerIndicePersona () {

                while (true) {
                    try {
                        String input = reader.readLine();
                        int indice = Integer.parseInt(input.trim());
                        return indice;
                    } catch (NumberFormatException | IOException e) {
                        System.out.print("Ingrese un índice válido: ");
                    }
                }
            }

            private static void guardarPersonasEnArchivo_xml () {
                try {
// Configuración para crear un documento XML
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                    // Crear un documento XML
                    Document doc = dBuilder.newDocument();
                    Element rootElement = doc.createElement("Personas");
                    doc.appendChild(rootElement);

// Agregar cada persona como un elemento al documento
                    for (Person persona : personList) {
                        Element personaElement = doc.createElement("Persona");
                        rootElement.appendChild(personaElement);

// Crear elementos para ID, Nombre y Apellido de la persona
                        Element idElement = doc.createElement("ID");
                        idElement.appendChild(doc.createTextNode(persona.getIdPersona()));
                        personaElement.appendChild(idElement);

                        Element nombreElement = doc.createElement("Nombre");
                        nombreElement.appendChild(doc.createTextNode(persona.getNombreApellidoPersona()));
                        personaElement.appendChild(nombreElement);

                        /*Element apellidoElement = doc.createElement("Apellido");
                        apellidoElement.appendChild(doc.createTextNode(persona.getLastName()));
                        personaElement.appendChild(apellidoElement);*/
                    }

// Configurar la salida con formato (saltos de línea y sangría)
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

// Especificar la fuente de datos (el documento creado) y el destino (archivo XML)
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new FileWriter("personas.xml"));

// Realizar la transformación para guardar el documento en el archivo XML
                    transformer.transform(source, result);
                    System.out.println("Datos de persona guardados en el archivo XML con formato.");

                } catch (IOException | ParserConfigurationException | TransformerException | DOMException e) {
                    System.out.println("Error al guardar los datos de persona en el archivo XML.");
                }
            }

}


