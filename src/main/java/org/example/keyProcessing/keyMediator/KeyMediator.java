package org.example.keyProcessing.keyMediator;

import org.example.keyProcessing.keyHistoryManager.KeyHistoryManager;
import org.example.keyProcessing.keyWriter.KeyWriter;
import org.example.keyProcessing.macroMatcher.MacroMatcher;
import org.example.models.Macro;
import org.example.repos.MacroRepo;
import org.jnativehook.keyboard.NativeKeyEvent;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.util.List;

public class KeyMediator implements IKeyMediator {

    private KeyWriter keyWriter;
    private MacroRepo macroRepo;
    private KeyHistoryManager keyHistoryManager;
    private MacroMatcher macroMatcher;

    @Inject
    public KeyMediator(KeyWriter keyWriter, MacroRepo macroRepo, KeyHistoryManager keyHistoryManager, MacroMatcher macroMatcher){
        this.keyWriter = keyWriter;
        this.macroRepo = macroRepo;
        this.keyHistoryManager = keyHistoryManager;
        this.macroMatcher = macroMatcher;
        this.macroMatcher.setMacros(macroRepo.loadMacros());
    }

    @Override
    public void handleKeyClick(NativeKeyEvent nativeKeyEvent) {
        char keyChar = nativeKeyEvent.getKeyChar();
        if (keyChar == KeyEvent.VK_BACK_SPACE){
            keyHistoryManager.undoKeyHistory();
            return;
        }
        keyHistoryManager.updateKeyHistory(keyChar);
        Macro macroTarget = macroMatcher.matchMacroTrigger(keyHistoryManager.getKeyHistory());
        if (macroTarget != null){
            keyWriter.write(macroTarget);
            keyHistoryManager.clearKeyHistory();
        }
    }
}
