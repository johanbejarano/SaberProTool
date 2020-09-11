package com.vortexbird.sapiens.utility;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordGenerator {

	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

	public static Optional<String> hashPassword(String password) {

		char[] chars = password.toCharArray();
		byte[] bytes = Constantes.salt.getBytes();

		PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

		Arrays.fill(chars, Character.MIN_VALUE);

		try {
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			return Optional.of(Base64.getEncoder().encodeToString(securePassword));

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			System.err.println("Exception encountered in hashPassword()");
			return Optional.empty();

		} finally {
			spec.clearPassword();
		}
	}

	public static String NUMEROS = "0123456789";

	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	public static String ESPECIALES = "ñÑ+-*";

	//
	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}

	public static String getPassword() {
		return getPassword(8);
	}

	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}

	public static String getPassword(String key, int length) {
		String pswd = "";

		for (int i = 0; i < length; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}

		return pswd;
	}
}
