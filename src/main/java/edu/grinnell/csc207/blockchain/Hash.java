package edu.grinnell.csc207.blockchain;

import java.util.Arrays;



/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    private byte[] data;


    

    /**
     * Constructs a new Hash object that contains the given hash (as an array of bytes).
     * @param data : the given hash as an array of bytes.
     */
    public Hash(byte[] data){
        this.data = data;
    }

    public Hash starter;

    /**
     * returns the hash contained in this object.
     * @returns an array of bytes that represents a hash
     */
    public byte[] getData(){
        return data;
    }

    /**
     * @returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes.
     */
    public boolean isValid(){
        if(data[0] == 0 && data[1] == 0 && data[2] == 0){
            return true;
        } else{
            return false;
        }
    }

    
    /**
     * @returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte.
     */
    public String toString(){
        String ret = "";
        byte test;

        for(int i = 0; i < data.length; i++){
            test = data[i];
            ret += Byte.toUnsignedInt(test);
        }

        return ret;
    }

    /**
     * @param other represents the object that the hash is being compared to.
     * @returns true if this hash is structurally equal to the argument. 
     */
    public boolean equals(Object other){
        if(other instanceof Hash){
            Hash o = (Hash) other;

            if(Arrays.equals(starter.data, o.data)){
                
                return true;
            } else {

                return false;
            }
            
        }
        return false;
    }
}
