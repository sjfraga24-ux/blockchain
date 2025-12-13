package edu.grinnell.csc207.blockchain;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;


/**
 * A single block of a blockchain.
 */
public class Block {

    private Hash curHash;
    private int amount;
    private static int num;
    private static Hash prevHash;
    private static long nonce;
    public int bob;
    public int anna;
    private int count = -1;
 

    public static byte[] calculateNonceHash(byte[] msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(msg);
        byte[] hash = md.digest();
        return hash;
    }

    public static byte[] calculateHash(ByteBuffer nonce, ByteBuffer num, ByteBuffer amt, Hash prev) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        if(prev == null){
            md.update(num);
            md.update(amt);
            md.update(nonce);
            byte[] hash = md.digest();
            return hash;
        }
        md.update(num);
        md.update(amt);
        md.update(prev.getData());
        md.update(nonce);
        byte[] hash = md.digest();
        return hash;
    }

    /**
     * Creates a new block from the specified parameters, performing the mining operation 
     * to discover the nonce and hash for this block given these parameters.
     * @param num : An integer that represents the block's place in the blockchain
     * @param amount : The data/amount transferred between the two parties represented as a single integer
     * @param prevHash : The hash of the previous block in the chain.
     */
    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException{
        this.amount = amount;
        this.num = num;
        this.prevHash = prevHash;
        nonce = -1;
        do{
            nonce++;
            ByteBuffer temp = ByteBuffer.allocate(64);
            temp.putLong(nonce);
            curHash = new Hash(calculateNonceHash(temp.array()));
            if(curHash.isValid()){
                count++;
            }
        }while(!curHash.isValid() || count != num);
        ByteBuffer temp2 = ByteBuffer.allocate(64);
        temp2.putLong(nonce);
        ByteBuffer temp3 = ByteBuffer.allocate(4);
        ByteBuffer temp4 = ByteBuffer.allocate(4);
        temp3.putInt(num);
        temp4.putInt(amount);
        curHash = new Hash(calculateHash(temp2, temp3, temp4, prevHash));
        

        if(amount >= 0){
            anna =+ amount;
            bob =- amount;
        } else if(amount < 0){
            anna =+ amount;
            bob =- amount;
        }
    }

    /**
     * Creates a new block from the specified parameters, 
     * using the provided nonce and additional parameters to generate the hash for the block.
     * @param num : An integer that represents the block's place in the blockchain
     * @param amount : The data transferred between the two parties represented as a single integer
     * @param prevHash : The hash of the previous block in the chain.
     * @param nonce : The nonce.
     */
    public Block(int num, int amount, Hash prevHash, long nonce){
        this.amount = amount;
        this.num = num;
        this.prevHash = prevHash;
        this.nonce = nonce;
        ByteBuffer temp = null;
        temp.putLong(nonce);
        curHash = new Hash(temp.array());

        if(amount >= 0){
            anna+= amount;
        } else if(amount < 0){
            bob += Math.abs(amount);
        }
    }

    /**
     * @returns the block number in the block chain.
     */
    public static int getNum(){
        return num;
    }

    /**
     * @returns the data/amount transferred between the two parties represented as a single integer
     */
    public int getAmount(){
        return amount;
    }

    /**
     * @returns the nonce of the block.
     */
    public static long getNonce(){
        return nonce;
    }

    /**
     * @returns the previous blocks hash.
     */
    public static Hash getPrevHash(){
        return prevHash;
    }

    /**
     * @returns the current block's hash.
     */
    public Hash getHash(){
        return curHash;
    }

    /**
     * @returns the elements contained in the block in String form.
     */
    public String toString(){
        String ret = "Block " + num + " (Amount: " + amount + ", " + 
                                        "Nonce: " + nonce + ", " + 
                                        "prevHash: " + prevHash + ", " + 
                                        "hash: "+ curHash + ")";
        return ret;
    }

}
