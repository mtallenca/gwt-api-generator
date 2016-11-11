package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface DOMTokenList {

    @JsProperty
    int getLength();

    boolean contains(String s);

    void add(String token);
 
    void remove(String token);

    void replace(String token, String replacement);

    boolean toggle(String token);
}
