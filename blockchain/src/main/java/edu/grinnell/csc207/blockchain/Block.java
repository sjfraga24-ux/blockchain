package edu.grinnell.csc207.blockchain;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


/**
 * A single block of a blockchain.
 */
public class Block {

    private Hash curHash;
    private int amount;
    private int num;
    private Hash prevHash;
    private long nonce;
    private ArrayList<Long> takenNonce;
    public int bob_Amt;
    public int anna_Amt;

    /**
     * Creates a new block from the specified parameters, performing the mining operation 
     * to discover the nonce and hash for this block given these parameters.
     * @param num : An integer that represents the block's place in the blockchain
     * @param amount : The data/amount transferred between the two parties represented as a single integer
     * @param prevHash : The hash of the previous block in the chain.
     */
    public Block(int num, int amount, Hash prevHash){
        this.amount = amount;
        this.num = num;
        this.prevHash = prevHash;
        nonce = 0;

        do{
            ByteBuffer temp = null;
            temp.putLong(nonce);
            curHash = new Hash(temp.array());
            nonce++;
        }while(!curHash.isValid() || indexOf(takenNonce, nonce) == -1);

        takenNonce.add(nonce);

        if(amount >= 0){
            anna_Amt+= amount;
            bob_Amt -= amount;
        } else if(amount < 0){
            anna_Amt+= amount;
            bob_Amt += Math.abs(amount);
        }
    }

    /**
     * Checks the indexes of an ArrayList<Long> for a specific value.
     * @param arr : the ArrayList<Long>
     * @param lng : The value being searched for.
     * @returns the index the value was at if found in arraylist, else returns -1.
     */
    private int indexOf(ArrayList<Long> arr, long lng) {
        for(int i = 0; i < arr.size(); i++){
            if((arr.get(i)) == lng){
                return i;
            }
        }
        return -1;
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
            anna_Amt+= amount;
        } else if(amount < 0){
            bob_Amt += Math.abs(amount);
        }
    }

    /**
     * @returns the block number in the block chain.
     */
    public int getNum(){
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
    public long getNonce(){
        return nonce;
    }

    /**
     * @returns the previous blocks hash.
     */
    public Hash getPrevHash(){
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
        String ret = "Block" + num + "(Amount: " + amount + ", " + 
                                        "Nonce: " + nonce + "," + 
                                        "prevHash: " + prevHash + ", " + 
                                        "hash: "+ curHash + ")";
        return ret;
    }

}
