package Blockchain;

import org.javatuples.Quintet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ContentData.*;

public class StudentAcademicInfo implements Serializable {

    public static final int SIZE = 10;
    public String merkleRoot;
    private List<String> hashList = new ArrayList<>();

    public StudentAcademicInfo() {
        recordList = new ArrayList<>(SIZE);
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void buildAndSetMerkleRoot(List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> recordList) {
        MerkleTree mt = MerkleTree.getInstance(recordList);
        mt.build();
        this.merkleRoot = mt.getMerkleRoot();
        this.hashList = mt.getRecordHashList();
    }
    
    private List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> recordList;

    public List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> getRecordList() {
        return recordList;
    }

    public boolean add(Quintet recordToAdd) {
        if (recordList.size() != SIZE) {
            recordList.add(recordToAdd);
            buildAndSetMerkleRoot(recordList);
            return true;
        }
        return false;
    }

    public List<String> getHashes() {
        return hashList;
    }

    @Override
    public String toString() {
        return "Student Record [merkleroot = " + merkleRoot + ", recordList = " + recordList + "]";
    }
}
