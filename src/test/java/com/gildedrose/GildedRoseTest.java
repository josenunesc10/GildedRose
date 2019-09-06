package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    
    @Test
    public void testGenericItemNormalDegradation() {
        Item[] items = new Item[] { new Item("generic item", 20, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // At the end of each day our system lowers both values (quality and sell in) for every item
        assertEquals(19, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
    }

    @Test
    public void testGenericItemJustBeforeSellInDegradation() {
        Item[] items = new Item[] { new Item("generic item", 1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        // Once the sell by date has passed, Quality degrades twice as fast
        assertEquals(49, app.items[0].quality);
    }
    
    @Test
    public void testGenericItemOnSellInDegradation() {
        Item[] items = new Item[] { new Item("generic item", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        // Once the sell by date has passed, Quality degrades twice as fast
        assertEquals(48, app.items[0].quality);
    }
    
    @Test
    public void testGenericItemAfterSellInDegradation() {
        Item[] items = new Item[] { new Item("generic item", -10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-11, app.items[0].sellIn);
        // Once the sell by date has passed, Quality degrades twice as fast
        assertEquals(48, app.items[0].quality);
    }
    
    @Test
    public void testGenericItemLimitDegradation() {
        Item[] items = new Item[] { new Item("generic item", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        // The Quality of an item is never negative
        assertEquals(0, app.items[0].quality);
    }
}
