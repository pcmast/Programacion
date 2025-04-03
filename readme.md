# Proyecto de Voluntariado y Actividades Sociales

## Descripción
Este proyecto es una aplicación de consola desarrollada en Java para la gestión de iniciativas de voluntariado y actividades sociales. Permite la creación y administración de iniciativas, así como la asignación de voluntarios y la organización de actividades dentro de cada iniciativa.

## Características principales
- **Registro e inicio de sesión** con usuario y contraseña encriptada.
- **Gestión de iniciativas** creadas por usuarios.
- **Registro y organización de actividades** asociadas a iniciativas.
- **Roles de usuario:**
  - Creador de iniciativa (CRUD completo de actividades y asignación de voluntarios).
  - Voluntario (ver actividades asignadas y actualizar su estado).
- **Estados de actividades:** NO INICIADA, EN PROGRESO, COMPLETADA.
- **Persistencia de datos** para conservar la información entre ejecuciones.
- **Uso de Wrapper para serialización**.

## Tecnologías utilizadas
- Lenguaje de programación: **Java**
- Arquitectura: **Modelo - Vista - Controlador (MVC)**
- Acceso a datos: **XML**
- Seguridad: **Hashing de contraseñas**
- Manejo de fechas: **LocalDate**
- Expresiones regulares para validación de datos
- Manejo de excepciones

## Requisitos para ejecutar el proyecto
- **Java 11** o superior instalado.
- Un IDE compatible con Java (IntelliJ IDEA, Eclipse, NetBeans, etc.).

## Instalación y ejecución
1. Clona el repositorio:
   ```bash
   git clone URL
   ```
2. Abre el proyecto en tu IDE.
3. Compila y ejecuta la clase principal del programa.

## Uso
1. **Iniciar sesión o registrarse** con un usuario y contraseña.
2. **Crear una iniciativa** e invitar a voluntarios.
3. **Definir actividades** dentro de una iniciativa.
4. **Asignar voluntarios** a las actividades.
5. **Actualizar el estado** de las actividades.

## Estructura del Proyecto
- `src/` - Carpeta principal del código fuente.
- `modelo/` - Clases que representan la lógica del negocio.
- `vista/` - Clases encargadas de la interacción con el usuario.
- `controlador/` - Clases que manejan la comunicación entre la vista y el modelo.
- `datos/` - Archivos XML para la persistencia de datos.

## Autores
- **Pedro Castaño**
- **Daniel Castro**
- **Alejandro Moreno**
- **Francisco Javier Soler**

Proyecto desarrollado como parte de la asignatura de Programación - 1º DAM.

#### IES Francisco de los Rios

