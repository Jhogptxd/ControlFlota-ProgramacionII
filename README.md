# Control de Flota – Cooperativa Interprovincial

Proyecto de **Programación II (UDLA)**. Sistema para controlar los buses, conductores, mantenimientos y viajes de una cooperativa de transporte interprovincial de pasajeros.

**Estado:** Progreso 2 – documentación hasta el punto 7 y proyecto en modo consola.

## Integrantes

| Integrante | Rol | Módulo |
|---|---|---|
| Josua Erazo | Líder | Conductores |
| Dennys Chanchicocha | Análisis y diseño | Mantenimientos |
| Galo Zambrano | Desarrollo | Viajes |
| Juan Jácome | Pruebas | Buses |

## Estructura

```
src
├── modelo      → entidades: Persona, Conductor, Bus, Mantenimiento, Viaje
├── negocio     → reglas de cada módulo: GestorConductores, GestorBuses, GestorMantenimientos, GestorViajes
└── interfaz    → menús por consola: Principal, Lectura, MenuConductores, MenuBuses, MenuMantenimientos, MenuViajes
```

Los diagramas UML están en la carpeta [`docs`](docs).

## Cómo ejecutarlo

1. Abrir la carpeta del proyecto en IntelliJ IDEA (**File → Open**).
2. Si `src` no aparece en azul: clic derecho → **Mark Directory as → Sources Root**.
3. Elegir un JDK 17 o superior en **File → Project Structure → Project → SDK**.
4. Ejecutar `src/interfaz/Principal.java`.

Las fechas se escriben `dd/mm/aaaa` y las fechas con hora `dd/mm/aaaa hh:mm`.
