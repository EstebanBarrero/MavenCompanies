package org.example;

import java.io.BufferedReader;    // Importa la clase BufferedReader, que se utiliza para leer texto de un flujo de entrada
import java.io.FileWriter;        // Importa la clase FileWriter, que se utiliza para escribir caracteres en un archivo de texto
import java.io.IOException;       // Importa la clase IOException, que se utiliza para manejar excepciones relacionadas con entrada/salida
import java.io.InputStreamReader; // Importa la clase InputStreamReader, que se utiliza para leer caracteres desde un flujo de entrada
import java.nio.file.Files;  // Importa la clase Files de java.nio.file para trabajar con operaciones relacionadas con archivos y directorios.
import java.nio.file.Paths;  // Importa la clase Paths de java.nio.file para gestionar rutas de archivos y directorios de manera eficiente.
import java.util.ArrayList;       // Importa la clase ArrayList, que se utiliza para crear listas dinámicas
import java.util.List;            // Importa la interfaz List, que define el comportamiento de una lista (por ejemplo, ArrayList implementa esta interfaz)
import java.nio.file.Path;        // Importación necesaria para trabajar con rutas de archivos.
import com.fasterxml.jackson.core.type.TypeReference;// Importación necesaria para manejar tipos genéricos al deserializar JSON con Jackson.
import com.fasterxml.jackson.databind.ObjectMapper; // Importación necesaria para utilizar ObjectMapper de Jackson para la deserialización y serialización de JSON.
import java.io.File; // Importa la clase File, que se utiliza para trabajar con archivos en el sistema de archivos.

import javax.xml.parsers.ParserConfigurationException; // Importa la clase ParserConfigurationException, que se utiliza para manejar excepciones relacionadas con la configuración del analizador XML.
import javax.xml.transform.TransformerException; // Importa la clase TransformerException, que se utiliza para manejar excepciones relacionadas con la transformación de XML.

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
//Document Object Model (Modelo de Objetos de Documento)

public class MainXML {

