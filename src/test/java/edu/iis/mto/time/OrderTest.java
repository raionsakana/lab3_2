package edu.iis.mto.time;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Order order;

    @Before
    public void setUp() {
        this.order = new Order();
    }

    @Test(expected = OrderExpiredException.class)
    public void testIfOrderExpiredExceptionThrown() {
        this.order.addItem(new OrderItem());
        this.order.submit();
        this.order.confirm(DateTime.now().plusHours(30));
    }

    @Test
    public void testIfOrderStateIsCreated() {
        assertThat(Order.State.CREATED, is(this.order.getOrderState()));
    }

    @Test
    public void testIfOrderStateIsSubmitted() {
        this.order.addItem(new OrderItem());
        this.order.submit();
        assertThat(Order.State.SUBMITTED, is(this.order.getOrderState()));
    }

    @Test
    public void testIfOrderStateIsConfirmed() {
        this.order.addItem(new OrderItem());
        this.order.submit();
        this.order.confirm(DateTime.now());
        assertThat(Order.State.CONFIRMED, is(this.order.getOrderState()));
    }

}