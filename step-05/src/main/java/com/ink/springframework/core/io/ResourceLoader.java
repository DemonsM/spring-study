package com.ink.springframework.core.io;

public interface ResourceLoader {
    /**
     * Pseudo URL prefixes for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
