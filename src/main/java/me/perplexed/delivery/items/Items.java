package me.perplexed.delivery.items;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items {

    private final static Items inst = new Items();

    public final Item DISCOUNT_TURKEY = new DiscountTurkey();
    public final Item CRANBERRY = new Cranberry();
    public final Item CRANBERRY_JUICE = new CranberryJuice();

    private final List<Item> items = Arrays.asList(DISCOUNT_TURKEY,CRANBERRY,CRANBERRY_JUICE);

    public List<Item> getItems() {
        return items;
    }

    public static Items getInstance() {
        return inst;
    }
}
