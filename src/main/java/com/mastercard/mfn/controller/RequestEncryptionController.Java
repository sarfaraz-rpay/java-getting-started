package com.mastercard.mfn.controller;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.mfn.service.PayloadEncryptionDecryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.security.cert.CertificateException;

@RestController
@RequestMapping('/api')
public class RequestEncryptionController {

    @Autowired
    private PayloadEncryptionDecryptionService payloadEncryptionDecryptionService;

    // @PostMapping(value = "/api/encrypt", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/encrypt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public String encryptPayload(@RequestBody String payloadToEncrypt) throws CertificateException, EncryptionException, FileNotFoundException {
        return payloadEncryptionDecryptionService.encrypt(payloadToEncrypt);
    }

}