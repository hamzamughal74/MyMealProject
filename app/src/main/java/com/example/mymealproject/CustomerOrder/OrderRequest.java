package com.example.mymealproject.CustomerOrder;

import com.example.mymealproject.sqlDatabase.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {
    private String tableNo;
    private String total;
    private List<Order> orderList;// list of dishes ordering
    private String restID;
    private String customerId;
    private String status;
    private String orderID;





    public OrderRequest() {
    }
    public OrderRequest(String status) {
        this.status = status;
    }

    public OrderRequest(String tableNo, String total, List<Order> orderList,String restID,String customerId,String orderID) {
        this.tableNo = tableNo;
        this.total = total;
        this.orderList = orderList;
        this.restID = restID;
        this.customerId = customerId;
        this.orderID = orderID;
        this.status = "0";


    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    public String getRestID() {
        return restID;
    }

    public void setRestID(String restID) {
        this.restID = restID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

