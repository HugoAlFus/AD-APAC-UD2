CREATE OR REPLACE FUNCTION actualizar_precio_total()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE public.PEDIDO
    SET PRECIO_TOTAL = (
        SELECT SUM(SUBTOTAL)
        FROM public.CONTENER
        WHERE ID_PEDIDO = NEW.ID_PEDIDO
    )
    WHERE ID_PEDIDO = NEW.ID_PEDIDO;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
--
CREATE TRIGGER trigger_actualizar_precio_total
    AFTER INSERT OR UPDATE ON public.CONTENER
    FOR EACH ROW
EXECUTE FUNCTION actualizar_precio_total();