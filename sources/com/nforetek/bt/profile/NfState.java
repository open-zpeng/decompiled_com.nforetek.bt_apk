package com.nforetek.bt.profile;
/* loaded from: classes.dex */
public class NfState {
    private int mState = 110;
    private int mPrevState = 110;

    public NfState(int state, int prevState) {
        setState(state);
        if (prevState != -1) {
            setPrevState(prevState);
        }
    }

    public int getState() {
        return this.mState;
    }

    public synchronized void setState(int state) {
        this.mState = state;
    }

    public int getPrevState() {
        return this.mPrevState;
    }

    public synchronized void setPrevState(int state) {
        this.mPrevState = state;
    }
}
