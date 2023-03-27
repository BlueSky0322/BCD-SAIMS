package Blockchain;

import Utils.Algorithms;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class Block implements Serializable {

    private static final long serialVersionUID = 1L;
    public Header blockHeader;

    public Header getBlockHeader() {
        return blockHeader;
    }

    public class Header implements Serializable {

        //data member
        private static final long serialVersionUID = 1L;
        public int blockIndex;
        public String currentHash, previousHash;
        public long timestamp;

        public int getIndex() {
            return blockIndex;
        }

        public void setIndex(int index) {
            blockIndex = index;
        }

        public String getCurrentHash() {
            return currentHash;
        }

        public void setCurrentHash(String currentHash) {
            this.currentHash = currentHash;
        }

        public String getPreviousHash() {
            return previousHash;
        }

        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Header [index= " + blockIndex + ", currentHash= "
                    + currentHash + ", previousHash= " + previousHash + ", timeStamp= "
                    + timestamp + "]";
        }
    }
    public AcademicInfoTranx transaction;

    public void setInfo(AcademicInfoTranx transaction) {
        this.transaction = transaction;
    }

    public AcademicInfoTranx getInfo() {
        return transaction;
    }

    public Block(String prevHash, AcademicInfoTranx studAcaInfoTranx) {
        super();
        long now = System.currentTimeMillis();

        /* construct part object upon object construction */
        this.blockHeader = new Header();
        this.blockHeader.setPreviousHash(prevHash);
        this.blockHeader.setTimestamp(now);
        
        String blockHash = Hashing.hashBlock(getBytes(), Algorithms.AlgoSHA256());
        this.blockHeader.setCurrentHash((blockHash));
        setInfo(studAcaInfoTranx);
    }

    private byte[] getBytes() {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(byteStream);) {
            out.writeObject(this);
            return byteStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
