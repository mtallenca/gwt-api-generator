package com.tecarta.demo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.vaadin.polymer.DomModule;
import com.vaadin.polymer.elemental.Event;
import com.vaadin.polymer.iron.IronListElement;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

public class IronListCards extends DomModule {
    IronListElement list;

    @JsType(isNative = true)
    public interface ListItem {
        @JsProperty String getName();
        @JsProperty String getState();
    }

    public IronListCards() {
        load("iron-list-cards");
    }

    @Override
    protected void domModuleReady() {
        GWT.log("i'm loaded");
        list = (IronListElement) $("list");
        getJSON("data/contacts.json", new ListCallback());
    }

    @JsMethod
    void _onSearch(Event e) {
        DomModule.attach(new MessageDialog("Search not implemented :("));
    }

    @JsMethod
    void _onItemTap(Event e) {
        ListItem item = (ListItem)e.getModel().getItem();
        DomModule.attach(new MessageDialog(item.getName() + " lives in " + item.getState()));
    }

    public static Request getJSON(String url, RequestCallback cb) {
        Request request = null;

        try {
            RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);
            rb.setHeader("Accept", "application/json");			
            rb.setCallback(cb);
            request = rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
        }

        return request;
    }

    class ListCallback implements RequestCallback {
        @Override
        public void onResponseReceived(Request request, Response response) {			
            if (response.getStatusCode() == 200) {
                JsArray<?> items = JsonUtils.safeEval(response.getText());
                list.setItems(items);
                domModuleElement.set("numItemLabel", items.length() + " items in list");
            }
        }

        @Override
        public void onError(Request request, Throwable exception) {
            // MessageDialog.show("Error getting your licenses [" + exception.getMessage() + "] :(");
        }
    }
}
