package org.example.keyProcessing.keyHistoryManager;

import javax.inject.Inject;

public class KeyHistoryManager implements IKeyHistoryManager{
    private String keyHistory;
    private int maxKeyHistoryLength;

    @Inject
    public KeyHistoryManager() {
        this.keyHistory = "";
        this.maxKeyHistoryLength = 100;
    }

    public void updateKeyHistory(char keyChar) {
        if(maxKeyHistoryLength < keyHistory.length())
            keyHistory = keyHistory.substring(1);
        keyHistory += keyChar;
    }

    public void undoKeyHistory() {
        if (keyHistory.length() > 0)
            keyHistory = keyHistory.substring(0, keyHistory.length() - 1);
    }

    public void clearKeyHistory() {
        keyHistory = "";
    }

    public String getKeyHistory() {
        return keyHistory;
    }
}
