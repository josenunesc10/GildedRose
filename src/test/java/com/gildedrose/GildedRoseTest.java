package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

	private Item createGenericItem(int sellIn, int quality) {
		return new Item("generic item", sellIn, quality);
	}

	private Item createAgedBrieItem(int sellIn, int quality) {
		return new Item("Aged Brie", sellIn, quality);
	}
	
	private Item createSulfurasItem(int sellIn, int quality) {
		return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
	}

	@Test
	public void foo() {
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
	}

	@Test
	public void testGenericItemNormalDegradation() {
		Item[] items = new Item[] { createGenericItem(20, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// At the end of each day our system lowers both values (quality and sell in)
		// for every item
		assertEquals(19, app.items[0].sellIn);
		assertEquals(49, app.items[0].quality);
	}

	@Test
	public void testGenericItemJustBeforeSellInDegradation() {
		Item[] items = new Item[] { createGenericItem(1, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// At the end of each day our system lowers both values (quality and sell in)
		// for every item
		assertEquals(0, app.items[0].sellIn);
		assertEquals(49, app.items[0].quality);
	}

	@Test
	public void testGenericItemOnSellInDegradation() {
		Item[] items = new Item[] { createGenericItem(0, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items[0].sellIn);
		// Once the sell by date has passed, Quality degrades twice as fast
		assertEquals(48, app.items[0].quality);
	}

	@Test
	public void testGenericItemAfterSellInDegradation() {
		Item[] items = new Item[] { createGenericItem(-10, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-11, app.items[0].sellIn);
		// Once the sell by date has passed, Quality degrades twice as fast
		assertEquals(48, app.items[0].quality);
	}

	@Test
	public void testGenericItemLimitDegradation() {
		Item[] items = new Item[] { createGenericItem(0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items[0].sellIn);
		// The Quality of an item is never negative
		assertEquals(0, app.items[0].quality);
	}

	@Test
	public void testAgedBrieNormalDegradation() {
		Item[] items = new Item[] { createAgedBrieItem(20, 10) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Aged Brie" actually increases in Quality the older it gets
		assertEquals(19, app.items[0].sellIn);
		assertEquals(11, app.items[0].quality);
	}

	@Test
	public void testAgedBrieJustBeforeSellInDegradation() {
		Item[] items = new Item[] { createAgedBrieItem(1, 10) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Aged Brie" actually increases in Quality the older it gets
		assertEquals(0, app.items[0].sellIn);
		assertEquals(11, app.items[0].quality);
	}

	@Test
	public void testAgedBrieOnSellInDegradation() {
		Item[] items = new Item[] { createAgedBrieItem(0, 10) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items[0].sellIn);
		// Once the sell by date has passed, Quality degrades twice as fast
		// "Aged Brie" actually increases in Quality the older it gets
		assertEquals(12, app.items[0].quality);
	}

	@Test
	public void testAgedBrieAfterSellInDegradation() {
		Item[] items = new Item[] { createAgedBrieItem(-10, 10) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-11, app.items[0].sellIn);
		// Once the sell by date has passed, Quality degrades twice as fast
		// "Aged Brie" actually increases in Quality the older it gets
		assertEquals(12, app.items[0].quality);
	}

	@Test
	public void testAgedBrieLimitDegradation() {
		Item[] items = new Item[] { createAgedBrieItem(0, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items[0].sellIn);
		// The Quality of an item is never more than 50
		assertEquals(50, app.items[0].quality);
	}
	
	@Test
	public void testSulfurasNoDegradationOnSellIn() {
		Item[] items = new Item[] { createSulfurasItem(0, 80) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
		assertEquals(0, app.items[0].sellIn);
		assertEquals(80, app.items[0].quality);
	}
	
	@Test
	public void testSulfurasNoDegradationOrSellInChange() {
		Item[] items = new Item[] { createSulfurasItem(10, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
		assertEquals(10, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}
}
