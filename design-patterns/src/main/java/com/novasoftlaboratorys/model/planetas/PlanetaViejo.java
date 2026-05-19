package com.novasoftlaboratorys.model.planetas;

// esta es una clase "supuestamente vieja" que no implementa la interfaz IPlaneta, sera un caso de uso para ADAPTER PATTERN
public class PlanetaViejo {
    // Attributes ENCAPSULATED
    private Integer area;
    private String forma;
    private Integer alto;
    private Integer ancho;
    private static final String _nombre = "planeta viejo";

    private static PlanetaViejo instance;

    private PlanetaViejo(Integer area, String forma, Integer alto, Integer ancho) {
        this.area = area;
        this.forma = forma;
        this.alto = alto;
        this.ancho = ancho;
    }

    public static PlanetaViejo getInstance() {
        if (instance == null) {
            instance = new PlanetaViejo(149598000, "deforme", 10821, 12742);
        }
        return instance;
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
        return "PlanetaViejo [NOMBRE=" + _nombre + ", area=" + area + ", forma=" + forma + ", alto=" + alto + ", ancho="
                + ancho + "]";
    }

    public void entrarViejoDiferenteAlDeLaInterfaz() {
        System.out.println("Entrando al planeta viejo de forma diferente");
    }
    public void explorarViejoDiferenteAlDeLaInterfaz() {
        System.out.println("Explorando el planeta viejo de forma diferente");
    }
    public void salirViejoDiferenteAlDeLaInterfaz() { 
        System.out.println("Saliendo del planeta viejo de forma diferente");
    }
}
