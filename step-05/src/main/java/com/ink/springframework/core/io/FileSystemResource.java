package com.ink.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author MT
 * @date 2021/8/5 11:11
 */
public class FileSystemResource implements Resource {
    private final String path;
    private final File file;

    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public final String getPath() {
        return path;
    }
}
