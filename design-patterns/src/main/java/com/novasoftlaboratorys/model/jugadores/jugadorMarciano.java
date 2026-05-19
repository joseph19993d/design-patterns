package com.novasoftlaboratorys.model.jugadores;

import com.novasoftlaboratorys.interfaces.IJugador;

import java.util.UUID;

public class jugadorMarciano implements IJugador {

    private final UUID id;
    private final String nombre;
    private final String raza;
    private final String clase;
    private final String bioma;
    private final String especialidad;

    public jugadorMarciano(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        // Características propias de Marte
        this.raza = "Marciano";
        this.clase = "Explorador Rojo";
        this.bioma = "Desierto Volcánico";
        this.especialidad = "Resistencia extrema";
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getRaza() {
        return raza;
    }

    @Override
    public String getClase() {
        return clase;
    }

    @Override
    public void mostrarDescripcion() {
        System.out.println("--- Descripción del Jugador Marciano ---");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Raza: " + raza);
        System.out.println("Clase: " + clase);
        System.out.println("Bioma de Origen: " + bioma);
        System.out.println("Especialidad: " + especialidad);
    }

    @Override
    public void mostrarEstadisticas() {
        System.out.println("\n--- Estadísticas de " + nombre + " ---");
        System.out.println("Fuerza: " + calcularFuerza());
        System.out.println("Agilidad: " + calcularAgilidad());
        System.out.println("Inteligencia: " + calcularInteligencia());
        System.out.println("Resistencia: " + calcularResistencia());
    }

    private int calcularFuerza() {
        return switch (especialidad) {
            case "Guerrero" -> 90;
            case "Arquero" -> 70;
            case "Mago" -> 40;
            case "Asesino" -> 60;
            default -> 50;
        };
    }

    private int calcularAgilidad() {
        return switch (especialidad) {
            case "Guerrero" -> 60;
            case "Arquero" -> 90;
            case "Mago" -> 70;
            case "Asesino" -> 95;
            default -> 60;
        };
    }

    private int calcularInteligencia() {
        return switch (especialidad) {
            case "Guerrero" -> 60;
            case "Arquero" -> 60;
            case "Mago" -> 95;
            case "Asesino" -> 70;
            default -> 60;
        };
    }

    private int calcularResistencia() {
        return switch (especialidad) {
            case "Guerrero" -> 90;
            case "Arquero" -> 70;
            case "Mago" -> 70;
            case "Asesino" -> 70;
            default -> 70;
        };
    }

    @Override
    public String toString() {
        return "JugadorMarciano [" + nombre + ", Raza: " + raza + ", Clase: " + clase + ", Bioma: " + bioma
                + ", Especialidad: " + especialidad + "]";
    }

    
}