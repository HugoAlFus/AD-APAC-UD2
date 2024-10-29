# Proyecto Restaurante

* * *

## Índice

1. [Explicación de la Base de Datos](#explicación-de-la-base-de-datos)
2. [Explicación del proyecto](#explicacion-del-proyecto)
3. [Instrucciones de uso](#instrucciones-de-uso)
4. [Uso de PostgreSQL](#uso-de-postgresql)
5. [Uso de Lombok](#uso-de-lombok)
6. [Bibliografía](#bibliografia)

* * *

## Explicación de la Base de Datos

Esta base de datos es sobre un restaurante y está diseñada para gestionar información relacionada con platos, chefs,
clientes, mesas, pedidos y las relaciones entre ellos.
A continuación se describen las tablas con sus relaciones:

![Imagen del diagrama de la base de datos](src/main/resources/img/diagrama_BD.png "Diagrama")

### 1. Plato

* **Descripción**: Gestión de los clientes que realizan pedidos en el restaurante.
* **Atributos**: ID, nombre, teléfono, correo electrónico, dirección.

### 2. Chef

Descripción: Gestión de los chefs que trabajan en el restaurante.
Atributos: ID, nombre, especialidad, experiencia, teléfono, disponibilidad.

### 3. Cliente

Descripción: Gestión de los clientes que realizan pedidos en el restaurante.
Atributos: ID, nombre, teléfono, correo electrónico, dirección.

### 4. Mesa

Descripción: Gestión de las mesas disponibles en el restaurante.
Atributos: ID, número, capacidad, ubicación, estado.

### 5. Pedido

Descripción: Gestión de los pedidos realizados por los clientes.
Atributos: ID, fecha, precio total, cliente, mesa, estado.

### 6. Contener
Descripción: Relación entre los pedidos y los platos que contienen.
Atributos: ID del pedido, ID del plato, cantidad, subtotal.

### 7. Realizar
Descripción: Relación entre los platos y los chefs que los preparan.
Atributos: ID del plato, ID del chef, fecha.



* * *

## Explicacion del proyecto
Es una aplicación diseñada para gestionar la información de un restaurante, que incluye platos, chefs, clientes, mesas, y pedidos.
Sus componentes son los siguientes:
### 1. Plato

* **ID_PLATO**:Identificador único del plato (clave primaria).
* **NOMBRE_PLATO**: Nombre del plato (único y no nulo).
* **DESCRIPCION**: Descripción del plato (no nulo).
* **PRECIO_PLATO**: Precio del plato (no nulo).
* **CATEGORIA_PLATO**: Categoría del plato (no nulo).

### 2. Chef

* **ID_CHEF**:Identificador único del chef (clave primaria).
* **NOMBRE_CHEF**: Nombre del chef (no nulo).
* **ESPECIALIDAD_CHEF**: Especialidad del chef (no nulo).
* **EXPERIENCIA**: Años de experiencia del chef (no nulo).
* **TELEFONO_CHEF**: Teléfono del chef (único y no nulo).
* **DISPONIBLE**: Disponibilidad del chef (no nulo).

### 3. Cliente

* **ID_CLIENTE**: Identificador único del cliente (clave primaria).
* **NOMBRE_CLIENTE**: Nombre del cliente (no nulo).
* **TELEFONO_CLIENTE**: Teléfono del cliente (único y no nulo).
* **CORREO_ELECTRONICO**: Correo electrónico del cliente (único y no nulo).
* **DIRECCION**: Dirección del cliente (no nulo).

### 4. Mesa

* **ID_MESA**: Identificador único de la mesa (clave primaria).
* **NUMERO_MESA**: Número de la mesa (único y no nulo).
* **CAPACIDAD**: Capacidad de la mesa (no nulo).
* **UBICACION_MESA**: Ubicación de la mesa (no nulo).
* **ESTADO_MESA**: Estado de la mesa (no nulo).

### 5. Pedido

* **ID_PEDIDO**: Identificador único del pedido (clave primaria).
* **FECHA_PEDIDO**: Fecha del pedido (no nulo).
* **PRECIO_TOTAL**: Precio total del pedido (no nulo, por defecto 0.00).
* **ID_CLIENTE**: Identificador del cliente que realizó el pedido (clave foránea).
* **ID_MESA**: Identificador de la mesa asociada al pedido (clave foránea).
* **ESTADO_PEDIDO**: Estado del pedido (no nulo).

### 6. Contener
* **ID_PEDIDO**: Identificador del pedido (clave primaria y foránea).
* **ID_PLATO**: Identificador del plato (clave primaria y foránea).
* **CANTIDAD**: Cantidad del plato en el pedido (no nulo).
* **SUBTOTAL**: Subtotal del plato en el pedido (no nulo).

### 7. Realizar
* **ID_PLATO**: Identificador del plato (clave primaria y foránea).
* **ID_CHEF**: Identificador del chef (clave primaria y foránea).
* **FECHA**: Fecha en que el chef realizó el plato (no nulo).

* * *

## Instrucciones de uso

* * *

## Uso de PostgreSQL

* * *

## Uso de Lombok

* * *
![Imagen Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Windows 11](https://img.shields.io/badge/Windows%2011-%230079d5.svg?style=for-the-badge&logo=Windows%2011&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

