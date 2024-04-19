package com.nforetek.bt.res.bt;

import android.os.Message;
/* loaded from: classes.dex */
public class State implements IState {
    @Override // com.nforetek.bt.res.bt.IState
    public void enter() {
    }

    @Override // com.nforetek.bt.res.bt.IState
    public void exit() {
    }

    @Override // com.nforetek.bt.res.bt.IState
    public boolean processMessage(Message msg) {
        return false;
    }

    @Override // com.nforetek.bt.res.bt.IState
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf(36);
        return name.substring(lastDollar + 1);
    }
}
