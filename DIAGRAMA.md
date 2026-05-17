# 📐 Diagrama de Clases UML - Patrones de Diseño

**Proyecto:** `design-patterns`  
**Paquete base:** `com.novasoftlaboratorys`  

---

## Diagrama General

```mermaid
classDiagram
    direction TB

    class IPlaneta {
        <<interface>>
        +entrar() void
        +explorar() void
        +salir() void
    }

    class PlanetaTierra {
        -Integer area
        -String forma
        -Integer alto
        -Integer ancho
        -String _nombre
        -Integer id
        -PlanetaTierra instance$
        -PlanetaTierra(Integer, String, Integer, Integer, Integer)
        +getInstance()$ PlanetaTierra
        +getId() Integer
        +getArea() Integer
        +getForma() String
        +getAlto() Integer
        +getAncho() Integer
        +getNombre() String
        +toString() String
        +entrar() void
        +explorar() void
        +salir() void
    }

    class PlanetaMarte {
        -Integer area
        -String forma
        -Integer alto
        -Integer ancho
        -String _nombre
        -Integer id
        -PlanetaMarte instance$
        -PlanetaMarte(Integer, String, Integer, Integer, Integer)
        +getInstance()$ PlanetaMarte
        +getArea() Integer
        +getForma() String
        +getAlto() Integer
        +getAncho() Integer
        +getNombre() String
        +toString() String
        +entrar() void
        +explorar() void
        +salir() void
    }

    class FactoryPlaneta {
        -Map~String, Supplier~IPlaneta~~ PlanetaDelivery$
        +registrarPlaneta(String, Supplier~IPlaneta~)$ void
        +getPlaneta(String)$ IPlaneta
    }

    class Humano {
        -String nombre
        -String sexo
        -Integer edad
        -PlanetaTierra planetaTierra$
        -Humano(String, String, Integer)
    }

    class App {
        +main(String[])$ void
    }

    IPlaneta <|.. PlanetaTierra : implements
    IPlaneta <|.. PlanetaMarte : implements
    FactoryPlaneta ..> IPlaneta : creates
    FactoryPlaneta ..> PlanetaTierra : uses
    FactoryPlaneta ..> PlanetaMarte : uses
    Humano --> PlanetaTierra : has reference
    App ..> PlanetaTierra : uses
```

---

## Diagrama del Patrón Singleton

```mermaid
classDiagram
    direction LR

    class PlanetaTierra {
        -PlanetaTierra instance$
        -PlanetaTierra(...)
        +getInstance()$ PlanetaTierra
    }

    class Cliente1["App (Cliente 1)"]
    class Cliente2["Humano (Cliente 2)"]

    note for PlanetaTierra "Solo UNA instancia\nen toda la aplicación"

    Cliente1 ..> PlanetaTierra : getInstance()
    Cliente2 ..> PlanetaTierra : getInstance()
```

---

## Diagrama del Patrón Factory Method

```mermaid
classDiagram
    direction TB

    class IPlaneta {
        <<interface>>
        +entrar() void
        +explorar() void
        +salir() void
    }

    class FactoryPlaneta {
        -Map PlanetaDelivery$
        +getPlaneta(String)$ IPlaneta
        +registrarPlaneta(String, Supplier)$ void
    }

    class PlanetaTierra {
        +getInstance()$ PlanetaTierra
    }

    class PlanetaMarte {
        +getInstance()$ PlanetaMarte
    }

    class NuevoPlaneta["🆕 Nuevo Planeta"] {
        <<futuro>>
    }

    IPlaneta <|.. PlanetaTierra : implements
    IPlaneta <|.. PlanetaMarte : implements
    IPlaneta <|.. NuevoPlaneta : implements
    FactoryPlaneta ..> IPlaneta : creates
    FactoryPlaneta ..> PlanetaTierra : "tierra"
    FactoryPlaneta ..> PlanetaMarte : "marte"
    FactoryPlaneta ..> NuevoPlaneta : registrarPlaneta()
```

---

## Diagrama de Secuencia - Singleton

```mermaid
sequenceDiagram
    participant App
    participant PlanetaTierra

    App->>PlanetaTierra: getInstance()
    alt instance == null
        PlanetaTierra->>PlanetaTierra: new PlanetaTierra(...)
        PlanetaTierra-->>PlanetaTierra: instance = nueva instancia
    end
    PlanetaTierra-->>App: retorna instance

    App->>PlanetaTierra: getInstance()
    Note over PlanetaTierra: instance != null, no crea otra
    PlanetaTierra-->>App: retorna la MISMA instance
    
    Note over App: hashCode tierra1 == hashCode tierra2 ✅
```

---

## Diagrama de Secuencia - Factory Method

