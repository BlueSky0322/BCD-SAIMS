package Blockchain;

import org.javatuples.Quintet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ContentData.*;

public class AcademicInfoTranx implements Serializable {

    public static final int SIZE = 30;
    public String merkleRoot;
    private List<String> hashList = new ArrayList<>();

    public AcademicInfoTranx() {
        studentAcademicInformation = new ArrayList<>(SIZE);
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public void buildAndSetMerkleRoot(List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> recordList) {
        MerkleTree mt = MerkleTree.getInstance(recordList);
        mt.build();
        this.merkleRoot = mt.getMerkleRoot();
        this.hashList = mt.getRecordHashList();
    }

    private List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> studentAcademicInformation;

    public List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> getRecordList() {
        return studentAcademicInformation;
    }

    public void AddList(Quintet recordToAdd) {
        if (studentAcademicInformation.size() < SIZE) {
            studentAcademicInformation.add(recordToAdd);
            buildAndSetMerkleRoot(studentAcademicInformation);
        }
    }

    public List<String> getHashes() {
        return hashList;
    }

    @Override
    public String toString() {
        return "Student Record [merkleroot = " + merkleRoot + ", recordList = " + studentAcademicInformation + "]";
    }
}
