package com.ink.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author MT
 * @date 2021/8/5 10:51
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
