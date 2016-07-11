package com.epam.trainings.dao;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 1 on 11.07.2016.
 */
public interface BasicCrudDao<ENTERED_TYPE> {
    ENTERED_TYPE save(ENTERED_TYPE entity);

    @Nullable ENTERED_TYPE get(@NotNull Serializable id);

    @Nullable ENTERED_TYPE get(String prorperty, @NotNull Serializable id);

    @Nullable ENTERED_TYPE get(Map<String, String> propertiesMapWithValues);

    List<ENTERED_TYPE> getList(String prorperty, @NotNull Serializable id);

    List<ENTERED_TYPE> getList(Map<String, String> propertiesMapWithValues);

    void remove(@NotNull Serializable id);
}
