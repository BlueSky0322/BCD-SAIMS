package Blockchain;

import Utils.Algorithms;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable {

    public Header blockHeader;

    public Header getBlockHeader() {
        return blockHeader;
    }

    public class Header implements Serializable {

        //data member
        public int index;
        public String currentHash, previousHash;
        public long timestamp;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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
            return "Header [index= " + index + ", currentHash= "
                    + currentHash + ", previousHash= " + previousHash + ", timeStamp= "
                    + timestamp + "]";
        }
    }
    private StudentAcademicInfo studAcaInfo;

    public Block(String prevHash, StudentAcademicInfo studAcaInfo) {
        super();
        long now = System.currentTimeMillis();

        /* construct part object upon object construction */
        this.blockHeader = new Header();
        this.blockHeader.setPreviousHash(prevHash);
        this.blockHeader.setTimestamp(now);

        //hashing the entire block using Sha256
        String blockHash = Hashing.hashBlock(getBytes(), Algorithms.AlgoSHA256());
        this.blockHeader.setCurrentHash((blockHash));
        setInfo(studAcaInfo);
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

    private void setInfo(StudentAcademicInfo studAcaInfo) {
        this.studAcaInfo = studAcaInfo;
    }

    public StudentAcademicInfo getInfo() {
        return studAcaInfo;
    }
}
