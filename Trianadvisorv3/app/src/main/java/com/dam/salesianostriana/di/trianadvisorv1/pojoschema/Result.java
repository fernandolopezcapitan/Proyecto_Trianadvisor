package com.dam.salesianostriana.di.trianadvisorv1.pojoschema;

/**
 * Created by flopez on 15/12/2015.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("fecha")
    @Expose
    private Fecha fecha;
    @SerializedName("comentario")
    @Expose
    private String comentario;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("coordenadas")
    @Expose
    private Coordenadas coordenadas;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("foto")
    @Expose
    private Foto foto;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("sitio")
    @Expose
    private Sitio sitio;
    @SerializedName("usuario")
    @Expose
    private Login login;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    public Result(Fecha fecha, String comentario, Foto foto, String nombre) {
        this.fecha = fecha;
        this.comentario = comentario;
        this.foto = foto;
        this.nombre = nombre;
    }

    public Result(String comentario, String createdAt, Fecha fecha, String objectId, Sitio sitio, String updatedAt, Login login) {
        this.comentario = comentario;
        this.createdAt = createdAt;
        this.fecha = fecha;
        this.objectId = objectId;
        this.sitio = sitio;
        this.updatedAt = updatedAt;
        this.login = login;
    }

    public Result(String categoria, Coordenadas coordenadas, String createdAt, String descripcion, String direccion, Foto foto, String nombre, String objectId, String telefono, String updatedAt) {
        this.categoria = categoria;
        this.coordenadas = coordenadas;
        this.createdAt = createdAt;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.foto = foto;
        this.nombre = nombre;
        this.objectId = objectId;
        this.telefono = telefono;
        this.updatedAt = updatedAt;
    }

    public Result(String objectId, String nombre, String telefono, Foto foto, String categoria, String direccion) {
        this.objectId = objectId;
        this.nombre = nombre;
        this.telefono = telefono;
        this.foto = foto;
        this.categoria = categoria;
        this.direccion = direccion;
    }
    /**
     *
     * @return
     * The comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     *
     * @param comentario
     * The comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     *
     * @return
     * The categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     * The categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return
     * The coordenadas
     */
    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    /**
     *
     * @param coordenadas
     * The coordenadas
     */
    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     * The descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     * The direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     * The direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return
     * The foto
     */
    public Foto getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     * The foto
     */
    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     * The nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     * The nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     * The objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId
     * The objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     *
     * @return
     * The telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     * The telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    /**
     *
     * @return
     * The fecha
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     * The fecha
     */
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
    /**
     *
     * @return
     * The sitio
     */
    public Sitio getSitio() {
        return sitio;
    }

    /**
     *
     * @param sitio
     * The sitio
     */
    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }
    /**
     *
     * @return
     * The usuario
     */
    public Login getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * The usuario
     */
    public void setLogin(Login login) {
        this.login = login;
    }



}
