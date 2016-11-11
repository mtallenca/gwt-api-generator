package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;

import com.google.gwt.dom.client.Element;

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
    Element getTarget();

    @JsProperty
    Element getCurrentTarget();
    
    @JsType(isNative=true)
    public interface Detail {
    }

    void stopPropagation();
}
