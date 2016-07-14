package br.com.example.domain.vo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhoneTest {
    
    @Test
    public void testOf() {
        assertEquals(Phone.of("554788226100"), Phone.of("+55 47 8822-6100"));
        assertEquals(Phone.of("5547988226100"), Phone.of("+55 47 98822-6100"));
    }

    @Test
    public void testGetCountryCode() {
        assertEquals("55", Phone.of("554788226100").getCountryCode());
        assertEquals("55", Phone.of("5547988226100").getCountryCode());
    }

    @Test
    public void testGetAreaCode() {
        assertEquals("47", Phone.of("554788226100").getAreaCode());
        assertEquals("47", Phone.of("5547988226100").getAreaCode());
    }

    @Test
    public void testGetNumber() {
        assertEquals("88226100", Phone.of("554788226100").getNumber());
        assertEquals("988226100", Phone.of("5547988226100").getNumber());
    }

    @Test
    public void testToString() {
        assertEquals("+55 47 8822-6100", Phone.of("554788226100").toString());
        assertEquals("+55 47 98822-6100", Phone.of("5547988226100").toString());
    }

}
