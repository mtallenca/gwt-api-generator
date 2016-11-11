package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JavaScriptObject;

public class Model extends JavaScriptObject {
   protected Model() {}     
   
   public final native Object getItem(String name) /*-{ return this[name]; }-*/;
   public final native Object getItem() /*-{ return this['item']; }-*/;
   public final native int getIndex() /*-{ return this.index; }-*/;
}
