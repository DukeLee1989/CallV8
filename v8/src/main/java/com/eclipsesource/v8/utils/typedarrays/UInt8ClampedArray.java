/*******************************************************************************
 * Copyright (c) 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.v8.utils.typedarrays;

import com.eclipsesource.v8.V8Value;

import java.nio.ByteBuffer;

/**
 * The Uint8ClampedArray typed array represents an array of 8-bit unsigned
 * integers clamped to 0-255; if you specified a value that is out of the
 * range of [0,255], 0 or 255 will be set instead.
 */
public class UInt8ClampedArray extends TypedArray {

    /**
     * Creates an UInt8ClampedArray projected onto the given ByteBuffer.
     *
     * @param buffer The ByteBuffer on which the array is projected on.
     */
    public UInt8ClampedArray(final ByteBuffer buffer) {
        super(buffer);
    }

    /**
     * Creates a UInt8ClampedArray projected onto the given ArrayBuffer.
     *
     * @param arrayBuffer The ArrayBuffer on which the array is projected on.
     */
    public UInt8ClampedArray(final ArrayBuffer arrayBuffer) {
        this(arrayBuffer.getByteBuffer());
    }

    /**
     * Returns the 8-bit unsigned integer at the given index.
     *
     * @param index The index at which to return the value.
     * @return The 8-bit unsigned integer at the index.
     */
    public short get(final int index) {
        return (short) (0xFF & buffer.get(index));
    }

    /*
     * (non-Javadoc)
     * @see com.eclipsesource.v8.utils.typedarrays.TypedArray#length()
     */
    @Override
    public int length() {
        return buffer.limit();
    }

    /**
     * Puts a 8-bit unsigned integer at a particular index. If the unsigned
     * integer is outside the range [0,255], 0 or 255 will be used instead.
     *
     * @param index The index at which to place the value.
     * @param value The 8-bit unsigned integer to put into buffer.
     */
    public void put(final int index, final short value) {
        if (value > 255) {
            buffer.put(index, (byte) (255));
        } else if (value < 0) {
            buffer.put(index, (byte) (0));
        } else {
            buffer.put(index, (byte) (value));
        }
    }

    /*
     * (non-Javadoc)
     * @see com.eclipsesource.v8.utils.typedarrays.TypedArray#getType()
     */
    @Override
    public int getType() {
        return V8Value.UNSIGNED_INT_8_CLAMPED_ARRAY;
    }

}
