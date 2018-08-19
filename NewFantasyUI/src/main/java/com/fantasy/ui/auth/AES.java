package com.fantasy.ui.auth;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class AES {

	private static final Logger log = LoggerFactory.getLogger(AES.class);

	@Value("${encryption.key}")
	private String encryptionKey;

	private SecretKey secretKey;
	
	private IvParameterSpec ivspec;

	public AES() {
		this.secretKey = buildKey();
		this.ivspec = buildIvParameterSpec();
	}

	private SecretKey buildKey() {
		try {
			return KeyGenerator.getInstance("AES").generateKey();
		} catch (Exception e) {
			log.error("ERROR :: trying to generate AES key", e);
			return null;
		}
	}
	
	private IvParameterSpec buildIvParameterSpec() {
		byte[] iv = new byte[128 / 8];
		new SecureRandom().nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		return ivspec;
	}

	public String encrypt(String plainText) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
		} catch (Exception e) {
			log.error("ERROR :: trying to encrypt: " + plainText, e);
		}
		return null;
	}

	public String decrypt(String encryptedText) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
		} catch (Exception e) {
			log.error("ERROR :: trying to decrypt: " + encryptedText, e);
		}
		return null;
	}
}
