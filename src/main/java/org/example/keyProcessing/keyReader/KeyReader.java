package org.example.keyProcessing.keyReader;

import org.example.keyProcessing.keyMediator.KeyMediator;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.inject.Inject;

public class KeyReader implements NativeKeyListener {

    private KeyMediator keyMediator;

    @Inject
    public KeyReader(KeyMediator keyMediator){
        this.keyMediator = keyMediator;
    }

    public void startReading() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        char keyChar = nativeKeyEvent.getKeyChar();
        if (isCharOfInterest(keyChar)){
            keyMediator.handleKeyClick(nativeKeyEvent);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }

    private boolean isCharOfInterest(char c) {
        return c == 8
                || (c >= 32 && c <= 47)
                || (c >= 58 && c <= 64)
                || (c >= 91 && c <= 96)
                || (c >= 123 && c <= 126)
                || Character.isLetterOrDigit(c);
    }
}
