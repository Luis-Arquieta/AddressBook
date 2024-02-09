/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.addressbook;
import java.io.*;
import java.util.*;
/**
 *
 * @author Luis Arquieta
 */
public class AddressBook {
    private Map<String, String> contacts;

    public AddressBook() {
        this.contacts = new HashMap<>();
    }

    // Método para cargar los contactos desde un archivo de texto
    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                contacts.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    // Método para guardar los contactos en un archivo de texto
    public void save(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + " : " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    // Método para mostrar los contactos
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Método para agregar un nuevo contacto
    public void create(String name, String phoneNumber) {
        contacts.put(phoneNumber, name);
        System.out.println("Contacto agregado correctamente.");
    }

    // Método para eliminar un contacto
    public void delete(String phoneNumber) {
        if (contacts.containsKey(phoneNumber)) {
            contacts.remove(phoneNumber);
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("El número telefónico no existe en la agenda.");
        }
    }

    // Método para ejecutar un menú interactivo
    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Agregar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar cambios");
            System.out.println("5. Cargar desde archivo");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea pendiente

            switch (choice) {
                case 1:
                    list();
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del contacto: ");
                    String name = scanner.nextLine();
                    System.out.print("Ingrese el número telefónico: ");
                    String phoneNumber = scanner.nextLine();
                    create(name, phoneNumber);
                    break;
                case 3:
                    System.out.print("Ingrese el número telefónico del contacto a eliminar: ");
                    String numberToDelete = scanner.nextLine();
                    delete(numberToDelete);
                    break;
                case 4:
                    save("contacts.txt");
                    System.out.println("Cambios guardados correctamente.");
                    break;
                case 5:
                    load("contacts.txt");
                    System.out.println("Contactos cargados correctamente.");
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        } while (choice != 0);
        scanner.close();
    }

    
    
    
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.runMenu();
        
    }
}
