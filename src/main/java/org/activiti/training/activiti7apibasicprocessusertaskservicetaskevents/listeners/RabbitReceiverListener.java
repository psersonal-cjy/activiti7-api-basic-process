package org.activiti.training.activiti7apibasicprocessusertaskservicetaskevents.listeners;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "hello")
@Component
public class RabbitReceiverListener {

    private Logger logger = LoggerFactory.getLogger(RabbitReceiverListener.class);

    @Autowired
    private RuntimeService runtimeService;
    @RabbitHandler
    public void handler(String message) {

        ExecutionQuery executionQuery = runtimeService.createExecutionQuery().messageEventSubscriptionName("MSG_协助处理");
        Execution execution = executionQuery.singleResult();

        runtimeService.messageEventReceived(message, execution.getId());
        logger.info(">>> MSG_协助处理-messageEventReceived: ");
        logger.info(">>> Received Message is : " + message);
    }
}
