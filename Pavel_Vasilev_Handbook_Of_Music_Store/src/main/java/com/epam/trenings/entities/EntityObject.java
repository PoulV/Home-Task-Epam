package com.epam.trenings.entities;

import java.io.Serializable;

/**
 * Created by Pol on 6/7/2016.
 */
public class EntityObject implements Serializable {
    private EntityUtils.EntityType entityType;
    private String stringViewOfObject;

    public EntityObject(String stringViewOfObject, EntityUtils.EntityType entityType) {
        this.stringViewOfObject = stringViewOfObject;
        this.entityType = entityType;
    }

    public String getStringViewOfObject() {
        return stringViewOfObject;
    }

    public EntityUtils.EntityType getEntityType() {
        return entityType;
    }

}