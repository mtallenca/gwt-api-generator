package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
public interface Event {
    @JsProperty
    Event getSourceEvent();

    @JsProperty
    Model getModel();

    @JsProperty
    String getType();

    @JsProperty
    Detail getDetail();

    @JsProperty
    HTMLElement getTarget();

    @JsProperty
    double getTimeStamp();

    @JsProperty
    HTMLElement getCurrentTarget();
    
    @JsType(isNative=true)
    public interface Detail {
        @JsProperty String getValue();
        @JsProperty String getState();
        @JsProperty int getX();
        @JsProperty int getY();
        @JsProperty int getDx();
        @JsProperty int getDy();
        @JsProperty int getDdx();
        @JsProperty int getDdy();
    }

    void stopPropagation();
    void stopImmediatePropagation();
}
