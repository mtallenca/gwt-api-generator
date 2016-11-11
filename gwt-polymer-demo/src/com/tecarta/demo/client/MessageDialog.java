package com.tecarta.demo.client;

import com.google.gwt.core.shared.GWT;
import com.vaadin.polymer.DomModule;
import com.vaadin.polymer.elemental.Event;
import com.vaadin.polymer.paper.PaperDialogElement;

import jsinterop.annotations.JsMethod;

public class MessageDialog extends DomModule {
    String message;
    PaperDialogElement dialog;

    public MessageDialog(String message) {
        this.message = message;
        load("message-dialog");
    }

    @JsMethod
    void _close(Event e) {
        dialog.close();
        DomModule.detach(this);
    }

    native void lockScroll(PaperDialogElement dialog) /*-{
    	if (dialog.opened) {
        	$wnd.Polymer.IronDropdownScrollManager.pushScrollLock(dialog);
	    } else {
	        $wnd.Polymer.IronDropdownScrollManager.removeScrollLock(dialog);
	    }
	}-*/;

    @JsMethod
    void _dialog_open_toggle(Event e) {
        if (dialog != null) {
            lockScroll(dialog);
        }
    }

    @Override
    protected void domModuleReady() {
        GWT.log("dialog ready");
        domModuleElement.set("message", message);
        dialog = (PaperDialogElement) $("dialog");
        dialog.open();
    }
}
