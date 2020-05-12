package edu.iis.mto.time;

import org.joda.time.DateTime;

public class FakeClock {

    private DateTime clock = new DateTime(0);

    public FakeClock() {}

    public void setClock(DateTime dateTime) {
        this.clock = dateTime;
    }

    public DateTime now() {
        return clock;
    }

}
