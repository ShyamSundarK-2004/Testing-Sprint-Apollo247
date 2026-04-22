package com.apollo247.testing.utilities;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class ExtentCucumberListener implements ConcurrentEventListener {

	@Override
	public void setEventPublisher(EventPublisher publisher) {

		publisher.registerHandlerFor(TestStepStarted.class, this::handleStepStarted);
	}

	private void handleStepStarted(TestStepStarted event) {

		if (event.getTestStep() instanceof PickleStepTestStep step) {

			String stepText = step.getStep().getText();
			String keyword = step.getStep().getKeyword();

			// STEP NAME
			ExtendsReportsUtilities.createStep("<b>" + keyword + "</b> " + stepText);
		}
	}
}