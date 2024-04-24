package com.mycompany.mywebapp;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ExchangeRequest {
    
    public static void getExchangeRates(final AsyncCallback<String> callback) {
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(exception);
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        callback.onSuccess(response.getText());
                    } else {
                        callback.onFailure(new Exception("Received HTTP status code other than 200"));
                    }
                }
            });
        } catch (RequestException e) {
            callback.onFailure(e);
        }
    }
}
