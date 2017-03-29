package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JavaScriptObject;

public class Model extends JavaScriptObject {
   protected Model() {}     
   
   public final native Object getItem(String name) /*-{ return this[name]; }-*/;
   public final native Object getItem() /*-{ return this['item']; }-*/;
   public final native Object getItemProperty(String property) /*-{ return this['item']['property']; }-*/;
   public final native Object getItemProperty(String itemName, String property) /*-{ return this[itemName]['property']; }-*/;
   public final native int getItemPropertyAsInt(String property) /*-{ return this['item']['property']; }-*/;
   public final native int getItemPropertyAsInt(String itemName, String property) /*-{ return this[itemName]['property']; }-*/;
   public final native String getItemPropertyAsString(String property) /*-{ return this['item']['property']; }-*/;
   public final native String getItemPropertyAsString(String itemName, String property) /*-{ return this[itemName]['property']; }-*/;
   public final native int getIndex() /*-{ return this.index; }-*/;
}
