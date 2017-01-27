package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface MouseEvent extends Event {
    @JsProperty
    int getClientX();

    @JsProperty
    int getClientY();
}
