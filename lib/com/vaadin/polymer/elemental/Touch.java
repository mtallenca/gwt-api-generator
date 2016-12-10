package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface Touch {
    @JsProperty
    int getClientX();

    @JsProperty
    int getClientY();

    @JsProperty
    int getPageX();

    @JsProperty
    int getPageY();
}
