package com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoracion;

/**
 * Created by flopez on 11/01/2016.
 */
public class ComentariosAux {

    private String imagen;
    private String nombre;
    private String comentario;
    private String fecha;

    public ComentariosAux() {
    }

    public ComentariosAux(String nombre, String comentario, String fecha) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public ComentariosAux(String imagen, String nombre, String comentario, String fecha) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
