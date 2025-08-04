package com.trackeou.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CsvLoggerLogin implements LoggerLogin {
    private String resource;

    public CsvLoggerLogin(String resource) {
        this.resource = resource;
    }

    @Override
    public void registrar(String email, String status) {
        try (FileWriter fw = new FileWriter(resource,true)){
            if(new java.io.File(resource).length() == 0) {
                fw.write("dataHora,email,status\n");
            }
            fw.write(LocalDateTime.now() + "," + email + "," + status  + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
