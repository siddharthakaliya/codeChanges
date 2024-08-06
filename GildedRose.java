public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras")) continue;

            if (item.name.equals("Aged Brie")) {
                updateAgedBrie(item);
            } else if (item.name.equals("Backstage passes")) {
                updateBackstagePasses(item);
            } else if (item.name.contains("Conjured")) {
                updateConjured(item);
            } else {
                updateNormalItem(item);
            }

            if (item.sellIn < 0) {
                handleExpiredItem(item);
            }
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item);
        item.sellIn--;
    }

    private void updateBackstagePasses(Item item) {
        increaseQuality(item);
        if (item.sellIn <= 10) increaseQuality(item);
        if (item.sellIn <= 5) increaseQuality(item);
        item.sellIn--;
    }

    private void updateConjured(Item item) {
        decreaseQuality(item, 2);
        item.sellIn--;
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item, 1);
        item.sellIn--;
    }

    private void handleExpiredItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes")) {
            item.quality = 0;
        } else if (item.name.contains("Conjured")) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item, int amount) {
        if (item.quality > 0) {
            item.quality = Math.max(0, item.quality - amount);
        }
    }
}
