package com.ink.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.ink.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author MT
 * @date 2021/8/5 10:53
 */
public class ClassPathResource implements Resource {
    private final String path;

    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null!");
        this.path = path;
        this.classLoader = Objects.nonNull(classLoader) ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if (Objects.isNull(resourceAsStream)) {
            throw new FileNotFoundException(path + " cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }
}
