package com.novasoftlaboratorys.model.jugadores;

import java.util.UUID;

import com.novasoftlaboratorys.interfaces.IJugador;

public class jugadorTerricola implements IJugador {

    private final UUID id;
    private final String nombre;
    private final String raza;
    private final String clase;
    private final String bioma;
    private final String especialidad;

    private jugadorTerricola(Builder builder) {
        this.id = UUID.randomUUID();
        this.nombre = builder.nombre;
        this.raza = builder.raza;
        this.clase = builder.clase;
        this.bioma = builder.bioma;
        this.especialidad = builder.especialidad;
    }
    //Builder
    public static class Builder {
        private String nombre;
        private String raza;
        private String clase;
        private String bioma;
        private String especialidad;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setRaza(String raza) {
            this.raza = raza;
            return this;
        }

        public Builder setClase(String clase) {
            this.clase = clase;
            return this;
        }

        public Builder setBioma(String bioma) {
            this.bioma = bioma;
            return this;
        }

        public Builder setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public jugadorTerricola build() {
            return new jugadorTerricola(this);
        }
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
        System.out.println("--- Descripción del Jugador Terricola ---");
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
        return "JugadorTerricola [" + nombre + ", Raza: " + raza + ", Clase: " + clase + ", Bioma: " + bioma
                + ", Especialidad: " + especialidad + "]";
    }

}
