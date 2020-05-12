package edu.iis.mto.time;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Clock clock;
    private Order order;

    @Before
    public void setUp() {
        this.clock = new FakeClock();
        this.order = new Order(this.clock);
    }

    @Test(expected = OrderExpiredException.class)
    public void testIfOrderExpiredExceptionThrown() {
        this.clock.setClock(DateTime.now());

        this.order.addItem(new OrderItem());
        this.order.submit();

        this.clock.setClock(DateTime.now().plusHours(30));
        this.order.confirm();
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
        this.clock.setClock(DateTime.now());

        this.order.addItem(new OrderItem());
        this.order.submit();
        this.order.confirm();

        assertThat(Order.State.CONFIRMED, is(this.order.getOrderState()));
    }

    @Test(expected = OrderExpiredException.class)
    public void testIfOrderStateIsCancelled() {
        this.clock.setClock(DateTime.now());

        this.order.addItem(new OrderItem());
        this.order.submit();

        this.clock.setClock(DateTime.now().plusHours(30));
        this.order.confirm();

        assertThat(Order.State.CANCELLED, is(this.order.getOrderState()));
    }

    @Test
    public void testIfOrderStateIsRealized() {
        this.clock.setClock(DateTime.now());
        this.order.addItem(new OrderItem());

        this.order.submit();
        this.order.confirm();
        this.order.realize();

        assertThat(Order.State.REALIZED, is(this.order.getOrderState()));
    }

    @Test
    public void testIfOrderStateIsCreatedWithProduct() {
        this.order.addItem(new OrderItem());
        assertThat(Order.State.CREATED, is(this.order.getOrderState()));
    }

}