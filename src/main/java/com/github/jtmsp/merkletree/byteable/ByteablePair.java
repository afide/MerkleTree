/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2016 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.jtmsp.merkletree.byteable;

import java.io.UnsupportedEncodingException;
import java.util.AbstractMap.SimpleEntry;

/**
 * A Byteable that holds a name/value pair
 *
 * @author wolfposd
 */
public class ByteablePair implements IByteable {

    private final byte[] bytes;
    public SimpleEntry<String, String> pair;

    public ByteablePair(String s) {
        this.bytes = s.getBytes();
        this.pair = getPair(s);
    }

    public ByteablePair(byte[] bytes) {
        this.bytes = bytes;
        String s = null;
        try {
            s = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.pair = getPair(s);
    }

    private SimpleEntry<String, String> getPair(String s) {
        String[] parts = s.split("=");
        if (parts.length == 2) {
            return new SimpleEntry<>(parts[0], parts[1]);
        } else {
            return new SimpleEntry<>(s, s);
        }
    }

    @Override public byte[] toByteArray() {
        return bytes;
    }

    @Override public String toPrettyString() {
        return pair.getKey() + '=' + pair.getValue();
    }

    @Override public int compareTo(IByteable other) {
        if (other instanceof ByteablePair) {
            return pair.getKey().compareTo(((ByteablePair) other).pair.getKey());
        }
        return -1;
    }
}