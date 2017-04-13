package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;

public class HTMLElement extends Element {
    protected HTMLElement() {}

    /**
     * Polymer’s custom property shim evaluates and applies custom property values once at element creation time.
     * In order to have an element (and its subtree) re- evaluate custom property values due to dynamic changes
     * such as application of CSS classes, etc., call updateStyles().
     */
    public final native void updateStyles() /*-{ this.updateStyles(); }-*/;
    public final native void setGwtObject(Object value) /*-{ this.gwtObject = value; }-*/;
    public final native Object getGwtObject() /*-{ return this.gwtObject; }-*/;
    public final native void setHidden(boolean value) /*-{ this.hidden = value; }-*/;
    public final native boolean isHidden() /*-{ return this.hidden; }-*/;
    public final native void addEventListener(String event, EventListener<?> listener) /*-{ this.addEventListener(event, listener); }-*/; 
    public final native void removeEventListener(String event, EventListener<?> listener) /*-{ this.removeEventListener(event, listener); }-*/; 
    public final native DOMTokenList getClassList() /*-{ return this.classList; }-*/;
    public final native Node querySelector(String query) /*-{ return this.querySelector(query); }-*/;
    public final native NodeList querySelectorAll(String query) /*-{ return this.querySelectorAll(query); }-*/;
    public final native void fire(String eventName, Object detail) /*-{ this.fire(eventName, detail); }-*/;
    public final native void fire(String eventName) /*-{ this.fire(eventName); }-*/;

    public final native Object get(String propertyPath) /*-{ return this.get(propertyPath); }-*/;
    public final native <T extends JavaScriptObject> T getAsJavaScriptObject(String propertyPath) /*-{ return this.get(propertyPath); }-*/;
    public final native String getAsString(String propertyPath) /*-{ return "" + this.get(propertyPath); }-*/;
    public final native int getAsInt(String propertyPath) /*-{ return this.get(propertyPath); }-*/;
    public final native void set(String propertyPath, Object value) /*-{ this.set(propertyPath, value); }-*/;
    public final native void set(String propertyPath, int value) /*-{ this.set(propertyPath, value); }-*/;

    /**
     * Returns the first node in this element’s local DOM that matches selector.
     */
    public final native Element $$(String selector) /*-{ return this.$$(selector); }-*/;

    /**
     * Toggles the named boolean class on the node, adding the class if bool is
     * truthy and removing it if bool is falsey.
     */
    public final native void toggleClass(String name, boolean b, Element node) /*-{ this.toggleClass(name, b, node); }-*/;

    /**
     * Toggles the named boolean attribute on the node.
     */
    public final native void toggleAttribute(String name, boolean b, Element node) /*-{ this.toggleAttribute(name, b, node); }-*/;

    /**
     * Moves a boolean attribute from oldNode to newNode, unsetting the attribute
     * (if set) on oldNode and setting it on newNode
     */
    public final native void attributeFollows(String name, Element newNode, Element oldNode) /*-{ attributeFollows(name, newNode, oldNode); }-*/;

    /**
     *  Moves a class from oldNode to newNode, removing the class (if present)
     *  on oldNode and adding it to newNode.
     */
    public final native void classFollows(String name, Element newNode, Element oldNode) /*-{ this.classFollows(name, newNode, oldNode); }-*/;


    /**
     * Fires a custom event. The options object can contain the following properties:
     *    node: Node to fire the event on (defaults to this).
     *    bubbles: Whether the event should bubble. Defaults to true.
     *    cancelable: Whether the event can be canceled with preventDefault. Defaults to false.
     */
    public final native void fire(String type, Object detail, Object options) /*-{ fire(type, detail, options); }-*/;

    /**
     * Calls method asynchronously. If no wait time is specified, runs tasks with microtask
     * timing (after the current method finishes, but before the next event from the event
     * queue is processed). Returns a handle that can be used to cancel the task.
     */
    public final native Object async(Function method, int wait) /*-{ return this.async(method, wait); }-*/;

    /**
     * Cancels the identified async task.
     */
    public final native void cancelAsync(Object handle) /*-{ this.cancelAsync(handle); }-*/;

    /**
     *  Applies a CSS transform to the specified node, or host element if no node is
     *  specified. transform is specified as a string. For example:
     *    transform('rotateX(90deg)', elm);
     */
    public final native void transform(String transform, Element node) /*-{ this.transform(transform, node) }-*/;

    /**
     * Transforms the specified node, or host element if no node is specified. For example:
     *   translate3d('100px', '100px', '100px', elm);
     */
    public final native void translate3d(String x, String y, String z, Element node) /*-{ this.translate3d(x, y, z, node); }-*/;

    /**
     *  Dynamically imports an HTML document.
     */
    public final native void importHref(String href, Function onload, Function onerror) /*-{ this.importHref(href, onload, onerror); }-*/;

    /**
     * Takes a URL relative to the <dom-module> of an imported Polymer element, and returns
     * a path relative to the current document. This method can be used, for example,
     * to refer to an asset delivered alongside an HTML import.
     */
    public final native String resolveUrl(String url) /*-{ return this.resolveUrl(url); }-*/;


    public final native void appendChild(HTMLElement child) /*-{ this.appendChild(child); }-*/;
    public final native void insertBefore(HTMLElement child, HTMLElement existingChild) /*-{ this.insertBefore(child,existingChild); }-*/;
}
