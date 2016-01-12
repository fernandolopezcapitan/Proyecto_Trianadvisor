package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

public class GreenDao {

    public static void main(String[] args) {

        // A Schema hay q pasarle la version y la ruta del paquete
        Schema schema = new Schema(1000, "de.greenrobot.daoexample");
        //addNote(schema);
        try {

            if (!Files.isDirectory(Paths.get("./src-gen")))
                Files.createDirectory(Paths.get("./src-gen"));

            new DaoGenerator().generateAll(schema, "./src-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
