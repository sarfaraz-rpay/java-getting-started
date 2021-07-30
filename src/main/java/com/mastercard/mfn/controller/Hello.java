package com.mastercard.mfn.controller;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.mfn.service.PayloadEncryptionDecryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
@RestController
public class Hello {
    @Autowired
    private PayloadEncryptionDecryptionService payloadEncryptionDecryptionService;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String encryptPayload() throws CertificateException, EncryptionException, FileNotFoundException {
        return "Hello world";
    }
    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    public String encryptPayload(@RequestBody String payloadToEncrypt) throws CertificateException, EncryptionException, FileNotFoundException {
        return payloadEncryptionDecryptionService.encrypt(payloadToEncrypt);
    }
}