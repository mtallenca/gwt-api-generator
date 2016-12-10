package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JsArray;
import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface TouchEvent extends Event {
    @JsProperty
    JsArray<Touch> getTouches();
}
