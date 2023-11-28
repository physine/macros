package org.example.dagger.encryption;

import dagger.Component;
import org.example.encryption.Encryption;

@Component(modules = EncryptionModule.class)
public interface EncryptionComponent {
    Encryption buildKeyEncryption();
}
