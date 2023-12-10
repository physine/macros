package org.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.example.dagger.keyReader.DaggerKeyReaderComponent;
import org.example.dagger.keyReader.KeyReaderComponent;
import org.example.dagger.macroRoutes.DaggerMacroRoutesComponent;
import org.example.dagger.macroRoutes.MacroRoutesComponent;
import org.example.keyProcessing.keyReader.KeyReader;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Log log = LogFactory.getLog(App.class);
    public static void main( String[] args ) {
        configLogger();
        startApplication();
    }

    private static void startApplication() {
        KeyReaderComponent KeyReaderComponent = DaggerKeyReaderComponent.create();
        KeyReader keyReader = KeyReaderComponent.buildKeyReader();
        keyReader.startReading();

        MacroRoutesComponent macroRoutesComponent = DaggerMacroRoutesComponent.create();
        macroRoutesComponent.buildMacroRoutes();
    }

    private static void configLogger() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.SEVERE);
    }
}
