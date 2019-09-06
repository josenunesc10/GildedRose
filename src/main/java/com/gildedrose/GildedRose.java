package com.gildedrose;

import com.gildedrose.handler.ItemHandler;
import com.gildedrose.handler.ItemHandlerFactory;

class GildedRose {
	Item[] items;
	private ItemHandlerFactory factory;

	public GildedRose(Item[] items) {
		this.items = items;
		this.factory = new ItemHandlerFactory();
	}

	public void updateQuality() {
		for (Item item : items) {
			ItemHandler handler = factory.getItemHandler(item);
			handler.updateItem(item);
		}
	}

}