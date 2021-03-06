package com.epam.trenings;

public interface ITypeConverter<IN extends Comparable, OUT extends Comparable> {
    OUT apply(IN element);
}
