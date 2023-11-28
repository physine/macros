package org.example.keyProcessing.macroMatcher;

import org.example.models.Macro;

public interface IMacroMatcher {
    Macro matchMacroTrigger(String keyHistory);
}
