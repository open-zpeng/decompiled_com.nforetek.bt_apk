package com.nforetek.bt.res.obex;

import java.io.IOException;
/* loaded from: classes.dex */
public interface SessionNotifier {
    ObexSession acceptAndOpen(ServerRequestHandler serverRequestHandler) throws IOException;

    ObexSession acceptAndOpen(ServerRequestHandler serverRequestHandler, Authenticator authenticator) throws IOException;
}
