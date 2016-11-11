package com.vaadin.polymer;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.vaadin.polymer.elemental.HTMLElement;

import jsinterop.annotations.JsMethod;

public abstract class DomModule {
	protected HTMLElement domModuleElement;

	public DomModule() {
	}

	protected void load(String tagName) {
		saveInitData(tagName);
		this.domModuleElement = (HTMLElement) Document.get().createElement(tagName);
		clearInitData(tagName);
		domModuleReady();		
	}

	native void saveInitData(String tagName) /*-{
		if (!$wnd.customDomModules) {
			$wnd.customDomModules = {};
		}

		$wnd.customDomModules[tagName] = this;
	}-*/;

	native void clearInitData(String tagName) /*-{
		if ($wnd.customDomModules) {
			delete $wnd.customDomModules[tagName];
		}
	}-*/;

	protected HTMLElement $(String id) {
		return $(domModuleElement, id);
	}

	native HTMLElement $(HTMLElement element, String id) /*-{
		return element.$[id];
	}-*/; 

	@JsMethod
	native void domModuleCreated(HTMLElement e) /*-{
		var gwtObject = this;

		var makeSafe = function(fn) {
  			return function() {
      			return fn.apply(gwtObject, arguments);
  			};
		};

		// assign JsMethods beginning with '_' to the custom dom-module
		Object.keys(this.__proto__).forEach(function(key,index) {
			if (key[0] == '_' && key[key.length - 1] != '$') {
				e[key.substr(1)] = makeSafe(gwtObject[key]);
			}
		});
	}-*/;

	protected void domModuleReady() {
	}

	public Object getDomModuleElement() {
		return domModuleElement;
	}

	public void setHidden(Boolean hidden) {
		domModuleElement.setHidden(hidden);
	}	

	/**
	 * Box a native JS array in a Java List. It does not have any performance
	 * penalty because we directly set the native array of the super ArrayList
	 * implementation.
	 */
	public native <T> List<T> asList(JavaScriptObject o) /*-{
        var l = @java.util.ArrayList::new()();
        l.@java.util.ArrayList::array = o;
        return l;
    }-*/;

	/**
	 * UnBox the native JS array in a Java List. It does have a performance
	 * penalty because Polymer Templates don't like the additional attributes 
	 * that come with GWT ArrayList -> Array
	 */
	public native <T extends JavaScriptObject> JsArray<T> asJsArray(List<?> l) /*-{
        javaArray = l.@java.util.ArrayList::array;
        jsArray = [];
        
        for (i = 0; i < javaArray.length; i++) {
        	jsArray.push(javaArray[i]);
        }
        
        return jsArray;
    }-*/;
	
	public static void attach(DomModule customElement) {
		attach(Document.get().getBody(), customElement, false);
	}
	
	public static void attach(DomModule customElement, boolean clearFirst) {
		attach(Document.get().getBody(), customElement, clearFirst);
	}

	public static void attach(Element container, DomModule customElement) {
		attach(container, customElement, false);
	}
	
	public static void attach(Element parent, DomModule customElement, boolean clearFirst) {
		HTMLElement e = (HTMLElement)customElement.getDomModuleElement();
		
		if (e == null) {
			return;
		}
		
		// save pointer to class with dom object
		e.setGwtObject(customElement);
		
		if (clearFirst) {
			// remove all children in the container
			NodeList<Node> nodes = parent.getChildNodes();
			for (int i = nodes.getLength() - 1; i >= 0; i--) {
				Element node = (Element) nodes.getItem(i);
				DomModule childDomModule = (DomModule)((HTMLElement)node).getGwtObject();
				if (childDomModule != null) {
					detach(childDomModule);
				}
				else {
					parent.removeChild(node);
				}
			}
		}
		
		parent.appendChild(e);
	}
	
	public static void detach(DomModule customElement) {
		HTMLElement e = (HTMLElement)customElement.getDomModuleElement();
		
		if (e == null) {
			return;
		}
		
		// clear saved class pointer 
		e.setGwtObject(null);
		
		// remove this element from the dom
		if (e.getParentNode() != null) {
			e.getParentNode().removeChild(e);
		}
	}
}
