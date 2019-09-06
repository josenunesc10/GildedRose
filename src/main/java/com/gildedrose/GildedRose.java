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
		if (item.quality < 50) {
			item.quality = item.quality + 1;
		}
		item.sellIn = item.sellIn - 1;
		if (item.sellIn < 0) {
			if (item.quality < 50) {
				item.quality = item.quality + 1;
			}
		}
	}

	private void updateSulfurasItem(Item item) {
		// Do nothing!
	}

	private void updateBackstagePassItem(Item item) {
		if (item.quality < 50) {
			item.quality = item.quality + 1;

			if (item.sellIn < 11) {
				if (item.quality < 50) {
					item.quality = item.quality + 1;
				}
			}

			if (item.sellIn < 6) {
				if (item.quality < 50) {
					item.quality = item.quality + 1;
				}
			}
		}
		item.sellIn = item.sellIn - 1;
		if (item.sellIn < 0) {
			item.quality = item.quality - item.quality;
		}

	}

	private void updateGenericItem(Item item) {
		if (item.quality > 0) {
			item.quality = item.quality - 1;
		}
		item.sellIn = item.sellIn - 1;

		if (item.sellIn < 0) {
			if (item.quality > 0) {
				item.quality = item.quality - 1;
			}
		}
	}
}