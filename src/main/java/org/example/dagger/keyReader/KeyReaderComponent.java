package org.example.dagger.keyReader;

import dagger.Component;
import org.example.dagger.keyMediator.KeyMediatorModule;
import org.example.keyProcessing.keyReader.KeyReader;

@Component(modules = {KeyReaderModule.class, KeyMediatorModule.class})
public interface KeyReaderComponent {
    KeyReader buildKeyReader();
}
