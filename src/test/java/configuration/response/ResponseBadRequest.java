package configuration.response;

import java.util.ArrayList;

public class ResponseBadRequest{
    public String type;
    public ArrayList<Row> rows;

    public static class Row {
        public String field;
        public String reason;
        public String message;
    }

    public ResponseBadRequest(String type, ArrayList<Row> rows) {
        this.type = type;
        this.rows = rows;
    }

    public ResponseBadRequest() {
    }

    public String getType() {
        return type;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }
}

