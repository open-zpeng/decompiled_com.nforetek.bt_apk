package com.nforetek.bt.profile.map.java;

import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes.dex */
public class NfMapCommandProcessor {
    private String TAG = "NfMapCommandProcessor";
    private byte[] lock = new byte[0];
    private BlockingQueue<NfMapCommand> mCommandQueue = new LinkedBlockingQueue();
    private HashSet<String> mSendMessageSet = new HashSet<>();
    private HashSet<String> mDeleteMessageSet = new HashSet<>();
    private HashSet<String> mDownloadSingleSet = new HashSet<>();
    private HashSet<String> mDownloadAllSet = new HashSet<>();
    private HashSet<String> mReadStatusSet = new HashSet<>();

    public void removeDevice(String address) {
        NfLog.v(this.TAG, "removeDevice() " + address);
        synchronized (this.lock) {
            dumpCommandQueue();
            Iterator<NfMapCommand> iterator = this.mCommandQueue.iterator();
            while (iterator.hasNext()) {
                NfMapCommand cmd = iterator.next();
                if (cmd.getAddress().equals(address)) {
                    switch (cmd.getType()) {
                        case 0:
                            this.mSendMessageSet.remove(cmd.toString());
                            break;
                        case 1:
                            this.mDeleteMessageSet.remove(cmd.toString());
                            break;
                        case 2:
                            this.mDownloadSingleSet.remove(cmd.toString());
                            break;
                        case 3:
                            this.mDownloadAllSet.remove(cmd.toString());
                            break;
                        case 4:
                            this.mReadStatusSet.remove(cmd.toString());
                            break;
                    }
                    iterator.remove();
                }
            }
            dumpCommandQueue();
        }
    }

    public void reset() {
        NfLog.v(this.TAG, "reset()");
        synchronized (this.lock) {
            if (this.mCommandQueue != null) {
                this.mCommandQueue.clear();
            }
            if (this.mSendMessageSet != null) {
                this.mSendMessageSet.clear();
            }
            if (this.mDeleteMessageSet != null) {
                this.mDeleteMessageSet.clear();
            }
            if (this.mDownloadSingleSet != null) {
                this.mDownloadSingleSet.clear();
            }
            if (this.mDownloadAllSet != null) {
                this.mDownloadAllSet.clear();
            }
            if (this.mReadStatusSet != null) {
                this.mReadStatusSet.clear();
            }
        }
    }

    public void release() {
        NfLog.v(this.TAG, "release()");
        reset();
        if (this.mCommandQueue != null) {
            this.mCommandQueue = null;
        }
        if (this.mSendMessageSet != null) {
            this.mSendMessageSet = null;
        }
        if (this.mDeleteMessageSet != null) {
            this.mDeleteMessageSet = null;
        }
        if (this.mDownloadSingleSet != null) {
            this.mDownloadSingleSet = null;
        }
        if (this.mDownloadAllSet != null) {
            this.mDownloadAllSet = null;
        }
        if (this.mReadStatusSet != null) {
            this.mReadStatusSet = null;
        }
    }

    public boolean isCommandInQueue(NfMapCommand cmd) {
        NfLog.d(this.TAG, "isCommandInQueue() " + cmd);
        boolean result = false;
        switch (cmd.getType()) {
            case 0:
                result = this.mSendMessageSet.contains(cmd.toString());
                break;
            case 1:
                result = this.mDeleteMessageSet.contains(cmd.toString());
                break;
            case 2:
                result = this.mDownloadSingleSet.contains(cmd.toString());
                break;
            case 3:
                result = this.mDownloadAllSet.contains(cmd.toString());
                break;
            case 4:
                result = this.mReadStatusSet.contains(cmd.toString());
                break;
        }
        NfLog.d(this.TAG, "isCommandInQueue: " + result);
        return result;
    }

    public boolean add(NfMapCommand cmd) {
        NfLog.d(this.TAG, "add() " + cmd);
        boolean result = false;
        synchronized (this.lock) {
            switch (cmd.getType()) {
                case 0:
                    result = this.mSendMessageSet.add(cmd.toString());
                    break;
                case 1:
                    result = this.mDeleteMessageSet.add(cmd.toString());
                    break;
                case 2:
                    result = this.mDownloadSingleSet.add(cmd.toString());
                    break;
                case 3:
                    result = this.mDownloadAllSet.add(cmd.toString());
                    break;
                case 4:
                    result = this.mReadStatusSet.add(cmd.toString());
                    break;
            }
            if (result) {
                this.mCommandQueue.add(cmd);
            }
            dumpCommandQueue();
            NfLog.d(this.TAG, "add: " + result);
        }
        return result;
    }

    public NfMapCommand poll() {
        NfLog.v(this.TAG, "poll()");
        synchronized (this.lock) {
            NfMapCommand cmd = this.mCommandQueue.poll();
            dumpCommandQueue();
            if (cmd == null) {
                NfLog.d(this.TAG, "poll cmd null");
                return null;
            }
            NfLog.d(this.TAG, cmd.toString());
            switch (cmd.getType()) {
                case 0:
                    this.mSendMessageSet.remove(cmd.toString());
                    break;
                case 1:
                    this.mDeleteMessageSet.remove(cmd.toString());
                    break;
                case 2:
                    this.mDownloadSingleSet.remove(cmd.toString());
                    break;
                case 3:
                    this.mDownloadAllSet.remove(cmd.toString());
                    break;
                case 4:
                    this.mReadStatusSet.remove(cmd.toString());
                    break;
            }
            return cmd;
        }
    }

    public boolean isCommandQueueEmpty() {
        return this.mCommandQueue.isEmpty();
    }

    public int getCommandQueueSize() {
        return this.mCommandQueue.size();
    }

    private void dumpCommandQueue() {
        NfLog.d(this.TAG, "S====dumpCommandQueue====S");
        ArrayList<NfMapCommand> list = new ArrayList<>(this.mCommandQueue);
        Iterator<NfMapCommand> it = list.iterator();
        while (it.hasNext()) {
            NfLog.v(this.TAG, "cmd: " + it.next());
        }
        NfLog.d(this.TAG, "E====dumpCommandQueue====E");
    }
}
