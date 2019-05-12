package com.example.mymealproject;

import com.example.mymealproject.sqlDatabase.Order;

import java.util.List;

public class OrderRequest {
    private String tableNo;
    private String total;
    private List<Order> orderList; // list of dishes ordering

    public OrderRequest() {
    }

    public OrderRequest(String tableNo, String total, List<Order> orderList) {
        this.tableNo = tableNo;
        this.total = total;
        this.orderList = orderList;
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
}

