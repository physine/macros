package org.example.keyProcessing.keyWriter;

import org.example.models.Macro;

import javax.inject.Inject;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyWriter implements IKeyWriter {

    private Robot robot;

    @Inject
    public KeyWriter(){
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Macro macro) {
        backspace(macro.getTrigger().length());
        copyToClipboard(macro.getTarget());
        paste();
    }

    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void paste() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void backspace(int n) {
        for (int i = 0; i < n; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
      }
    }
}
