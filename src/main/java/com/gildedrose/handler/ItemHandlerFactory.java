package com.gildedrose.handler;

import com.gildedrose.Item;

public class ItemHandlerFactory {

	// Name prefix for the support types of items
	private String AGED_BRIE_TYPE = "Aged Brie";
	private String SULFURAS_TYPE = "Sulfuras";
	private String BACKSTAGE_PASS_TYPE = "Backstage pass";
	private String CONJURED_TYPE = "Conjured";

	public ItemHandler getItemHandler(Item item) {
		// TODO: Could do caching
		if (item.name.startsWith(AGED_BRIE_TYPE)) {
			return new AgedBrieItemHandler();
		}
		if (item.name.startsWith(SULFURAS_TYPE)) {
			return new SulfurasItemHandler();
		}
		if (item.name.startsWith(BACKSTAGE_PASS_TYPE)) {
			return new BackstagePassItemHandler();
		}
		if (item.name.startsWith(CONJURED_TYPE)) {
			return new ConjuredItemHandler();
		}
		return new GenericItemHandler();
	}
}
