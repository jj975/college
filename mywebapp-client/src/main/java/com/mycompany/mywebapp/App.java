package com.mycompany.mywebapp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;




/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send to server");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		//task5.3
		//sendButton.addStyleName("sendButton");
		sendButton.addStyleName("sendButton bg-green-500 px-2 py-2 rounded-lg text-white ml-4");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);


		//task5.5
		SimplePanel headerPanel = new SimplePanel();
		headerPanel.addStyleName("sticky top-0 h-20 bg-[#243c5a] p-5 sm:px-24 xl:px-64 flex justify-between items-center");
		RootPanel.get("header").add(headerPanel);
		Label siteTitle = new Label("Start Tailwind");
		siteTitle.addStyleName("uppercase text-white text-xl xl:text-3xl font-bold whitespace-no-wrap font-Montserrat leading-normal tracking-tighter");
		headerPanel.add(siteTitle);

		//task 5.6
		//label form
		final Label formLabel = new Label("Subscribe");
		formLabel.addStyleName("text-center uppercase text-black text-4xl font-bold  leading-none tracking-normal");
		RootPanel.get("formLabel").add(formLabel);
		//form 
		final FormPanel subscriptionForm = new FormPanel();
		subscriptionForm.addStyleName("px-6 pb-12 max-w-2xl mx-auto");
		subscriptionForm.setEncoding(FormPanel.ENCODING_MULTIPART);
        subscriptionForm.setMethod(FormPanel.METHOD_POST);

		//email
		final FlowPanel emailPanel = new FlowPanel();
		emailPanel.addStyleName("flex items-center border-b border-b-2 border-gray-400 py-10");
		final TextBox emailField = new TextBox();
		emailField.setName("email");
		emailField.getElement().setAttribute("type", "email");
		emailField.getElement().setAttribute("aria-label", "Email Address");
		emailField.getElement().setAttribute("placeholder", "Email Address");
		emailField.addStyleName("appearance-none bg-transparent border-none w-full placeholder-gray-700 mr-3 py-1 leading-tight text-2xl focus:outline-none");
		emailPanel.add(emailField);

		//massege
		final FlowPanel messagePanel = new FlowPanel();
		messagePanel.addStyleName("flex items-center border-b border-b-2 border-gray-400 py-10");
		final TextArea messageField = new TextArea();
		messageField.setName("message");
		messageField.getElement().setAttribute("rows", "5");
		messageField.getElement().setAttribute("placeholder", "Message");
		messageField.addStyleName("appearance-none border-none w-full placeholder-gray-700 mr-3 py-1 leading-tight text-2xl focus:outline-none");
		messagePanel.add(messageField);

		//button
		final FlowPanel subscriptionButtonPanel = new FlowPanel();
		subscriptionButtonPanel.addStyleName("py-5");
		final Button subscriptionButton = new Button("Subscribe");
		subscriptionButton.getElement().setAttribute("type", "submit");
		subscriptionButton.addStyleName("bg-green-500 px-8 py-5 rounded-lg text-white");
		subscriptionButtonPanel.add(subscriptionButton);
		
		//all form content
		final FlowPanel formContentPanel = new FlowPanel();
		formContentPanel.add(emailPanel);
		formContentPanel.add(messagePanel);
		formContentPanel.add(subscriptionButtonPanel);
		subscriptionForm.add(formContentPanel);

		//add to form
		RootPanel.get("subscriptionForm").add(subscriptionForm);


		//first popup
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		//task5.4
		dialogBox.addStyleName("bg-cyan-500 px-2 py-2 rounded-lg text-white");
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<GreetingResponse>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(GreetingResponse result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(new SafeHtmlBuilder()
										.appendEscaped(result.getGreeting())
										.appendHtmlConstant("<br><br>I am running ")
										.appendEscaped(result.getServerInfo())
										.appendHtmlConstant(".<br><br>It looks like you are using:<br>")
										.appendEscaped(result.getUserAgent())
										.toSafeHtml());
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);


		/////////////////////
		//* Create the popup dialog box
		final DialogBox dialogBoxSubscription = new DialogBox();
		dialogBoxSubscription.setText("Status subscription");
		dialogBoxSubscription.setAnimationEnabled(true);
		//task5.4
		dialogBoxSubscription.addStyleName("bg-green-500 px-2 py-2 rounded-lg text-white");
		final Button closeButtonSubscription = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButtonSubscription.getElement().setId("closeButtonSubscription");
		final Label emailToServerLabel = new Label();
		final Label massegeToServerLabel = new Label();
		final HTML serverResponseLabelSubscription = new HTML();
		VerticalPanel dialogVPanelSubscription = new VerticalPanel();
		dialogVPanelSubscription.addStyleName("dialogVPanelSubscription");
		dialogVPanelSubscription.add(new HTML("<b>Sending subscription to the server:</b>"));
		dialogVPanelSubscription.add(emailToServerLabel);
		dialogVPanelSubscription.add(massegeToServerLabel);
		dialogVPanelSubscription.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanelSubscription.add(serverResponseLabelSubscription);
		dialogVPanelSubscription.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanelSubscription.add(closeButtonSubscription);
		dialogBoxSubscription.setWidget(dialogVPanelSubscription); //*/


		class SubscriptionButtonHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the subscriptionButton.
			 */
			public void onClick(ClickEvent event) {
				sendSubscriptionToServer();
			}
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendSubscriptionToServer();
				}
			}
			private void sendSubscriptionToServer() {
				errorLabel.setText("");
				String emailToServer = emailField.getText();
				String massegeToServer = messageField.getText();
				//*
				if (!FieldVerifier.isValidName(emailToServer) && !FieldVerifier.isValidName(massegeToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}
				//*/
				subscriptionButton.setEnabled(false);
				emailToServerLabel.setText(emailToServer);
				massegeToServerLabel.setText(massegeToServer);
				serverResponseLabelSubscription.setText("");
				/*
				serverResponseLabelSubscription.setHTML(new SafeHtmlBuilder()
								.appendHtmlConstant("<br><br>I register subscribe for you ")
								.appendEscaped(emailToServer)
								.appendHtmlConstant(".<br><br>And you left a message:<br>")
								.appendEscaped(massegeToServer)
								.toSafeHtml());
				dialogBoxSubscription.center();
				closeButtonSubscription.setFocus(true);
								//*/
				//*
				greetingService.greetServer(emailToServer,
						new AsyncCallback<GreetingResponse>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBoxSubscription
										.setText("Remote Procedure Call - Failure");
								serverResponseLabelSubscription
										.addStyleName("serverResponseLabelSubscriptionError");
								serverResponseLabelSubscription.setHTML(SERVER_ERROR);
								dialogBoxSubscription.center();
								closeButtonSubscription.setFocus(true);
							}

							public void onSuccess(GreetingResponse result) {
								dialogBoxSubscription.setText("Remote Procedure Call");
								serverResponseLabelSubscription.removeStyleName("serverResponseLabelSubscriptionError");
								serverResponseLabelSubscription.setHTML(new SafeHtmlBuilder()
								.appendEscaped(result.getGreeting())
								.appendHtmlConstant("<br><br>I register subscribe for you ")
								.appendEscaped(emailToServer)
								.appendHtmlConstant(".<br><br>And you left a message:<br>")
								.appendEscaped(massegeToServer)
								.toSafeHtml());
								dialogBoxSubscription.center();
								closeButtonSubscription.setFocus(true);
							}
						});
				//*/

			}

		}//*/

		SubscriptionButtonHandler handler2 = new SubscriptionButtonHandler();
		subscriptionButton.addClickHandler(handler2);
		messageField.addKeyUpHandler(handler2);
		closeButtonSubscription.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBoxSubscription.hide();
				subscriptionButton.setEnabled(true);
				subscriptionButton.setFocus(true);
			}
		});
		//task7.3//////////////////
		Frame weatherFrame = new Frame("https://www.meteoblue.com/en/weather/widget/daily/rivne_ukraine_695594?geoloc=fixed&days=7&tempunit=CELSIUS&windunit=KILOMETER_PER_HOUR&precipunit=MILLIMETER&coloured=monochrome&pictoicon=0&pictoicon=1&maxtemperature=0&maxtemperature=1&mintemperature=0&mintemperature=1&windspeed=0&windspeed=1&windgust=0&windgust=1&winddirection=0&winddirection=1&uv=0&uv=1&humidity=0&humidity=1&precipitation=0&precipitation=1&precipitationprobability=0&precipitationprobability=1&spot=0&spot=1&pressure=0&pressure=1&layout=dark");
		final Button getWeatherFrameButton = new Button("Get weather frame");
		getWeatherFrameButton.addStyleName("bg-green-500 px-2 py-2 rounded-lg text-white ml-4");
		weatherFrame.addStyleName("bg-cyan-500 px-2 py-2 rounded-lg text-white");
		RootPanel.get("weatherButton").add(getWeatherFrameButton);
		
		class GetWeatherFrameButtonHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				showWeatherFrame();
			}
			private void showWeatherFrame() {
				getWeatherFrameButton.setEnabled(false);
				RootPanel.get("weatherButton").clear();
				RootPanel.get("weatherContainer").add(weatherFrame);
			}

		}
		GetWeatherFrameButtonHandler getWeatherFrameButtonHandler = new GetWeatherFrameButtonHandler();
		getWeatherFrameButton.addClickHandler(getWeatherFrameButtonHandler);


	}	

}
