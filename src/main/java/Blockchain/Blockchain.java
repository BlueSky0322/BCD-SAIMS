package Blockchain;

import Utils.Algorithms;
import Utils.FilePaths;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class Blockchain {

    private static String chainFile = FilePaths.getChainFilePath();
    private static String ledgerFile = FilePaths.getAcaInfoPath();
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
        System.out.println(">> Blockchain object is created!");
    }

    public void genesis() {
        Block genesis = new Block("0", null);
        db.add(genesis);
        persist();
        distribute();
    }

    public void nextBlock(Block newBlock) {
        db = getChain();
        newBlock.getBlockHeader().setIndex(db.getLast().getBlockHeader().getIndex() + 1);
        db.add(newBlock);
        persist();
    }

    public LinkedList<Block> getChain() {
        try (FileInputStream fis = new FileInputStream(chainFile); ObjectInputStream in = new ObjectInputStream(fis);) {
            return (LinkedList<Block>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void persist() {
        try (FileOutputStream fos = new FileOutputStream(chainFile); ObjectOutputStream out = new ObjectOutputStream(fos);) {
            out.writeObject(db);
            System.out.println(">> Master file updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void distribute() {
        //convert chain to string using API
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
        System.out.println(chain);
        try {
            Files.write(Paths.get(ledgerFile), chain.getBytes(), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
