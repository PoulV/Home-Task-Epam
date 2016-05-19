package com.epam.trenings;

public interface ITypeConverter<IN extends Comparable<IN>, OUT extends Comparable<OUT>> {
    OUT aply(IN element);
}
