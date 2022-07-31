package com.kenzie.orders;


import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.kenzie.orders.resources.CreditProcessor;
import com.kenzie.orders.resources.CustomerManager;
import com.kenzie.orders.resources.InventoryManager;
import com.kenzie.orders.resources.Order;

/**
 * Class representing final state of the coding activity.
 */
public class OrderProcessor {

    private CustomerManager customerManager = new CustomerManager();
    private InventoryManager inventoryManager = new InventoryManager();
    private CreditProcessor creditProcessor;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a OrderProcessor object.
     *
     * @param metricsPublisher Used to publish metrics to CloudWatch.
     */
    public OrderProcessor(MetricsPublisher metricsPublisher, CreditProcessor creditProcessor, InventoryManager inventoryManager, CustomerManager customerManager) {
        this.metricsPublisher = metricsPublisher;
        this.creditProcessor = creditProcessor;
    }

    /**
     * Processes an order and payment.
     *
     * @param newOrder The order to be processed
     */
    public void processOrder(Order newOrder) {
        double startTime = System.currentTimeMillis();

        try {
            customerManager.verifyCustomerInfo(newOrder);
            int pickListNumber = inventoryManager.createPickList(newOrder);
            creditProcessor.processPayment(newOrder);
            inventoryManager.processPickList(pickListNumber);
            metricsPublisher.addMetric("ORDER_TOTALS", newOrder.getTotalPrice(), StandardUnit.None);
        } catch (Exception e) {
            System.out.println("Error processing order " + newOrder.getOrderId());
            metricsPublisher.addMetric("ORDER_FAILURES", 1, StandardUnit.Count);
        }
        metricsPublisher.addMetric("ORDER_FAILURES", 0, StandardUnit.Count);
        double endTime = System.currentTimeMillis();
        double timeInMilliseconds = endTime - startTime;
        metricsPublisher.addMetric("ORDER_PROCESSING_TIMES", timeInMilliseconds, StandardUnit.Milliseconds);
    }
}
