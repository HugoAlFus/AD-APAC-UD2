INSERT INTO public.PLATO (NOMBRE_PLATO, DESCRIPCION, PRECIO_PLATO, CATEGORIA_PLATO)
VALUES ('Ensalada César',
        'Una clásica ensalada con crujientes hojas de lechuga romana, aderezo César cremoso, queso parmesano y trocitos de pan tostado. Ideal para una opción ligera y fresca.',
        7.50, 'Ensalada'),
       ('Sopa de Tomate',
        'Una reconfortante sopa de tomate hecha con tomates frescos y hierbas aromáticas. Suave y deliciosa, perfecta para comenzar cualquier comida.',
        5.00, 'Sopa'),
       ('Pizza Margarita',
        ' Una pizza clásica con una base de salsa de tomate, mozzarella fundida y albahaca fresca, que ofrece un sabor simple pero auténtico, ideal para los amantes de la cocina italiana.',
        12.00, 'Fuerte'),
       ('Hamburguesa',
        ' Una jugosa hamburguesa con carne de alta calidad, acompañada de vegetales frescos y pan suave. Perfecta para quienes buscan un plato satisfactorio y lleno de sabor.',
        8.50, 'Fuerte'),
       ('Tacos','Tortillas de maíz rellenas de ingredientes frescos y sabrosos, una opción deliciosa y típica de la comida mexicana, con el balance perfecto entre sazón y frescura.', 6.00, 'Fuerte'),
       ('Sushi','Un plato japonés elegante que combina arroz sazonado con vinagre y una selección de pescado fresco y vegetales. Perfecto para quienes disfrutan de sabores delicados y frescos.', 15.00, 'Fuerte'),
       ('Tarta de Queso','Un postre cremoso y dulce con base de galleta y suave relleno de queso. Perfecto para cerrar la comida con un toque dulce y delicioso.', 6.50, 'Postre');

INSERT INTO public.CHEF (NOMBRE_CHEF, ESPECIALIDAD_CHEF, EXPERIENCIA, TELEFONO_CHEF, DISPONIBLE)
VALUES ('Gordon Ramsay', 'Italiana', 20, '123456789', TRUE),
       ('Jamie Oliver', 'Britanica', 15, '987654321', TRUE),
       ('Heston Blumenthal', 'Japonesa', 25, '123123123', TRUE),
       ('Massimo Bottura', 'Italiana', 30, '321321321', FALSE);

INSERT INTO public.CLIENTE (NOMBRE_CLIENTE, TELEFONO_CLIENTE, CORREO_ELECTRONICO, DIRECCION)
VALUES ('Juan Pérez', '111222333', 'juan.perez@example.com', 'Calle Falsa 123'),
       ('María López', '444555666', 'maria.lopez@example.com', 'Avenida Siempre Viva 742'),
       ('Carlos García', '777888999', 'carlos.garcia@example.com', 'Calle Luna 456'),
       ('Ana Martínez', '666555444', 'ana.martinez@example.com', 'Avenida Sol 789');

INSERT INTO public.MESA (NUMERO_MESA, CAPACIDAD, UBICACION_MESA, ESTADO_MESA)
VALUES (1, 4, 'Terraza', 'Libre'),
       (2, 2, 'Zona_VIP', 'Ocupada'),
       (3, 6, 'Barra', 'Libre'),
       (4, 4, 'Ventana', 'Reservada');

INSERT INTO public.PEDIDO (FECHA_PEDIDO, PRECIO_TOTAL, ID_CLIENTE, ID_MESA, ESTADO_PEDIDO)
VALUES ('2024-10-01', 19.50, 1, 1, 'Pendiente'),
       ('2023-10-02', 12.00, 2, 2, 'Entregado'),
       ('2022-10-03', 25.50, 3, 3, 'Cancelado'),
       ('2023-10-04', 30.00, 4, 4, 'Preparacion');

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