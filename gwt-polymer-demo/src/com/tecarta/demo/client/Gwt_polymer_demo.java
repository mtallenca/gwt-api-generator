package com.tecarta.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.vaadin.polymer.DomModule;

public class Gwt_polymer_demo implements EntryPoint {
	Timer loading;
	int count = 0;
	final int COUNT_INTERVAL = 100;
	final int ONE_SECOND = 1000;
	final int MAX_WAIT = 5000;
	
	public void onModuleLoad() {
		loading = new Timer() {
			public void run() {
				if (polymerLoaded()) {
					loadingComplete();

					// init any global objects here
					
					// attach "home" page
					DomModule.attach(Document.get().getBody(), new IronListCards(), false);
				}
				else if (count == MAX_WAIT) {
					loadingComplete();
					GWT.log("unable to load :(");
					
					// DomModule.attach(Document.get().getBody(), new OldBrowser());
				}
				else {
					count += COUNT_INTERVAL;
					if (count % ONE_SECOND == 0 && count > ONE_SECOND) {
						Element text = Document.get().getElementById("loading_text");
						if (text.getInnerText().length() == 0) {
							text.setInnerText("loading");
						}

						Element dots = Document.get().getElementById("dots");
						if (dots != null) {
							dots.setInnerText((dots.getInnerText().length() < 5) ? dots.getInnerText() + "." : "");
						}
					}
				}
			}
		};

		loading.scheduleRepeating(COUNT_INTERVAL);
	}
	
	private void loadingComplete() {
		// kill the timer
		loading.cancel();

		// remove the loading element from the dom
		Document.get().getElementById("loading").setInnerHTML("");
	}

	private static native boolean polymerLoaded() /*-{
		if ($wnd.Polymer && $wnd.customDomModules) {
	      	return true;
		}
	    else {
	    	return false;
	    }
	}-*/;
}
