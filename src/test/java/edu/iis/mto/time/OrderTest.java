package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Order order;

    @Before
    public void setUp() {
        this.order = new Order();
        this.order.addItem(new OrderItem());
        this.order.submit();
    }

    @Test(expected = OrderExpiredException.class)
    public void testIfOrderExpiredExceptionThrown() {
        this.order.confirm(DateTime.now().plusHours(30));
    }
}