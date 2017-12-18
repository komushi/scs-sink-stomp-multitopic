package info.cloudnative.scs.sink.stomp.multitopic;

public class StompPayload {

    public StompPayload(Object payload) {

        this.payload = payload;
    }

    private final Object payload;

    public Object getPayload() {
        return payload;
    }

    public String getPayloadString() {
        return payload.toString();
    }

}
