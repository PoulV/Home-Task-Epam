package com.epam.trenings.entities;

import com.epam.trenings.Utils;
import com.epam.trenings.model.INamed;

import static com.epam.trenings.entities.EntityUtils.EntityType.*;

/**
 * Created by Pol on 6/7/2016.
 */
public class EntityUtils {
    private static StringBuffer buffer = new StringBuffer();

    public static <NAMED extends INamed> EntityObject getEntityFromNamed(NAMED targetNamed) {
        buffer.setLength(Utils.ZERO);
        Utils.getStringFromNamed(buffer, targetNamed);
        EntityType entityType = COMPOSITION;
        switch (targetNamed.getClass().getSimpleName()) {
            case Utils.ALBUM:
                entityType = ALBUM;
                break;
            case Utils.MUSICIAN:
                entityType = MUSICIAN;
                break;
        }
        EntityObject resultEntity = new EntityObject(buffer.toString().replaceAll(Utils.EOL, Utils.EMPTY_STRING), entityType);
        return resultEntity;
    }

    public enum EntityType {COMPOSITION, ALBUM, MUSICIAN}
}