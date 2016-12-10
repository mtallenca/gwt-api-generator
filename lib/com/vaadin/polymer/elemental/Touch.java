package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JavaScriptObject;

public class Touch extends JavaScriptObject {
    public final native int getClientX() /*-{ this.clientX; }-*/;
    public final native int getClientY() /*-{ this.clientY; }-*/;
    public final native int getPageX() /*-{ this.pageX; }-*/;
    public final native int getPageY() /*-{ this.pageY; }-*/;
}
