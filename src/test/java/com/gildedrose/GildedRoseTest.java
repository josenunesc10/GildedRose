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

	private Item createBackstagePassItem(int sellIn, int quality) {
		return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
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
		// "Sulfuras", being a legendary item, never has to be sold or decreases in
		// Quality
		assertEquals(0, app.items[0].sellIn);
		assertEquals(80, app.items[0].quality);
	}

	@Test
	public void testSulfurasNoDegradationOrSellInChange() {
		Item[] items = new Item[] { createSulfurasItem(10, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Sulfuras", being a legendary item, never has to be sold or decreases in
		// Quality
		assertEquals(10, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}

	@Test
	public void testBackstagePassMoreThan10DaysSellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(20, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Backstage passes", like aged brie, increases in Quality as its SellIn value
		// approaches;
		assertEquals(19, app.items[0].sellIn);
		assertEquals(21, app.items[0].quality);
	}

	@Test
	public void testBackstagePassOn11DaysSellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(11, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		// "Backstage passes", like aged brie, increases in Quality as its SellIn value
		// approaches;
		assertEquals(10, app.items[0].sellIn);
		assertEquals(21, app.items[0].quality);
	}

	@Test
	public void testBackstagePassOn10DaySellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(10, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items[0].sellIn);
		// Quality increases by 2 when there are 10 days or less and by 3 when there are
		// 5 days or less
		assertEquals(22, app.items[0].quality);
	}

	@Test
	public void testBackstagePassOn6DaysSellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(6, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(5, app.items[0].sellIn);
		// Quality increases by 2 when there are 10 days or less and by 3 when there are
		// 5 days or less
		assertEquals(22, app.items[0].quality);
	}

	@Test
	public void testBackstagePassOn5DaysSellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(5, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(4, app.items[0].sellIn);
		// Quality increases by 2 when there are 10 days or less and by 3 when there are
		// 5 days or less
		assertEquals(23, app.items[0].quality);
	}

	@Test
	public void testBackstagePassOn1DaySellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(1, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(0, app.items[0].sellIn);
		// Quality increases by 2 when there are 10 days or less and by 3 when there are
		// 5 days or less
		assertEquals(23, app.items[0].quality);
	}

	@Test
	public void testBackstagePassOnSellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(0, 20) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items[0].sellIn);
		// Quality drops to 0 after the concert
		assertEquals(0, app.items[0].quality);
	}

	@Test
	public void testBackstagePassAfterSellInDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(-10, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-11, app.items[0].sellIn);
		// Quality drops to 0 after the concert
		assertEquals(0, app.items[0].quality);
	}

	
	@Test
	public void testBackstagePassLimitDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(5, 49) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(4, app.items[0].sellIn);
		// an item can never have its Quality increase above 50
		assertEquals(50, app.items[0].quality);
	}
	
	@Test
	public void testBackstagePassAtMaxQualityLimitDegradation() {
		Item[] items = new Item[] { createBackstagePassItem(5, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(4, app.items[0].sellIn);
		// an item can never have its Quality increase above 50
		assertEquals(50, app.items[0].quality);
	}
}
