package com.epam.trenings.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pol on 6/7/2016.
 */
public class EntityObject implements Serializable {
    private ObjectType entityType;
    private String stringViewOfObject;
    private List<String> dependencyListToMusician;
    private List<String> dependencyListToComposition;

    public EntityObject(String stringViewOfObject, List<String> dependencyListToMusician, List<String> dependencyListToComposition) {
        this.stringViewOfObject = stringViewOfObject;
        this.dependencyListToMusician = dependencyListToMusician;
        this.dependencyListToComposition = dependencyListToComposition;
    }

    public ObjectType getEntityType() {
        return entityType;
    }

    public List<String> getDependencyListToMusician() {
        return dependencyListToMusician;
    }

    public List<String> getDependencyListToComposition() {
        return dependencyListToComposition;
    }
}
