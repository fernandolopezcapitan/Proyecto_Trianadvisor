package com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoraciones;

/**
 * Created by flopez on 16/12/2015.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FotoValoraciones {

    @SerializedName("__type")
    @Expose
    private String Type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     *
     */
    public FotoValoraciones() {
    }

    /**
     *
     * @param Type
     * @param name
     * @param url
     */
    public FotoValoraciones(String Type, String name, String url) {
        this.Type = Type;
        this.name = name;
        this.url = url;
    }

    /**
     *
     * @return
     * The Type
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The __type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
