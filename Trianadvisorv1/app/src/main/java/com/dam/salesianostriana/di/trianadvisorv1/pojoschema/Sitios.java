package com.dam.salesianostriana.di.trianadvisorv1.pojoschema;

/**
 * Created by flopez on 15/12/2015.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sitios {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Sitios() {
    }

    /**
     *
     * @param results
     */
    public Sitios(List<Result> results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
