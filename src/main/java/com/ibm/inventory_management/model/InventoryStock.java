package com.ibm.inventory_management.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InventoryStock {
    private Integer count;

    public InventoryStock() {
        super();
    }

    public InventoryStock(String count) {
        this(Integer.valueOf(count));
    }

    public InventoryStock(Integer count) {
        this.count = count;
    }
}
