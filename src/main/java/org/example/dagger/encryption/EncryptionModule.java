package org.example.dagger.encryption;

import dagger.Module;
import dagger.Provides;
import org.example.encryption.Encryption;

@Module
public class EncryptionModule {

    @Provides
    public Encryption provideEncryption(){
        return new Encryption();
    }
}
