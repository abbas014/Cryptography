package com.syed.keypair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateKeys {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {

        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
    }

    public void createKeys() {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();

    }

    public PrivateKey getPrivateKey() {
    	System.out.println("\n private key "+this.privateKey);
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
    	System.out.println("\n public key "+this.publicKey);
        return this.publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
    	System.out.println("\n writing it to file key ");
        File f = new File(path);
        System.out.println("\n writing it to file key "+f);
        f.getParentFile().mkdirs();
        System.out.println("\n writing it to file key "+f.getParentFile().mkdirs());

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
        GenerateKeys myKeys = new GenerateKeys(2048);
        myKeys.createKeys();
        System.out.println("Private Key using Base64 encoding to String "+Base64.getMimeEncoder().encodeToString(myKeys.getPrivateKey().getEncoded()));
        System.out.println("Public Key using Base64 encoding to String "+Base64.getMimeEncoder().encodeToString(myKeys.getPublicKey().getEncoded()));
        myKeys.writeToFile("MySecureKeys/publicKey", Base64.getMimeEncoder().encode(myKeys.getPublicKey().getEncoded()));
        myKeys.writeToFile("MySecureKeys/privateKey", Base64.getMimeEncoder().encode(myKeys.getPrivateKey().getEncoded()));
    }
}