package edu.grinnell.csc207.blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    // TODO: fill me in with tests that you write for this project!
    
    @Test
    @DisplayName("No nonce Duplicates")
    public void duplicateTest() throws NoSuchAlgorithmException {
        BlockChain tst = new BlockChain(10);
        List<Long> n = new ArrayList<>();
        boolean dble =true;
        for(int i = 0; i < 5; i ++){
            tst.append(new Block(i+1, i, Block.getPrevHash()));
            n.add(Block.getNonce());
        }
        for(int j = 0; j < 5; j++){
            for(int i =0; i <5; i++){
                if(n.get(i) == n.get(j)){
                    dble = false;
                }
            }
        }
        
        assertEquals(true, dble);
    }


@Test
@DisplayName("Invalid")
public void InvalidTest() throws NoSuchAlgorithmException  {
    BlockChain tst = new BlockChain(10);
    tst.append(tst.mine(40));

    assertEquals(false, tst.isValidBlockChain());
}




@Test
@DisplayName("Valid")
public void ValidTest() throws NoSuchAlgorithmException  {
    BlockChain tst = new BlockChain(10);
    tst.append(tst.mine(-2));
    assertEquals(true, tst.isValidBlockChain());
}
}

