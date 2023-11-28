package org.example.dagger.keyReader;

import dagger.Module;
import dagger.Provides;
import org.example.keyProcessing.keyMediator.KeyMediator;
import org.example.keyProcessing.keyReader.KeyReader;

@Module
public class KeyReaderModule {

    @Provides
    public KeyReader provideKeyMediator(KeyMediator keyMediator){
        return new KeyReader(keyMediator);
    }
}
