CREATE TABLE IF NOT EXISTS public.PLATO
(
    ID_PLATO        SERIAL PRIMARY KEY,
    NOMBRE_PLATO    VARCHAR(50) UNIQUE NOT NULL,
    PRECIO_PLATO    DECIMAL(5, 2) NOT NULL,
    CATEGORIA_PLATO VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS public.CHEF
(
    ID_CHEF           SERIAL PRIMARY KEY,
    NOMBRE_CHEF       VARCHAR(50) NOT NULL,
    ESPECIALIDAD_CHEF VARCHAR(20),
    EXPERIENCIA       INT,
    TELEFONO_CHEF     CHAR(9) UNIQUE NOT NULL,
    DISPONIBLE        BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS public.REALIZAR
(
    ID_PLATO INT,
    ID_CHEF  INT,
    FECHA    DATE,
    PRIMARY KEY (ID_PLATO, ID_CHEF),
    FOREIGN KEY (ID_PLATO) REFERENCES PLATO (ID_PLATO),
    FOREIGN KEY (ID_CHEF) REFERENCES CHEF (ID_CHEF)
);

CREATE TABLE IF NOT EXISTS public.CLIENTE
(
    ID_CLIENTE         SERIAL PRIMARY KEY,
    NOMBRE_CLIENTE     VARCHAR(50) NOT NULL,
    TELEFONO_CLIENTE   CHAR(9) UNIQUE,
    CORREO_ELECTRONICO VARCHAR(50) UNIQUE NOT NULL,
    DIRECCION          VARCHAR(75)
);

CREATE TABLE IF NOT EXISTS public.MESA
(
    ID_MESA        SERIAL PRIMARY KEY,
    NUMERO_MESA    INT UNIQUE NOT NULL,
    CAPACIDAD      INT,
    UBICACION_MESA VARCHAR(20),
    ESTADO_MESA    VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.PEDIDO
(
    ID_PEDIDO    SERIAL PRIMARY KEY,
    FECHA_PEDIDO DATE,
    PRECIO_TOTAL DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    ID_CLIENTE   INT,
    ID_MESA      INT,
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID_CLIENTE),
    FOREIGN KEY (ID_MESA) REFERENCES MESA (ID_MESA)
);

CREATE TABLE IF NOT EXISTS public.CONTENER
(
    ID_PEDIDO INT,
    ID_PLATO  INT,
    CANTIDAD  INT NOT NULL,
    SUBTOTAL  DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (ID_PEDIDO, ID_PLATO)
);