package org.example.keyProcessing.keyHistoryManager;

public interface IKeyHistoryManager {
    void updateKeyHistory(char keyChar);
    void undoKeyHistory();
    void clearKeyHistory();

    String getKeyHistory();
}
