package configuration.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class ResponseNotFound {
    public String entity;
    public String field;
    public String value;

    @JsonProperty("timestamp")
    private Instant timeStamp;
}
