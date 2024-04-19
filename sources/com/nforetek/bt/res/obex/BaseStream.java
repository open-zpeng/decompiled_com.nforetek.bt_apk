package com.nforetek.bt.res.obex;

import java.io.IOException;
/* loaded from: classes.dex */
public interface BaseStream {
    boolean continueOperation(boolean z, boolean z2) throws IOException;

    void ensureNotDone() throws IOException;

    void ensureOpen() throws IOException;

    void streamClosed(boolean z) throws IOException;
}
