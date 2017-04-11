package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JavaScriptObject;

public class Model extends JavaScriptObject {
   protected Model() {}     
   
   public final native Model getParentModel() /*-{ return this.dataHost.dataHost; }-*/;
   public final native int getIndex() /*-{ return this.index; }-*/;
   public final native Object get(String propertyPath) /*-{ return this.get(propertyPath); }-*/;
   public final native String getAsString(String propertyPath) /*-{ return "" + this.get(propertyPath); }-*/;
   public final native int getAsInt(String propertyPath) /*-{ return this.get(propertyPath); }-*/;
   public final native void set(String propertyPath, Object value) /*-{ this.set(propertyPath, value); }-*/;
   public final native void set(String propertyPath, int value) /*-{ this.set(propertyPath, value); }-*/;
}
