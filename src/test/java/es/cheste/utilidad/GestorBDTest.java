package es.cheste.utilidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestorBDTest {

    private Connection connectionMock;
    private Statement statementMock;
    private ConexionBD conexionBDMock;

    @BeforeEach
    void setUp() {
        connectionMock = mock(Connection.class);
        statementMock = mock(Statement.class);
        conexionBDMock = mock(ConexionBD.class);
    }

    @Test
    void iniciarBaseDatos_executesAllSQLStatementsSuccessfully() throws SQLException {
        when(conexionBDMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.createStatement()).thenReturn(statementMock);

        boolean result = GestorBD.iniciarBaseDatos();

        assertTrue(result);
    }
}