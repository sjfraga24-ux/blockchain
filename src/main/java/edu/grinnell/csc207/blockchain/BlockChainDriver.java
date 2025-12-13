package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * The main driver for the block chain program.
 */
public class BlockChainDriver {
   
    /**
     * The main entry point for the program.
     * @param args the command-line arguments
     * @throws NoSuchAlgorithmException 
     * @throws NumberFormatException 
     */
    public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException {
        if(args.length != 1){
            System.out.println("Invalid input.");
            return;
        }
        BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
        boolean running = true;
        String command = "";
        String command2 ="";
        System.out.println("Hello world!");
        Scanner input = new Scanner(System.in);
        while(running){
            System.out.println(chain.toString());
            System.out.println("Command? ");
            command = input.nextLine();
            if(command.equals("quit")){
                running = false;
            } else if(command.equals("mine")){
                System.out.println("Amount Transferred? ");
                command = input.nextLine();
                Block test = chain.mine(Integer.parseInt(command));
                System.out.println("Amount = " + test.getAmount() + ", nonce = " + test.getNonce());
            } else if(command.equals("append")){
                System.out.println("Amount Transferred? ");
                command = input.nextLine();
                System.out.println("Nonce? ");
                command2 = input.nextLine();
                chain.append(new Block(Block.getNum(), Integer.parseInt(command), Block.getPrevHash(), Integer.parseInt(command2)));
            } else if(command.equals("remove")){
                chain.removeLast();
            } else if(command.equals("check")){
                if(chain.isValidBlockChain()){
                    System.out.println("Chain is Valid!");
                } else{
                    System.out.println("Chain is Invalid!");
                }
            } else if(command.equals("report")){
                chain.printBalances();
            } else if(command.equals("help")){
                System.out.println("Valid commands:\r\n" + 
                                    "    mine: discovers the nonce for a given transaction\r\n" + 
                                    "    append: appends a new block onto the end of the chain\r\n" + 
                                    "    remove: removes the last block from the end of the chain\r\n" + 
                                    "    check: checks that the block chain is valid\r\n" + 
                                    "    report: reports the balances of Alice and Bob\r\n" + 
                                    "    help: prints this list of commands\r\n" + 
                                    "    quit: quits the program\r\n" +  "");
            }
        }
        input.close();
        
    }  
}
