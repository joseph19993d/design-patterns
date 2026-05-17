# 📘 Documentación - Patrones de Diseño

**Proyecto:** `design-patterns`  
**Paquete base:** `com.novasoftlaboratorys`  
**Autor:** NovaSoft Laboratorys  
**Fecha:** Mayo 2026  

---

## 📑 Tabla de Contenidos

1. [Descripción General](#descripción-general)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Patrones Implementados](#patrones-implementados)
   - [Singleton](#1-patrón-singleton)
   - [Factory Method](#2-patrón-factory-method)
4. [Clases del Proyecto](#clases-del-proyecto)
5. [Ejemplo de Uso](#ejemplo-de-uso)
6. [Cómo Ejecutar](#cómo-ejecutar)

---

## Descripción General

Este proyecto es una implementación educativa de **patrones de diseño creacionales** en Java.  
Se utiliza un dominio temático de **planetas** para ilustrar de forma práctica los patrones **Singleton** y **Factory Method**, demostrando cómo controlar la creación de objetos de manera eficiente y desacoplada.

---

## Estructura del Proyecto

```
design-patterns/src/main/java/com/novasoftlaboratorys/
│
├── App.java                              # Punto de entrada principal
│
├── interfaces/
│   └── IPlaneta.java                     # Interfaz común para todos los planetas
│
└── model/
    ├── Humano.java                       # Modelo de entidad Humano
    │
    ├── singlenton/                       # Implementaciones Singleton
    │   ├── PlanetaTierra.java            # Singleton - Planeta Tierra
    │   └── PlanetaMarte.java             # Singleton - Planeta Marte
    │
    └── factory/
        └── FactoryPlaneta.java           # Factory Method para crear planetas
```

---

## Patrones Implementados

### 1. Patrón Singleton

#### 🎯 Propósito
Garantizar que una clase tenga **una única instancia** en toda la aplicación y proporcionar un **punto de acceso global** a ella.

#### 📖 Explicación
En el contexto de este proyecto, los planetas **Tierra** y **Marte** son entidades únicas: solo existe un planeta Tierra y un planeta Marte. Por eso tiene sentido aplicar el patrón Singleton, ya que no deberían existir múltiples instancias de estos objetos.

#### ✅ Características implementadas

| Característica | Detalle |
|---|---|
| **Constructor privado** | Impide la instanciación directa con `new` |
| **Instancia estática privada** | Variable `private static instance` almacena la única instancia |
| **Método `getInstance()`** | Punto de acceso público y estático para obtener la instancia |
| **Lazy Initialization** | La instancia se crea solo cuando se solicita por primera vez |

#### 🔍 Ejemplo en código

```java
// Constructor privado: no se puede hacer new PlanetaTierra(...)
private PlanetaTierra(Integer area, String forma, Integer alto, Integer ancho, Integer id) {
    this.area = area;
    this.forma = forma;
    this.alto = alto;
    this.ancho = ancho;
    this.id = id;
}

// Método estático para obtener la única instancia
public static PlanetaTierra getInstance() {
    if (instance == null) {
        instance = new PlanetaTierra(144800000, "esferico", 6779, 6779, new Random().nextInt(1000));
    }
    return instance;
}
```

#### ⚠️ Nota sobre Thread Safety
La implementación actual **no es thread-safe**. En un entorno multihilo, dos hilos podrían pasar la verificación `if (instance == null)` simultáneamente y crear dos instancias. Para hacerlo seguro se podría:
- Usar `synchronized` en el método `getInstance()`
- Aplicar **Double-Checked Locking**
- Usar un **enum Singleton**

---

### 2. Patrón Factory Method

#### 🎯 Propósito
Definir una interfaz para crear objetos, pero **delegar la decisión** de qué clase concreta instanciar a una fábrica. Esto desacopla el código cliente de las clases concretas.

#### 📖 Explicación
`FactoryPlaneta` actúa como una fábrica centralizada que sabe cómo crear cada tipo de planeta. El código cliente solo necesita pedir un planeta por su nombre (por ejemplo `"tierra"` o `"marte"`) sin conocer las clases concretas.

#### ✅ Características implementadas

| Característica | Detalle |
|---|---|
| **Registry pattern** | Usa un `Map<String, Supplier<IPlaneta>>` para registrar creadores |
| **Desacoplamiento** | El cliente no conoce las clases concretas `PlanetaTierra`, `PlanetaMarte` |
| **Extensibilidad** | Método `registrarPlaneta()` permite agregar nuevos planetas sin modificar la clase |
| **Open/Closed Principle** | Abierta para extensión, cerrada para modificación |

#### 🔍 Ejemplo en código

```java
// Registro estático de planetas disponibles
private static final Map<String, Supplier<IPlaneta>> PlanetaDelivery = new HashMap<>();
static {
    PlanetaDelivery.put("tierra", PlanetaTierra::getInstance);
    PlanetaDelivery.put("marte", PlanetaMarte::getInstance);
}

// Obtener un planeta por nombre
public static IPlaneta getPlaneta(String nombrePlaneta) {
    Supplier<IPlaneta> planetaSupplier = PlanetaDelivery.get(nombrePlaneta.toLowerCase());
    if (planetaSupplier == null) {
        throw new IllegalArgumentException("Planeta desconocido: " + nombrePlaneta);
    }
    return planetaSupplier.get();
}

// Registrar un nuevo planeta dinámicamente
public static void registrarPlaneta(String nombrePlaneta, Supplier<IPlaneta> planetaSupplier) {
    PlanetaDelivery.put(nombrePlaneta.toLowerCase(), planetaSupplier);
}
```

---

## Clases del Proyecto

### `IPlaneta` (Interfaz)
**Paquete:** `com.novasoftlaboratorys.interfaces`

Define el contrato que todo planeta debe cumplir:

| Método | Descripción |
|---|---|
| `void entrar()` | Acción de entrar al planeta |
| `void explorar()` | Acción de explorar el planeta |
| `void salir()` | Acción de salir del planeta |

---

### `PlanetaTierra` (Singleton)
**Paquete:** `com.novasoftlaboratorys.model.singlenton`  
**Implementa:** `IPlaneta`

| Atributo | Tipo | Valor por defecto |
|---|---|---|
| `area` | `Integer` | `144800000` |
| `forma` | `String` | `"esferico"` |
| `alto` | `Integer` | `6779` |
| `ancho` | `Integer` | `6779` |
| `_nombre` | `String` | `"tierra"` |
| `id` | `Integer` | Aleatorio (0-999) |

---

### `PlanetaMarte` (Singleton)
**Paquete:** `com.novasoftlaboratorys.model.singlenton`  
**Implementa:** `IPlaneta`

| Atributo | Tipo | Valor por defecto |
|---|---|---|
| `area` | `Integer` | `144800000` |
| `forma` | `String` | `"esferico"` |
| `alto` | `Integer` | `6779` |
| `ancho` | `Integer` | `6779` |
| `_nombre` | `String` | `"marte"` |
| `id` | `Integer` | Aleatorio (0-999) |

---

### `Humano`
**Paquete:** `com.novasoftlaboratorys.model`

Modelo de entidad que representa un humano con referencia a un planeta.

| Atributo | Tipo |
|---|---|
| `nombre` | `String` |
| `sexo` | `String` |
| `edad` | `Integer` |
| `planetaTierra` | `PlanetaTierra` (static) |

---

### `FactoryPlaneta`
**Paquete:** `com.novasoftlaboratorys.model.factory`

Fábrica de planetas que utiliza un registro interno.

| Método | Descripción |
|---|---|
| `getPlaneta(String)` | Retorna una instancia de `IPlaneta` según el nombre |
| `registrarPlaneta(String, Supplier)` | Registra un nuevo tipo de planeta en la fábrica |

---

## Ejemplo de Uso

```java
public static void main(String[] args) {
    // === SINGLETON ===
    // Ambas variables apuntan a la MISMA instancia
    PlanetaTierra tierra1 = PlanetaTierra.getInstance();
    PlanetaTierra tierra2 = PlanetaTierra.getInstance();
    
    System.out.println(tierra1.hashCode() == tierra2.hashCode()); // true ✅
    System.out.println(tierra1.getNombre()); // "tierra"

    // === FACTORY ===
    // Crear planetas sin conocer las clases concretas
    IPlaneta planeta = FactoryPlaneta.getPlaneta("marte");
    planeta.entrar();     // "Entrando a Marte"
    planeta.explorar();   // "Explorando Marte"
    planeta.salir();      // "Saliendo de Marte"
}
```

---

## Cómo Ejecutar

```bash
# Compilar el proyecto
mvn compile

# Ejecutar la clase principal
mvn exec:java -Dexec.mainClass="com.novasoftlaboratorys.App"

# O ejecutar los tests
mvn test
```

---

> 📌 **Referencia:** Para ver el diagrama de clases UML del proyecto, consultar el archivo [`DIAGRAMA.md`](./DIAGRAMA.md).
