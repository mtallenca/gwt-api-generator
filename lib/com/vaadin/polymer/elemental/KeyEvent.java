package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface KeyEvent extends Event {
    @JsProperty
    int getKeyCode();

    @JsProperty
    boolean getShiftKey();

    @JsProperty
    boolean getCtrlKey();

    @JsProperty
    boolean getAltKey();
}
