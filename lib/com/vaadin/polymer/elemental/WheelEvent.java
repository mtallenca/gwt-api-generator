package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface WheelEvent extends Event {
    public static final int DOM_DELTA_PIXEL = 0;
    public static final int DOM_DELTA_LINE = 1;
    public static final int DOM_DELTA_PAGE = 2;

    @JsProperty
    int getDeltaX();

    @JsProperty
    int getDeltaY();

    @JsProperty
    int getDeltaMode();
}
