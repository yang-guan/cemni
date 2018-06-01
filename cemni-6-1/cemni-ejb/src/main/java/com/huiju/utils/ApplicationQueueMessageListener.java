package com.huiju.utils;

import static com.huiju.module.message.jms.ApplicationMessageConstants.*;

import java.util.Arrays;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;

import com.huiju.module.message.MessageResolver;
import com.huiju.module.message.jms.AbstractJmsMessageListener;

/**
 * @author wuxii@foxmail.com
 */
@MessageDriven(mappedName = QUEUE_NAME, activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class ApplicationQueueMessageListener extends AbstractJmsMessageListener implements MessageListener {

	@EJB(mappedName = "ApplicationNotifyMessageResolver")
	private MessageResolver notifyMessageResolver;

	@Override
	public void init() {
	}

	@Override
	protected List<MessageResolver> getMessageResolvers() {
		return Arrays.asList(notifyMessageResolver);
	}

	@Override
	public void destroy() {
	}
}