    private static List<Person> personList = new ArrayList<>();
    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Campus> campusList = new ArrayList<>();
    private static List<Company> companyList = new ArrayList<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
            System.out.println("Guardando datos antes de salir...");
        }));

        //TODO REVISAR PORQUE NO SIRVE EL 5 O 0 PARA SALIR DEL MENU
        int opcion;
        do{
            showMenu();
            opcion = leerOpcion();
            String typeMenu;
            switch (opcion){
                case 1 -> {
                    do{
                        typeMenu = "registerPerson.xml";
                        cargarMenuDesdeXML(typeMenu);
                        int opcionSub = leerOpcion();
                        switch (opcionSub) {
                            case 1 -> ver_personas_registradas(); //Ver personas registradas
                            case 2 -> registrar_Persona();  //Registrar_Persona
                            case 3 -> modificar_Persona();//Modificar_Persona
                            case 4 -> eliminar_Persona(); //Eliminar_Persona
                            case 5 -> System.out.println("¡Hasta luego!");
                            default -> System.out.println("Opción no válida. Intente nuevamente.");
                        }
                    }while (opcion != 5);
                }
                case 3 -> {
                    do{
                        typeMenu = "registerCampus.xml";
                        cargarMenuDesdeXML(typeMenu);
                        opcion = leerOpcion();
                        switch (opcion) {
                            case 1 -> verSedesRegistradas(); //Ver personas registradas
                            case 2 -> registrarSede();  //Registrar_Persona
                            case 3 -> modificar_sede_principal_empresa();//Modificar_Persona
                            case 4 -> eliminarSede(); //Eliminar_Persona
                            case 5 -> System.out.println("¡Hasta luego!");
                            default -> System.out.println("Opción no válida. Intente nuevamente.");
                        }
                    }while (opcion != 5);
                }
            }

        }while (opcion != 5);
        guardarEmpresasEnArchivo_json();
        guardarSedesEnArchivo_json();
        guardarPersonasEnArchivo_json();



        int opcion2;

        do {
            //cargarMenuDesdeXML(typeMenu);
            opcion = leerOpcion();

            switch (opcion) {
                /*TODO ESTO ES DE PERSONAS
                case 1 -> ver_personas_registradas(); //Ver personas registradas
                case 2 -> registrar_Persona();  //Registrar_Persona
                case 3 -> modificar_Persona();//Modificar_Persona
                case 4 -> eliminar_Persona(); //Eliminar_Persona*/
                case 1 -> verSedesRegistradas(); //Ver personas registradas
                case 2 -> registrarSede();  //Registrar_Persona
                case 3 -> modificar_sede_principal_empresa();//Modificar_Persona
                case 4 -> eliminarSede(); //Eliminar_Persona

                case 5 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 5);

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
                System.out.println((i + 1) + ". " + option.getTextContent());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el menú desde el archivo XML.");
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
                System.out.println((i + 1) + ". " + option.getTextContent());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el menú desde el archivo XML.");
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
            System.out.println("Datos de Empresa guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de Empresa en el archivo JSON.");
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

                System.out.println("Datos de Empresa cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'empresas.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de empresas desde el archivo JSON.");
        }
    }

    private static void verEmpresasRegistradas() {
        if (companyList.isEmpty()) {                                        //Verifica si la lista está vacía
            System.out.println("No hay empresas registradas");
        } else {
            System.out.println("=== Empresas Registrados ===");
            int index = 0;
            for (Company empresa : companyList) {                     //bucle for-each que recorre la lista
                System.out.println("Índice " + index + ": " + empresa);
                index++;
            }
        }
    }

    private static void registrarEmpresa() {
        System.out.println("=== Registrar Empresa ===");

        // Declaración de variables para almacenar los datos del país
        String nombre_empresa = null;
        String codigo_empresa = null;
        // Ciclo para validar y registrar los datos
        while (true) {
            if (nombre_empresa == null) {
                System.out.print("nombre empresa: ");
                nombre_empresa = leerCadenaNoVaciaTexto().toUpperCase();

                // Validar si el pais ya está registrado por nombre
                for (Company empresa : companyList) {
                    if (empresa.getNombre_empresa().equalsIgnoreCase(nombre_empresa)) {
                        System.out.println("la empresa con este nombre ya está registrada.");
                        nombre_empresa = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si el codigo del pais aún no se ha ingresado
            else if (codigo_empresa == null) {
                System.out.print("código empresa: ");
                codigo_empresa = leerCodigoNumerico();

                // Validar si el pais ya está registrado por código
                for (Company empresa : companyList) {
                    if (empresa.getCodigo_empresa().equals(codigo_empresa)) {
                        System.out.println("La empresa con este código ya está registrada");
                        codigo_empresa = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            }
            // Si se han ingresado todos los datos requeridos, registrar el país
            if (nombre_empresa != null && codigo_empresa != null) {
                companyList.add(new Company(nombre_empresa, codigo_empresa));
                System.out.println("Empresa registrada exitosamente.");
                guardarEmpresasEnArchivo_json();
                break; // Salir del bucle en caso de éxito
            }
        }
    }

    private static void modificarEmpresa() {
        System.out.println("=== Modificar Registro de Empresa ===");

        // Verificar si hay empresas registrados
        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registrados.");
            return;
        }

        // Mostrar la lista de empresas registrados
        verEmpresasRegistradas();

        System.out.print("Ingrese el índice de la empresa que desea modificar: ");
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
                        System.out.println("La empresa con este nombre ya está registrada");
                        nombreEmpresaRegistrada = true;
                        break;
                    }
                }
                if (!nombreEmpresaRegistrada) {
                    nuevoNombreEmpresa = input;
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
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
                        System.out.println("La empresa con este código ya está registrada");
                        codigoEmpresaRegistrada = true;
                        break;
                    }
                }
                if (!codigoEmpresaRegistrada) {
                    nuevoCodigoEmpresa = input;
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            }
        }
        empresaSeleccionada.setNombre_empresa(nuevoNombreEmpresa);
        empresaSeleccionada.setCodigo_empresa(nuevoCodigoEmpresa);
        System.out.println("Empresa modificado exitosamente.");
        guardarEmpresasEnArchivo_json();

    }

    private static void eliminarEmpresa() {
        System.out.println("=== Eliminar Registro de Empresa ===");
        if (companyList.isEmpty()) {
            System.out.println("No hay empresas registrados.");
            return;
        }
        verEmpresasRegistradas();
        System.out.print("Ingrese el índice de la empresa que desea eliminar: ");
        int indice = leerIndiceValido(companyList.size());
        companyList.remove(indice);
        System.out.println("empresa eliminada exitosamente.");
        guardarEmpresasEnArchivo_json();
    }

    private static void verSedesRegistradas() {
        if (campusList.isEmpty()) {
            System.out.println("No hay sedes registradas.");
        } else {
            System.out.println("=== sedes Registradas ===");
            int index = 0;
            for (Campus sede : campusList) {                     //bucle for-each que recorre la lista
                System.out.println("Índice " + index + ": " + sede);
                index++;
            }
        }
    }

    private static void registrarSede() {
        System.out.println("=== Registrar sede ===");

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
                        System.out.println("la sede con este nombre ya está registrado.");
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
                        System.out.println("La sede con este código ya está registrada");
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
            System.out.println("No hay sedes registradas.");
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
    private static void Asociar_Sede_Empresa() {
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
                        if (d.getId_persona().equals(persona.getId_persona())) {
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
                            if (opcion >= 0 && opcion <= 4) {
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

            private static void registrar_Persona () {
                System.out.println("=== Registrar Persona ===");

                // Declaración de variables para almacenar los datos de la persona
                String nombre_persona = null;
                String apellido_persona = null;
                String id_persona = null;

                // Ciclo para validar y registrar los datos de la persona
                while (true) {
                    if (nombre_persona == null && apellido_persona == null) {
                        System.out.print("Nombre persona: ");
                        nombre_persona = leerCadenaNoVaciaTexto().toUpperCase();
                        System.out.print("Apellido persona: ");
                        apellido_persona = leerCadenaNoVaciaTexto().toUpperCase();
                    } else if (id_persona == null) {
                        System.out.print("Número identificación persona: ");
                        id_persona = leerCodigoNumerico();

                        // Validar si la persona ya está registrada por id
                        boolean idRepetido = false;
                        for (Person persona : personList) {
                            if (persona.getId_persona().equals(id_persona)) {
                                System.out.println("La persona con este id ya está registrada.");
                                id_persona = null; // Reiniciar para volver a pedir el dato
                                idRepetido = true;
                                break;
                            }
                        }

                        if (idRepetido) {
                            continue; // Volver al inicio del ciclo si el id está repetido
                        }
                    }

                    // Si se han ingresado todos los datos requeridos, registrar la persona
                    if (nombre_persona != null && apellido_persona != null && id_persona != null) {
                        personList.add(new Person(id_persona, nombre_persona, apellido_persona));
                        System.out.println("Persona registrada exitosamente.");

                        // Llamar a la función para guardar las personas en un archivo JSON
                        guardarPersonasEnArchivo_json();
                        guardarPersonasEnArchivo_xml();
                        break; // Salir del bucle en caso de éxito
                    }
                }
            }

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

    private static void modificar_Persona() {
        System.out.println("=== Modificar Persona ===");

        // Mostrar la lista de personas registradas con índices
        for (int i = 0; i < personList.size(); i++) {
            Person persona = personList.get(i);
            System.out.println("Índice: " + i);
            System.out.println("ID: " + persona.getId_persona());
            System.out.println("Nombre: " + persona.getNombre_apellido_persona());
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
            System.out.println("ID: " + personaAModificar.getId_persona());
            System.out.println("Nombre actual: " + personaAModificar.getNombre_apellido_persona());
            //System.out.println("Apellido actual: " + personaAModificar.getApellidos());

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = leerCadenaNoVaciaTexto();
            if (!nuevoNombre.isEmpty()) {
                personaAModificar.setNombre_apellido_persona(nuevoNombre.toUpperCase());
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
                        idElement.appendChild(doc.createTextNode(persona.getId_persona()));
                        personaElement.appendChild(idElement);

                        Element nombreElement = doc.createElement("Nombre");
                        nombreElement.appendChild(doc.createTextNode(persona.getNombre_apellido_persona()));
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


