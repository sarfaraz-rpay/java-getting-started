package com.mastercard.mfn.service;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryption;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import com.mastercard.developer.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;


@Slf4j
@Service
public class PayloadEncryptionDecryptionService {

    @Value("${encryption.certificate}") // path to encryptionCertificate (.pem) file
    private File certificatePath;

    public String encrypt(String payload) throws CertificateException, FileNotFoundException, EncryptionException {
        Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(certificatePath.getPath());

        FieldLevelEncryptionConfig config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
                .withEncryptionCertificate(encryptionCertificate)
                .withEncryptionPath("$", "$")
                .withOaepPaddingDigestAlgorithm("SHA-256")
                .withEncryptedValueFieldName("encryptedValue")
                .withEncryptedKeyFieldName("encryptedKey")
                .withEncryptionKeyFingerprintFieldName("publicKeyFingerprint")
                .withIvFieldName("iv")
                .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.BASE64)
                .build();

        return FieldLevelEncryption.encryptPayload(payload, config);
    }

 }