package com.zakcorp.questions.cache_design;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CacheTest {
    private Cache<Integer, String> cache;

    @Before
    public void setup() {
        cache = new CacheFactory<Integer, String>().defaultCache(3);
    }

    @Test
    public void itShouldBeAbleToGetAndAddItemsInTheCache() {
        cache.put(1, "Zakir");
        cache.put(2, "Atheeq");

        assertEquals("Zakir", cache.get(1)); // Accessing 1 after 2 got inserted which makes 2 the least recently used till now.
        cache.put(3, "Ariv");
        assertEquals("Ariv", cache.get(3));

        // Now if i try to add any element, the eviction should happen
        // Also eviction should happen based on LeastRecentlyUsedItem
        // which is 2 in this case.
        cache.put(4, "Fauz");

        cache.get(2); // This should throw exception "Tried to access non-existing key."
    }
}
