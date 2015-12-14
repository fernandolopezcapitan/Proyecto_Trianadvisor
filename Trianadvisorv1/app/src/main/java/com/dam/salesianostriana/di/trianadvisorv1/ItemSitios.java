package com.dam.salesianostriana.di.trianadvisorv1;

/**
 * Created by flopez on 14/12/2015.
 */
public class ItemSitios {

    private String nombre, descripcion, direccion, coordenadas, categoria, telefono;
    private int foto;

    // Constructor para Activity de sitios registrados
    public ItemSitios(String nombre, String direccion, String categoria, String telefono, int foto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.categoria = categoria;
        this.telefono = telefono;
        this.foto = foto;
    }

    // Constructor completo
    public ItemSitios(String nombre, String descripcion, String direccion, String coordenadas, String categoria, String telefono, int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.categoria = categoria;
        this.telefono = telefono;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
