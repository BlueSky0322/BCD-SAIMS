package Blockchain;

import Utils.Algorithms;
import ContentData.*;
import org.javatuples.Quintet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MerkleTree {

    private List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> studAcaInfoList;
    private String merkleRoot = "0";
    private List<String> recordsHashListAsString = new LinkedList<>();

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public List<String> getRecordHashList() {
        return recordsHashListAsString;
    }

    private MerkleTree(List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> recordList) {
        this.studAcaInfoList = recordList;
    }

    private static MerkleTree instance;

    public static MerkleTree getInstance(List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> recordList) {
        if (instance == null) {
            instance = new MerkleTree(recordList);
        }
        return instance;
    }

    public void build() {
        List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> tempList = new ArrayList<>();
        for (Quintet data : this.studAcaInfoList) {
            tempList.add(data);
            //System.out.println(data);
        }
        List<String> hashes = genRecordHashList(tempList);
        //System.out.println("[BUILD] " + hashes.get(0));
        while (hashes.size() != 1) {
            hashes = genRecordHashListAsString(hashes);            
        }
        this.merkleRoot = hashes.get(0);
        this.recordsHashListAsString = hashes;
    }

    private List<String> genRecordHashList(List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> recordList) {
        List<String> hashList = new ArrayList<>();
        int i = 0;
        while (i < recordList.size()) {
            Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments> left = recordList.get(i); 
            i++;
            Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments> right;
            String hashing;
            if (i != recordList.size()) {
                right = recordList.get(i);
                hashing = Hashing.hashContentData(left.add(right).toString().getBytes(), Algorithms.AlgoSHA256());
            } else {
                hashing = Hashing.hashContentData(left.toString().getBytes(), Algorithms.AlgoSHA256());
            }
            hashList.add(hashing);
            i++;
        }
        return hashList;
    }

    private List<String> genRecordHashListAsString(List<String> record) {
        List<String> hashList = new ArrayList<>();
        int i = 0;
        while (i < record.size()) {
            String left = record.get(i);
            i++;
            String right = "";
            if (i != record.size()) {
                right = record.get(i);
            }
            String hashing = Hashing.hashContentData(left.concat(right).getBytes(), Algorithms.AlgoSHA256());
            hashList.add(hashing);
            i++;
        }
        return hashList;
    }
}
