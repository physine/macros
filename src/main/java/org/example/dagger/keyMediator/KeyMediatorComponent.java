package org.example.dagger.keyMediator;

import dagger.Component;
import org.example.dagger.macroRepo.MacroRepoModule;
import org.example.keyProcessing.keyMediator.KeyMediator;

@Component(modules = {MacroRepoModule.class, KeyMediatorModule.class})
public interface KeyMediatorComponent {
    KeyMediator buildKeyMediator();
}
