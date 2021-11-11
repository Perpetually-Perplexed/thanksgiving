package me.perplexed.delivery.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items {

    private final static Items inst = new Items();

    public final Item DISCOUNT_TURKEY = new DiscountTurkey();

    private final List<Item> items = Arrays.asList(DISCOUNT_TURKEY);

    public List<Item> getItems() {
        return items;
    }

    public static Items getInstance() {
        return inst;
    }
}
