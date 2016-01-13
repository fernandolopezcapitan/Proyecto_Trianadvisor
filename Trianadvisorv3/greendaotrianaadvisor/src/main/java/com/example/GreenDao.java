package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class GreenDao {

    public static void main(String[] args) {

        // Cada cambio que realicemos en el modelo de la base de datos,
        // tiene que verse reflejado, en el aumento del número de versión,
        // para que Android, sepa que tiene que realizar un upgrade de la
        // base de datos
        int version = 1;

        // ¿Que ruta hay que ponerle?
        // Ejemplo: "com.jtristan.greendao.dao"
        String ruta = "com.dam.salesianostriana.di.trianadvisorv1.greendao";

        Schema schema = new Schema(version, ruta);
        addTable(schema);
        try {

            if (!Files.isDirectory(Paths.get("./src-gen")))
                Files.createDirectory(Paths.get("./src-gen"));
            new DaoGenerator().generateAll(schema, "./src-gen");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTable(Schema schema) {

        // Tabla 1. Valoraciones
        // Ver pojoValoraciones ->> ResultValoraciones
        Entity valoraciones = schema.addEntity("Valoraciones");

        valoraciones.addIdProperty();
        valoraciones.addStringProperty("objectId");
        valoraciones.addStringProperty("updateAt");// ¿Hace falta updateAt en todas las tablas?
        valoraciones.addStringProperty("valoracion");

        // Tabla 2. Sitios
        // Ver Sitios ->> Result
        Entity sitios = schema.addEntity("Sitios");

        sitios.addIdProperty();
        sitios.addStringProperty("objectId");
        sitios.addStringProperty("updateAt");
        sitios.addStringProperty("nombre");
        sitios.addStringProperty("categoria");
        sitios.addStringProperty("direccion");
        sitios.addStringProperty("tlf");
        sitios.addStringProperty("descripcion");
        sitios.addStringProperty("fotoUrl");
        sitios.addStringProperty("latitud");
        sitios.addStringProperty("longitud");

        // Tabla 3. Usuarios
        // Ver Login
        Entity usuarios = schema.addEntity("Usuarios");

        usuarios.addIdProperty();
        usuarios.addStringProperty("objectId");
        usuarios.addStringProperty("updateAt");
        usuarios.addStringProperty("sessionToken");
        usuarios.addStringProperty("user");
        usuarios.addStringProperty("nombre");
        usuarios.addStringProperty("email");
        usuarios.addStringProperty("fotoUrl");

        // Tabla 4. Comentarios
        // Ver Comentario ->> Result
        Entity comentarios = schema.addEntity("Comentarios");

        comentarios.addIdProperty();
        comentarios.addStringProperty("objectId");
        comentarios.addStringProperty("updateAt");
        comentarios.addStringProperty("comentario");

        // Relación Sitio - Comentarios
        // Un Sitio tendrá varios Comentarios (1:N) y un
        // Comentario sólo puede pertenecer a un Sitio (1:1).

        Property idSitioC = comentarios.addLongProperty("idSitioC").notNull().getProperty();
        comentarios.addToOne(sitios, idSitioC);//La tabla comentarios tiene como clave foránea el idSitioC de sitios

        ToMany comentariosDeUnSitio = sitios.addToMany(comentarios, idSitioC);
        comentariosDeUnSitio.setName("comentariosDeUnSitio");
        comentariosDeUnSitio.orderAsc(idSitioC);

        // Relación Usuario - Comentarios
        // Un Usuario podrá hacer varios Comentarios (1:N) y un
        // Comentario sólo puede pertenecer a un Usuario (1:1).

        Property idUsuarioC = comentarios.addLongProperty("idUsuarioC").notNull().getProperty();
        comentarios.addToOne(usuarios, idUsuarioC);//La tabla comentarios tiene como clave foránea el idUsuarioC de usuarios

        ToMany comentariosDeUnUsuario = usuarios.addToMany(comentarios, idUsuarioC);
        comentariosDeUnUsuario.setName("comentariosDeUnUsuario");
        comentariosDeUnUsuario.orderAsc(idUsuarioC);

        // Relación Valoraciones - Usuario
        // Un Usuario podrá hacer varias Valoraciones (1:N) y una
        // Valoración sólo puede pertenecer a un Usuario (1:1).

        Property idUsuarioV = valoraciones.addLongProperty("idUsuarioV").notNull().getProperty();
        valoraciones.addToOne(usuarios, idUsuarioV);//La tabla comentarios tiene como clave foránea el idUsuarioV de usuarios

        ToMany valoracionesDeUnUsuario = usuarios.addToMany(valoraciones, idUsuarioV);
        valoracionesDeUnUsuario.setName("valoracionesDeUnUsuario");
        valoracionesDeUnUsuario.orderAsc(idUsuarioV);

        // Relación Valoraciones - Sitio
        // Un Sitio podrá hacer tener varias Valoraciones (1:N) y una
        // Valoración sólo puede pertenecer a un Sitio (1:1).

        Property idSitioV = valoraciones.addLongProperty("idSitioV").notNull().getProperty();
        valoraciones.addToOne(sitios, idSitioV);//La tabla comentarios tiene como clave foránea el idSitioV de sitios

        ToMany valoracionesDeUnSitio = usuarios.addToMany(valoraciones, idSitioV);
        valoracionesDeUnSitio.setName("valoracionesDeUnSitio");
        valoracionesDeUnSitio.orderAsc(idSitioV);


    }

}
