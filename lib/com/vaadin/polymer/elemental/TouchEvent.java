package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Touch;
import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface TouchEvent extends Event {
    @JsProperty
    JsArray<Touch> getTouches();
    @JsProperty
    JsArray<Touch> getChangedTouches();
}
