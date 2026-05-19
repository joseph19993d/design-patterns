package com.novasoftlaboratorys.model.planetas;

import com.novasoftlaboratorys.interfaces.IPlaneta;

public class PlanetaTierra implements IPlaneta {
    private Integer area;
    private String forma;
    private Integer alto;
    private Integer ancho;
    private static final String _nombre = "tierra";
    private Integer id;

    private static PlanetaTierra instance;

    private PlanetaTierra(Integer area, String forma, Integer alto, Integer ancho, Integer id) {
        this.area = area;
        this.forma = forma;
        this.alto = alto;
        this.ancho = ancho;
        this.id = id;
    }

    public static PlanetaTierra getInstance() {
        if (instance == null) {
            instance = new PlanetaTierra(144800000, "esferico", 6779, 6779, new java.util.Random().nextInt(1000));
        }
        return instance;
    }

    public Integer getId() {
        return id;
    }

    public Integer getArea() {
        return area;
    }

    public String getForma() {
        return forma;
    }

    public Integer getAlto() {
        return alto;
    }

    public Integer getAncho() {
        return ancho;
    }

    public String getNombre() {
        return _nombre;
    }

    @Override
    public String toString() {
        return "PlanetaTierra [NOMBRE=" + _nombre + ", area=" + area + ", forma=" + forma + ", alto=" + alto + ", ancho="
                + ancho + "]";
    }

    @Override
    public void entrar() {
        System.out.println("Entrando a la tierra");
        System.out.println();
    }

    @Override
    public void explorar() {
        System.out.println("Explorando la tierra");
        System.out.println(this.toString()); 
    }

    @Override
    public void salir() {
        System.out.println("Saliendo de la tierra");
    }    

}
