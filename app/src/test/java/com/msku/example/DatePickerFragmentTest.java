package com.msku.example;

import junit.framework.TestCase;

import org.junit.Test;

public class DatePickerFragmentTest extends TestCase {
@Test
    public void testOnCreateDialog() {
    DatePickerFragment fragment = new DatePickerFragment();
    assertNotNull(fragment);
    }
}