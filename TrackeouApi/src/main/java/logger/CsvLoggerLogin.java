package logger;

import logger.LoggerLogin;
import service.ResultadoLogin;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Formato CSV:
 * timestamp,email,resultado,motivo,clienteId (opcional)
 */
public class CsvLoggerLogin implements LoggerLogin {

    private final Path csvPath;
    private final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public CsvLoggerLogin(Path csvPath) {
        this.csvPath = csvPath;
        ensureHeader();
    }

    private void ensureHeader() {
        try {
            if (!Files.exists(csvPath)) {
                Files.createDirectories(csvPath.getParent() == null ? Path.of(".") : csvPath.getParent());
                try (BufferedWriter w = Files.newBufferedWriter(csvPath, StandardOpenOption.CREATE)) {
                    w.write("timestamp,email,resultado,motivo,clienteId");
                    w.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar/garantir header do log CSV: " + e.getMessage(), e);
        }
    }

    @Override
    public void log(ResultadoLogin resultado) {
        String line = String.format("%s,%s,%s,%s,%s",
                LocalDateTime.now().format(dtf),
                safe(resultado.getEmail()),
                resultado.isSucesso() ? "SUCESSO" : "FALHA",
                safe(resultado.getMensagem()),
                resultado.getClienteId() == null ? "" : resultado.getClienteId().toString()
        );
        try (BufferedWriter w = Files.newBufferedWriter(csvPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            w.write(line);
            w.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro escrevendo log CSV: " + e.getMessage(), e);
        }
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replaceAll("[\\r\\n,]", " "); // simplificar CSV
    }
}
