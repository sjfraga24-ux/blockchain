package edu.grinnell.csc207.blockchain;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {

    public class Node<T>{
        T val;
        Node<T> next;

        public Node(T val){
            this.val = val;
            next = null;
        }
    }

    Node<Block> first = null;
    Node<Block> last = null;
    int count = 0;

    /**
     * Creates a BlockChain that possess a single block the starts with the given initial amount
     * @param initial : the intial amount stored in the BlockChain
     */
    public BlockChain(int initial){
        first.val = new Block(0, initial, null);
        last.val = first.val;
        count++;
    }


    /**
     * Mines a new candidate block to be added to the list.
     * @param amount : the amount to be stored in the new block
     * @returns a Block that stores the next amount.
     */
    public Block mine(int amount){
        Block addOn = new Block(count, amount, last.val.getHash());
        return addOn;
    }

    /**
     * @returns the number of Blocks in BlockChain.
     */
    public int getSize(){
        return count;
    }


    /**
     * Adds this block to the list, throwing an IllegalArgumentException if this 
     * block cannot be added to the list
     * @param blk : the block to be added
     */
    public void append(Block blk) throws IllegalArgumentException{
        Node<Block> newNode = new Node<Block>(blk);
        last.next = newNode;
        last = newNode;
        count++;
    }

    /**
     * removes the last block from the chain.If the chain only contains a single block, 
     * then removeLast does nothing.
     * @returns true if it removes a block and false otherwise.
     */
    public boolean removeLast(){
        if(count <= 1){
            return false;
        }else{
            Node<Block> curr = first;
            while(curr.next != last){
                curr = curr.next;
            }
            curr.next = null;
            count--;
            return true;
        }

    }

    
    /**
     * @returns the hash of the last block in the chain.
     */
    public Hash getHash(){
       return last.val.getHash();
    }


    /**
     * Walks the blockchain and ensures that its blocks are consistent and valid.
     * @returns true if valid, false if not.
     */
    public boolean isValidBlockChain(){
        Node<Block> curr = first;
            while(curr.next != null){

                if(!curr.val.getHash().isValid()){
                    return false;
                } else if(curr.val.getAmount() >= 0 && curr.val.bob_Amt < curr.val.getAmount()){
                    return false;
                } else if(curr.val.getAmount() < 0 && curr.val.anna_Amt < Math.abs(curr.val.getAmount())){
                    return false;
                }
                curr = curr.next;
            }
            return true;
    }

    /**
     * prints Alice's and Bob's respective balances in the form:
     *  Alice: <amt>, Bob: <amt> on a single line
     */
    public void printBalances(){
        String balance = "Alice: " + last.val.anna_Amt + ", Bob: " + last.val.bob_Amt;
        System.out.println(balance); 
    }

    /**
     * @returns a string representation of the BlockChain
     */
    public String toString(){
        String ret = "";
        Node<Block> curr = first;
            while(curr.next != null){
                ret += curr.val.toString();
                ret += "/n";
                curr = curr.next;
            }
        return ret;
    }

}