```mermaid
sequenceDiagram
    participant Cliente
    participant FactoryPlaneta
    participant Map as "PlanetaDelivery (Map)"
    participant PlanetaMarte

    Cliente->>FactoryPlaneta: getPlaneta("marte")
    FactoryPlaneta->>Map: get("marte")
    Map-->>FactoryPlaneta: Supplier<IPlaneta>
    FactoryPlaneta->>PlanetaMarte: supplier.get() → getInstance()
    PlanetaMarte-->>FactoryPlaneta: instancia de PlanetaMarte
    FactoryPlaneta-->>Cliente: IPlaneta (PlanetaMarte)
    
    Cliente->>Cliente: planeta.entrar()
    Cliente->>Cliente: planeta.explorar()
    Cliente->>Cliente: planeta.salir()
```

---

## Diagrama de Paquetes

```mermaid
graph TB
    subgraph "com.novasoftlaboratorys"
        A[App.java]
        
        subgraph "interfaces"
            B[IPlaneta]
        end
        
        subgraph "model"
            C[Humano]
            
            subgraph "singlenton"
                D[PlanetaTierra]
                E[PlanetaMarte]
            end
            
            subgraph "factory"
                F[FactoryPlaneta]
            end
        end
    end

    A --> D
    B -.-> D
    B -.-> E
    C --> D
    F --> B
    F --> D
    F --> E

    style A fill:#4CAF50,color:#fff
    style B fill:#2196F3,color:#fff
    style C fill:#FF9800,color:#fff
    style D fill:#9C27B0,color:#fff
    style E fill:#9C27B0,color:#fff
    style F fill:#F44336,color:#fff
```

---

## Leyenda

| Color | Significado |
|---|---|
| 🟢 Verde | Punto de entrada (App) |
| 🔵 Azul | Interfaz (IPlaneta) |
| 🟠 Naranja | Modelo de entidad (Humano) |
| 🟣 Púrpura | Singleton (Planetas) |
| 🔴 Rojo | Factory (FactoryPlaneta) |

---

## 🖥️ ¿Cómo ver los diagramas?

Los diagramas de este archivo están escritos en **[Mermaid](https://mermaid.js.org/)**, un lenguaje de diagramación basado en texto. Para visualizarlos como gráficos necesitas una de las siguientes opciones:

---

### Opción 1: GitHub (automático ✅)

Si subes este archivo a un repositorio de **GitHub**, los diagramas se renderizan **automáticamente** al ver el archivo `.md` en el navegador. No necesitas instalar nada.

> Simplemente haz push del archivo y ábrelo desde GitHub.

---

### Opción 2: VS Code (extensión)

1. Abre VS Code
2. Ve a **Extensions** (`Ctrl + Shift + X`)
3. Busca e instala: **`Markdown Preview Mermaid Support`** (de Matt Bierner)
4. Abre este archivo `DIAGRAMA.md`
5. Presiona `Ctrl + Shift + V` para abrir la **vista previa de Markdown**
6. Los diagramas se renderizarán automáticamente dentro de la previsualización

> 💡 **Extensión alternativa:** También puedes instalar **`Mermaid Markdown Syntax Highlighting`** para tener resaltado de sintaxis en los bloques de código Mermaid.

---

### Opción 3: IntelliJ IDEA

1. Abre IntelliJ IDEA
2. Ve a **File → Settings → Plugins**
3. Busca e instala: **`Mermaid`**
4. Reinicia el IDE
5. Abre este archivo y usa la vista previa de Markdown (`Ctrl + Shift + F10` o el ícono de preview)

---

### Opción 4: Mermaid Live Editor (navegador web)

1. Abre [https://mermaid.live](https://mermaid.live) en tu navegador
2. Copia el contenido de cualquier bloque ````mermaid`  de este archivo (solo el código, sin las comillas triples)
3. Pégalo en el editor de la izquierda
4. El diagrama se renderiza en tiempo real a la derecha
5. Puedes exportar como **PNG**, **SVG**, o compartir un link

> 🎯 **Esta es la forma más rápida** si solo quieres ver un diagrama sin instalar nada.

---

### Opción 5: Línea de comandos (generar imágenes)

Si deseas generar archivos de imagen (PNG/SVG) de los diagramas:

```bash
# 1. Instalar Mermaid CLI
npm install -g @mermaid-js/mermaid-cli

# 2. Crear un archivo con el diagrama (ejemplo: diagrama.mmd)
# Copia el contenido de un bloque mermaid al archivo .mmd

# 3. Generar la imagen
mmdc -i diagrama.mmd -o diagrama.png
mmdc -i diagrama.mmd -o diagrama.svg
```

---

### Resumen rápido

| Método | ¿Instalar algo? | Dificultad |
|---|---|---|
| **GitHub** | No | ⭐ Muy fácil |
| **Mermaid Live Editor** | No | ⭐ Muy fácil |
| **VS Code + extensión** | Sí (extensión) | ⭐⭐ Fácil |
| **IntelliJ + plugin** | Sí (plugin) | ⭐⭐ Fácil |
| **Mermaid CLI** | Sí (npm) | ⭐⭐⭐ Intermedio |

---

> 📌 **Referencia:** Para ver la documentación completa del proyecto, consultar [`DOCUMENTACION.md`](./DOCUMENTACION.md).
