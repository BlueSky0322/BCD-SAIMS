package Blockchain;

import org.javatuples.Quintet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ContentData.*;

public class StudentAcademicInfo implements Serializable {

    public static final int SIZE = 30;
    public String merkleRoot = "0";
    private List<String> hashList = new ArrayList<>();

    public StudentAcademicInfo() {
        recordList = new ArrayList<>(SIZE);
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void buildAndSetMerkleRoot(List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> recordList) {
        //System.out.println("Begin get instance");
        MerkleTree mt = MerkleTree.getInstance(recordList);
        //System.out.println("Begin build");
        mt.build();
        this.merkleRoot = mt.getMerkleRoot();
        this.hashList = mt.getRecordHashList();
    }

    private List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> recordList;

    public List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> getRecordList() {
        return recordList;
    }

    public boolean checkAdd(Quintet recordToAdd) {
        if (recordList.size() != SIZE) {
            return true;
        }
        return false;
    }

    public void AddList(Quintet recordToAdd) {
        recordList.add(recordToAdd);
        buildAndSetMerkleRoot(recordList);
    }

    public List<String> getHashes() {
        return hashList;
    }

    @Override
    public String toString() {
        return "Student Record [merkleroot = " + merkleRoot + ", recordList = " + recordList + "]";
    }
}
