INSERT INTO public.PLATO (NOMBRE_PLATO, PRECIO_PLATO, CATEGORIA_PLATO)
VALUES ('Ensalada César', 7.50, 'Ensalada'),
       ('Sopa de Tomate', 5.00, 'Sopa'),
       ('Pizza Margarita', 12.00, 'Plato fuerte'),
       ('Hamburguesa', 8.50, 'Plato fuerte'),
       ('Tacos', 6.00, 'Plato fuerte'),
       ('Sushi', 15.00, 'Plato fuerte'),
       ('Tarta de Queso', 6.50, 'Postre');

INSERT INTO public.CHEF (NOMBRE_CHEF, ESPECIALIDAD_CHEF, EXPERIENCIA, TELEFONO_CHEF, DISPONIBLE)
VALUES ('Gordon Ramsay', 'Italiana', 20, '123456789', TRUE),
       ('Jamie Oliver', 'Británica', 15, '987654321', TRUE),
       ('Heston Blumenthal', 'Japonesa', 25, '123123123', TRUE),
       ('Massimo Bottura', 'Italiana', 30, '321321321', FALSE);

INSERT INTO public.CLIENTE (NOMBRE_CLIENTE, TELEFONO_CLIENTE, CORREO_ELECTRONICO, DIRECCION)
VALUES ('Juan Pérez', '111222333', 'juan.perez@example.com', 'Calle Falsa 123'),
       ('María López', '444555666', 'maria.lopez@example.com', 'Avenida Siempre Viva 742'),
       ('Carlos García', '777888999', 'carlos.garcia@example.com', 'Calle Luna 456'),
       ('Ana Martínez', '666555444', 'ana.martinez@example.com', 'Avenida Sol 789');

INSERT INTO public.MESA (NUMERO_MESA, CAPACIDAD, UBICACION_MESA, ESTADO_MESA)
VALUES (1, 4, 'Terraza', 'Disponible'),
       (2, 2, 'Interior', 'Ocupada'),
       (3, 6, 'Jardín', 'Disponible'),
       (4, 4, 'Interior', 'Reservada');

INSERT INTO public.PEDIDO (FECHA_PEDIDO, PRECIO_TOTAL, ID_CLIENTE, ID_MESA)
VALUES ('2023-10-01', 19.50, 1, 1),
       ('2023-10-02', 12.00, 2, 2),
       ('2023-10-03', 25.50, 3, 3),
       ('2023-10-04', 30.00, 4, 4);

INSERT INTO public.CONTENER (ID_PEDIDO, ID_PLATO, CANTIDAD, SUBTOTAL)
VALUES (1, 1, 1, 7.50),
       (1, 2, 1, 5.00),
       (1, 3, 1, 7.00),
       (2, 3, 1, 12.00),
       (3, 4, 2, 17.00),
       (3, 5, 1, 6.00),
       (3, 6, 1, 15.00),
       (4, 4, 1, 8.50),
       (4, 5, 2, 12.00);

INSERT INTO public.REALIZAR (ID_PLATO, ID_CHEF, FECHA)
VALUES (1, 1, '2023-10-01'),
       (2, 2, '2023-10-01'),
       (3, 1, '2023-10-02'),
       (4, 3, '2023-10-03'),
       (5, 4, '2023-10-03'),
       (6, 3, '2023-10-04');