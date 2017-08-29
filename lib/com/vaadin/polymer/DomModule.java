package com.vaadin.polymer;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.Timer;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.elemental.HTMLElement;

import jsinterop.annotations.JsMethod;

public abstract class DomModule {
	protected HTMLElement domModuleElement;

	public DomModule() {
	}

	protected void create(String tagName) {
        create(tagName, true);
    }

	protected void create(String tagName, boolean createNow) {
		saveObjectReference(tagName);
	    this.domModuleElement = (createNow) ? 
            (HTMLElement) Document.get().createElement(tagName) : null;
	}

	protected native void saveObjectReference(String tagName) /*-{
		if (!$wnd.customDomModules) {
			$wnd.customDomModules = {};
		}

		$wnd.customDomModules[tagName] = this;
	}-*/;

	protected HTMLElement $(String id) {
		return $(domModuleElement, id);
	}

	native HTMLElement $(HTMLElement element, String id) /*-{
		return element.$[id];
	}-*/; 

	@JsMethod
	native void domModuleCreated(HTMLElement e, String tagName) /*-{
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

		if ($wnd.customDomModules) {
			delete $wnd.customDomModules[tagName];
		}
	}-*/;

    @JsMethod
    void ready(HTMLElement element) {
        // elements created as "shadow" i.e. used in another component need init here
        if (this.domModuleElement == null) {
		    this.domModuleElement = element;
        }

        domModuleReady();
    }

	protected void domModuleReady() {
	}

	public HTMLElement getDomModuleElement() {
		return domModuleElement;
	}

    public Timer async(Runnable runnable, int delayMillis) {
        Timer t = new Timer() {
            public void run() {
                runnable.run();
            }
        };

        t.schedule(delayMillis);

        return t;
    }

    public void cancelAsync(Timer timer) {
        if (timer != null) {
            timer.cancel();
        }
    }

	public void setHidden(Boolean hidden) {
		domModuleElement.setHidden(hidden);
	}	

	public static void init(String importsFile) {
	    init(GWT.getModuleBaseURL(), importsFile);
	}
	
	private static native void init(String moduleBaseURL, String importsFile) /*-{
      (function() {
          if ('registerElement' in $doc
             && 'HTMLImports' in $wnd
             && 'import' in $doc.createElement('link')
             && 'content' in $doc.createElement('template')) {
             // platform is good!
          } else {
             // polyfill the platform!
             var e = $doc.createElement('script');
             e.src = moduleBaseURL + 'bower_components/webcomponentsjs/webcomponents-lite.js';
             $doc.body.appendChild(e);
          }
          
          if (importsFile) {
              var l = $doc.createElement('link');
              l.rel = 'import';
              l.href = importsFile;
              $doc.head.appendChild(l);
          }
        })();
    }-*/;
	   
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
		attach(Document.get().getBody(), customElement, null, false);
	}
	
	public static void attach(DomModule customElement, boolean clearFirst) {
		attach(Document.get().getBody(), customElement, null, clearFirst);
	}

	public static void attach(Element parent, DomModule customElement) {
		attach(parent, customElement, null, false);
	}

	public static void attach(Element parent, DomModule customElement, boolean clearFirst) {
		attach(parent, customElement, null, clearFirst);
	}

	public static void attachBefore(Element parent, DomModule customElement, DomModule insertBeforeDomModule) {
        Element insertBefore = null;
        if (insertBeforeDomModule != null) {
		    insertBefore = (Element)insertBeforeDomModule.getDomModuleElement();
        }
		attachBefore(parent, customElement, insertBefore);
	}

	public static void attachBefore(Element parent, DomModule customElement, Element insertBefore) {
        attach(parent, customElement, insertBefore, false);
    }
	
	public static void attachAfter(Element parent, DomModule customElement, DomModule insertAfterDomModule) {
        Element insertAfter = null;
        if (insertAfterDomModule != null) {
            insertAfter = (Element)insertAfterDomModule.getDomModuleElement();
        }
        attachAfter(parent, customElement, insertAfter);
	}

	public static void attachAfter(Element parent, DomModule customElement, Element insertAfter) {
	    Element insertBefore = null;
	    if (insertAfter != null) {
	        insertBefore = insertAfter.getNextSiblingElement();
	    }
        attach(parent, customElement, insertBefore, false);
    }
	
	private static void attach(Element parent, DomModule customElement, Element insertBefore, boolean clearFirst) {
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
		
        if (insertBefore != null) {
            parent.insertBefore(e, insertBefore);
        }
        else {
		    parent.appendChild(e);
        }
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
