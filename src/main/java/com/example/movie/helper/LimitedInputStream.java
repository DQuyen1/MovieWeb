package com.example.movie.helper;

import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends InputStream {
    private final InputStream source;
    private long remaining;

    public LimitedInputStream(InputStream source, long limit) {
        this.source = source;
        this.remaining = limit;
    }

    @Override
    public int read() throws IOException {
        if (remaining <= 0) return -1;
        int data = source.read();
        if (data != -1) remaining--;
        return data;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (remaining <= 0) return -1;
        len = (int) Math.min(len, remaining);
        int bytesRead = source.read(b, off, len);
        if (bytesRead > 0) remaining -= bytesRead;
        return bytesRead;
    }

    @Override
    public void close() throws IOException {
        source.close();
    }
}

