package com.paulo.apipokedex.services;

import java.util.function.Consumer;


class UpdatePartial{
    protected static <E> void updatePropererIfNotNull(Consumer<E> setter, E updatedValue ){
        if(updatedValue != null) setter.accept(updatedValue);
    }
}