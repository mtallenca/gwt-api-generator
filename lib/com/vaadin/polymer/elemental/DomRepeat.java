package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JsArray;

public class DomRepeat extends HTMLElement {
    protected DomRepeat() {}

    public final native Model modelForElement(HTMLElement e) /*-{ return this.modelForElement(e); }-*/;
}
