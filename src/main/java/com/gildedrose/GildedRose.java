package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			updateItem(items[i]);
		}
	}

	private void updateItem(Item item) {
		if (item.name.startsWith("Aged Brie")) {
			updateAgedBrieItem(item);
		} else if (item.name.startsWith("Sulfuras")) {
			updateSulfurasItem(item);
		} else if (item.name.startsWith("Backstage pass")) {
			updateBackstagePassItem(item);
		} else {
			updateGenericItem(item);
		}
	}

	private void updateAgedBrieItem(Item item) {
		increaseQualityIfUnderLimit(item);
		decreaseSellIn(item);
		if (item.sellIn < 0) {
			increaseQualityIfUnderLimit(item);
		}
	}

	private void updateSulfurasItem(Item item) {
		// Do nothing!
	}

	private void updateBackstagePassItem(Item item) {
		increaseQualityIfUnderLimit(item);
		if (item.sellIn < 11) {
			increaseQualityIfUnderLimit(item);
		}

		if (item.sellIn < 6) {
			increaseQualityIfUnderLimit(item);
		}
		decreaseSellIn(item);
		if (item.sellIn < 0) {
			item.quality = 0;
		}

	}

	private void updateGenericItem(Item item) {
		decreaseQualityIfAboveZero(item);
		decreaseSellIn(item);

		if (item.sellIn < 0) {
			decreaseQualityIfAboveZero(item);
		}
	}

	private void decreaseQualityIfAboveZero(Item item) {
		if (item.quality > 0) {
			item.quality = item.quality - 1;
		}
	}

	private void decreaseSellIn(Item item) {
		item.sellIn = item.sellIn - 1;
	}

	private void increaseQualityIfUnderLimit(Item item) {
		if (item.quality < 50) {
			item.quality = item.quality + 1;
		}
	}
}