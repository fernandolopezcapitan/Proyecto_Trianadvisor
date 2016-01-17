package com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoraciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flopez on 16/12/2015.
 */
public class Valoraciones {
    @SerializedName("results")
    @Expose
    private List<ResultValoraciones> results = new ArrayList<ResultValoraciones>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Valoraciones() {
    }

    /**
     *
     * @param results
     */
    public Valoraciones(List<ResultValoraciones> results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The results
     */
    public List<ResultValoraciones> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<ResultValoraciones> results) {
        this.results = results;
    }

}
