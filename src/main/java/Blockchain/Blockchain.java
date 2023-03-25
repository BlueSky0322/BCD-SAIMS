package Blockchain;

import Utils.GeneralOperation;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class Blockchain {

    private static String chainFile = GeneralOperation.getMaster_binary();
    private static String ledgerFile = GeneralOperation.getRecord_path();
    private static LinkedList<Block> db = new LinkedList<>();

    //singleton pattern
    public static Blockchain _instance;

    public static Blockchain getInstance(String masterChainFile) {
        if (_instance == null) {
            _instance = new Blockchain(masterChainFile);
        }
        return _instance;
    }

    private Blockchain(String chainFile) {
        super();
        this.chainFile = chainFile;

        System.out.println(this.chainFile);
        System.out.println(this.ledgerFile);

    }

    public static void genesis(StudentAcademicInfo record) {
        Block genesis = new Block("0", record);
        db.add(genesis);
        Blockchain.presist();
        Blockchain.distribute();
    }

    public static void nextBlock(Block newBlock) {

        db = Blockchain.getChain();
        db.add(newBlock);

        Blockchain.presist();
    }

    public static LinkedList<Block> getChain() {
        try (
                FileInputStream fis = new FileInputStream(chainFile); ObjectInputStream in = new ObjectInputStream(fis);) {
            return (LinkedList<Block>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void presist() {
        try (FileOutputStream fos = new FileOutputStream(chainFile); ObjectOutputStream out = new ObjectOutputStream(fos);) {
            out.writeObject(db);
            System.out.println(">> Master file updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void distribute() {
        //convert chain to string using API
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(Blockchain.getChain());
        System.out.println(chain);
        try {
            Files.write(Paths.get(ledgerFile), chain.getBytes(), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
