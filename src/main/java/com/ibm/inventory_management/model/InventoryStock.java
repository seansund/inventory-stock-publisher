package com.ibm.inventory_management.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InventoryStock {
    private String id;
    private Integer count;

    public InventoryStock() {
        super();
    }

    public InventoryStock(String id, String count) {
        this(id, Integer.valueOf(count));
    }

    public InventoryStock(String id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
