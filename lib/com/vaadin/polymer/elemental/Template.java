package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JsArray;

public class Template extends HTMLElement {
    protected Template() {}

    public final native String getIs() /*-{ return this.is; }-*/;
    public final native void setIs(String value) /*-{ this.is = value; }-*/;
    public final native String getAs() /*-{ return this.as; }-*/;
    public final native void setAs(String value) /*-{ this.as = value; }-*/;
    public final native String getIdexAs() /*-{ return idexAx; }-*/;
    public final native void setIndexAs(String value) /*-{ this.indexAs = value; }-*/;
    public final native JsArray<?> getItems() /*-{ return this.items; }-*/;
    public final native void setItems(JsArray<?> items) /*-{ this.items = items; }-*/;
    public final native void setFilter(Function<Boolean, ?> items) /*-{ this.filter = items; }-*/;
    public final native void setIf(String value) /*-{ this["if"] = value; }-*/;
}
