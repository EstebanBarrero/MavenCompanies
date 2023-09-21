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

    private static List<Persona> personas = new ArrayList<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        // llamado a los metodos para persistir los archivos txt y XML
        cargarPersonasDesdeArchivo_json();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarPersonasEnArchivo_json();
            guardarPersonasEnArchivo_xml();
            System.out.println("Guardando datos antes de salir...");
        }));

        int opcion;

        do {
            cargarMenuDesdeXML();
            opcion = leerOpcion();

            switch (opcion) {
                case 1 ->
                        ver_personas_registradas(); //Ver personas registradas
                case 2 ->
                        registrar_Persona();  //Registrar_Persona
                case 3 ->
                        modificar_Persona();//Modificar_Persona
                case 4 ->
                        eliminar_Persona(); //Eliminar_Persona

                case 0 ->
                        System.out.println("¡Hasta luego!");
                default ->
                        System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 0);

// Llamado a los métodos para guardar los datos antes de salir
        guardarPersonasEnArchivo_json();

    }

    ////////////////////
    /**
     * Carga y muestra el menú de opciones desde un archivo XML. El método lee
     * el archivo "menu.xml", analiza su contenido y muestra las opciones en la
     * consola. Si se produce un error durante el proceso de lectura o análisis,
     * muestra un mensaje de error.
     */
    private static void cargarMenuDesdeXML() {
        try {
// Crear un objeto File que representa el archivo "menu.xml"
            File menuFile = new File("registerPerson.xml");
// Crear una instancia de DocumentBuilderFactory para configurar el analizador XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
// Crear un objeto DocumentBuilder para analizar el contenido XML
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
// Analizar el contenido del archivo XML y crear un documento DOM: Document Object Model (Modelo de Objetos de Documento)
            Document doc = dBuilder.parse(menuFile);
// Obtener la lista de nodos "option" del documento XML
            NodeList options = doc.getElementsByTagName("option");
// Mostrar el menú en la consola
            System.out.println("=== MENÚ - REGISTRO DE PERSONAS EN/ES ===");
            for (int i = 0; i < options.getLength(); i++) {
                Element option = (Element) options.item(i);
                System.out.println((i + 1) + ". " + option.getTextContent());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el menú desde el archivo XML.");
        }
    }

    ///////////////////
    private static void guardarPersonasEnArchivo_json() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("persona.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), personas);
            System.out.println("Datos de persona guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de persona en el archivo JSON.");
        }
    }

    private static void cargarPersonasDesdeArchivo_json() {
        personas.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("persona.json");

            if (Files.exists(filePath)) {
                List<Persona> personasCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Persona>>() {
                });
                // Iteramos sobre las personas cargadas y añadimos cada uno a la lista de personas actual.
                for (Persona persona : personasCargadas) {
                    // Verificamos si la persona ya existe en la lista actual.
                    boolean existe = false;
                    for (Persona d : personas) {
                        if (d.getId_persona().equals(persona.getId_persona())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        personas.add(persona);
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
    private static int leerOpcion() {
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

    private static int leerIndiceValido(int maximo) {
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

    private static void registrar_Persona() {
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
                for (Persona persona : personas) {
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
                personas.add(new Persona(id_persona, nombre_persona, apellido_persona));
                System.out.println("Persona registrada exitosamente.");

                // Llamar a la función para guardar las personas en un archivo JSON
                guardarPersonasEnArchivo_json();
                guardarPersonasEnArchivo_xml();
                break; // Salir del bucle en caso de éxito
            }
        }
    }

    private static void ver_personas_registradas() {
        System.out.println("=== Personas Registradas ===");

        // Verificar si hay personas registradas
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            int index = 0;
            for (Persona persona : personas) {
                System.out.println("Índice " + index + ": " + persona);
                index++;
            }
        }
    }

    private static void modificar_Persona() {
        System.out.println("=== Modificar Persona ===");

        // Mostrar la lista de personas registradas con índices
        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);
            System.out.println("Índice: " + i);
            System.out.println("ID: " + persona.getId_persona());
            System.out.println("Nombre: " + persona.getNombres());
            System.out.println("Apellido: " + persona.getApellidos());
            System.out.println();
        }

        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }

        System.out.print("Ingrese el índice de la persona que desea modificar: ");
        int indiceSeleccionado = leerIndicePersona();

        // Verificar que el índice esté dentro del rango válido
        if (indiceSeleccionado >= 0 && indiceSeleccionado < personas.size()) {
            Persona personaAModificar = personas.get(indiceSeleccionado);

            System.out.println("Persona seleccionada:");
            System.out.println("ID: " + personaAModificar.getId_persona());
            System.out.println("Nombre actual: " + personaAModificar.getNombres());
            System.out.println("Apellido actual: " + personaAModificar.getApellidos());

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = leerCadenaNoVaciaTexto();
            if (!nuevoNombre.isEmpty()) {
                personaAModificar.setNombres(nuevoNombre.toUpperCase());
            }

            System.out.print("Nuevo apellido: ");
            String nuevoApellido = leerCadenaNoVaciaTexto();
            if (!nuevoApellido.isEmpty()) {
                personaAModificar.setApellidos(nuevoApellido.toUpperCase());
            }

            System.out.println("Persona modificada exitosamente.");

            // Llamar a la función para guardar las personas en un archivo JSON después de la modificación
            guardarPersonasEnArchivo_json();
            guardarPersonasEnArchivo_xml();
        } else {
            System.out.println("Índice no válido. No se realizó ninguna modificación.");
        }
    }

    private static void eliminar_Persona() {
        System.out.println("=== Eliminar Registro de personas ===");
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        ver_personas_registradas();
        System.out.print("Ingrese el índice de la persona que desea eliminar: ");
        int indice = leerIndiceValido(personas.size());
        personas.remove(indice);
        System.out.println("persona eliminada exitosamente.");
        guardarPersonasEnArchivo_json();
        guardarPersonasEnArchivo_xml();
    }

    private static int leerIndicePersona() {

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

    private static void guardarPersonasEnArchivo_xml() {
        try {
// Configuración para crear un documento XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Crear un documento XML
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Personas");
            doc.appendChild(rootElement);

// Agregar cada persona como un elemento al documento
            for (Persona persona : personas) {
                Element personaElement = doc.createElement("Persona");
                rootElement.appendChild(personaElement);

// Crear elementos para ID, Nombre y Apellido de la persona
                Element idElement = doc.createElement("ID");
                idElement.appendChild(doc.createTextNode(persona.getId_persona()));
                personaElement.appendChild(idElement);

                Element nombreElement = doc.createElement("Nombre");
                nombreElement.appendChild(doc.createTextNode(persona.getNombres()));
                personaElement.appendChild(nombreElement);

                Element apellidoElement = doc.createElement("Apellido");
                apellidoElement.appendChild(doc.createTextNode(persona.getApellidos()));
                personaElement.appendChild(apellidoElement);
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
