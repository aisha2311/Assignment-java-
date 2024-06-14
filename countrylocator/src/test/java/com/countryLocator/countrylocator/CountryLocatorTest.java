package com.countryLocator.countrylocator;

import org.junit.Test;

import com.countryLocator.CountryLocator;

import static org.junit.Assert.assertEquals;

public class CountryLocatorTest {
    @Test
    public void testGetCountryCode() {
        CountryLocator locator = new CountryLocator();
        assertEquals("IN", locator.getCountryCode(20.5937, 73.1947));
        assertEquals("US", locator.getCountryCode(37.7749, -122.4194));
        assertEquals(null, locator.getCountryCode(-50, -50));
    }
}
