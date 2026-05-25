package com.novasoftlaboratorys.model.mascotas;

import com.novasoftlaboratorys.interfaces.IMascota;

public class perroMarciano implements IMascota {

    private final String nombre;
    private final String raza;
    private final String clase;
    private final String bioma;
    private final String especialidad;

    public perroMarciano(String nombre) {
        this.nombre = nombre;
        this.raza = "Marciano";
        this.clase = "Perro";
        this.bioma = "Desierto Volcánico";
        this.especialidad = "Resistencia extrema";
    }

    public perroMarciano(Builder builder) {
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

        public perroMarciano build() {
            return new perroMarciano(this);
        }
    }
    
    @Override
    public void hacerSonido() {
        System.out.println("Woof woof");
    }

    @Override
    public void mover() {
        System.out.println("I'm running");
    }

    @Override
    public void mostrarDescripcion() {
        System.out.println("I'm a Martian dog");
    }

    @Override
    public void mostrarEstadisticas() {
        System.out.println("I'm a Martian dog with stats");
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
        return "PerroMarciano [NOMBRE=" + nombre + ", RAZA=" + raza + ", CLASE=" + clase + ", BIOMA=" + bioma
                + ", ESPECIALIDAD=" + especialidad + "]";
    }

}
