package com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares;

/**
 * Created by flopez on 17/12/2015.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Bares {
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Bares() {
    }

    /**
     *
     * @param results
     */
    public Bares(List<Result> results) {
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
