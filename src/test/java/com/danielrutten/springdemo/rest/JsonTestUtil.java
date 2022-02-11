package com.danielrutten.springdemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test utility to transform a given object to JSON.
 */
public class JsonTestUtil {

    public static String toJson(final Object json) {
        try {
            return new ObjectMapper().writeValueAsString(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
