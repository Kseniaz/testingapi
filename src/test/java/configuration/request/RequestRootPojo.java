package configuration.request;

import configuration.request.RequestGoodsPojo;

import java.util.ArrayList;

public class RequestRootPojo {
    public String externalId;
    public String orderCurrency;
    public double transportationPrice;
    public String originCountry;
    public String destinationCountry;
    public ArrayList<RequestGoodsPojo> goods;
}
