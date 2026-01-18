package pl.wsb.fitnesstracker.event;

import org.springframework.context.ApplicationEvent;

public class MySpringEvent extends ApplicationEvent {

    private String myMessage;

    public MySpringEvent(final Object source, String myMessage) {
        super(source);
        this.myMessage = myMessage;

    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(final String myMessage) {
        this.myMessage = myMessage;
    }
}
