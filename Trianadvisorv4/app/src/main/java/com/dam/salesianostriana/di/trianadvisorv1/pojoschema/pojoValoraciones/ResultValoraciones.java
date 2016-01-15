package com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoraciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by flopez on 16/12/2015.
 */
public class ResultValoraciones {
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("sitio")
    @Expose
    private SitioValoraciones sitio;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("usuario")
    @Expose
    private UsuarioValoraciones usuario;
    @SerializedName("valoracion")
    @Expose
    private Integer valoracion;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResultValoraciones() {
    }

    /**
     *
     * @param updatedAt
     * @param sitio
     * @param valoracion
     * @param usuario
     * @param objectId
     * @param createdAt
     */
    public ResultValoraciones(String createdAt, String objectId, SitioValoraciones sitio, String updatedAt, UsuarioValoraciones usuario, Integer valoracion) {
        this.createdAt = createdAt;
        this.objectId = objectId;
        this.sitio = sitio;
        this.updatedAt = updatedAt;
        this.usuario = usuario;
        this.valoracion = valoracion;
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
     * The sitio
     */
    public SitioValoraciones getSitio() {
        return sitio;
    }

    /**
     *
     * @param sitio
     * The sitio
     */
    public void setSitio(SitioValoraciones sitio) {
        this.sitio = sitio;
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
     * The usuario
     */
    public UsuarioValoraciones getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     * The usuario
     */
    public void setUsuario(UsuarioValoraciones usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     * The valoracion
     */
    public Integer getValoracion() {
        return valoracion;
    }

    /**
     *
     * @param valoracion
     * The valoracion
     */
    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

}

