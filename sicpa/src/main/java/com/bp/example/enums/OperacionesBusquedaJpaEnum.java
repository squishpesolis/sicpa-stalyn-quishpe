package com.bp.example.enums;

public enum OperacionesBusquedaJpaEnum {
    GREATER_THAN, //>
    LESS_THAN, //<
    GREATER_THAN_EQUAL, //>=
    LESS_THAN_EQUAL,//=<
    NOT_EQUAL, // <>
    EQUAL,//==
    MATCH,// %%
    MATCH_START, //%---
    MATCH_END,//---%
    IN,
    NOT_IN,
    BOOLEAN_EQUAL // solo para valores booleano
}
