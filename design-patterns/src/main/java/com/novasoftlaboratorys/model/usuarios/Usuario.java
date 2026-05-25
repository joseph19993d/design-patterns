package com.novasoftlaboratorys.model.usuarios;
import com.novasoftlaboratorys.interfaces.IUsuario;

public class Usuario implements IUsuario, Cloneable {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.edad = builder.edad;
        this.email = builder.email;
    }

    public static class Builder {
        private String nombre;
        private String apellido;
        private int edad;
        private String email;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setApellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder setEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    // crearemos builder


    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return email.equals(usuario.email);
    }

    //constructor copia para clonacion
    public Usuario(Usuario prototipo) {
        this.nombre = prototipo.nombre;
        this.apellido = prototipo.apellido;
        this.edad = prototipo.edad;
        this.email = prototipo.email;
    }

    @Override
    public Usuario clone() {
        return new Usuario(this);
    }

}