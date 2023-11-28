package org.example.dagger.keyMediator;

import dagger.Module;
import dagger.Provides;
import org.example.keyProcessing.keyHistoryManager.KeyHistoryManager;
import org.example.keyProcessing.keyMediator.KeyMediator;
import org.example.keyProcessing.keyWriter.KeyWriter;
import org.example.keyProcessing.macroMatcher.MacroMatcher;
import org.example.repos.MacroRepo;

@Module
public class KeyMediatorModule {

    @Provides
    public KeyMediator provideKeyMediator(KeyWriter keyWriter,
                                          MacroRepo macroRepo,
                                          KeyHistoryManager keyHistoryManager,
                                          MacroMatcher macroMatcher){
        return new KeyMediator(keyWriter, macroRepo, keyHistoryManager, macroMatcher);
    }

    @Provides
    public KeyHistoryManager provideKeyHistoryManager(){
        return new KeyHistoryManager();
    }

    @Provides
    public MacroMatcher provideMacroMatcher(){
        return MacroMatcher.getInstance();
    }
}
