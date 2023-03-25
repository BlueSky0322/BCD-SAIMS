package Blockchain;

import Utils.GeneralOperation;
import ContentData.*;
import org.javatuples.Quintet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MerkleTree {

    private List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> recordList;
    private String merkleRoot;
    private List<String> recordsHashListAsString = new LinkedList<>();

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public List<String> getRecordHashList() {
        return recordsHashListAsString;
    }

    private MerkleTree(List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> recordList) {
        super();
        this.recordList = recordList;
    }

    private static MerkleTree instance;

    public static MerkleTree getInstance(List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> recordList) {
        if (instance == null) {
            instance = new MerkleTree(recordList);
        }
        return instance;
    }

    public void build() {
        List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> tempList = new ArrayList<>();
        for (Quintet data : this.recordList) {
            tempList.add(data);
        }
        List<String> hashes = genRecordHashList(tempList);
        while (hashes.size() != 1) {
            hashes = genRecordHashListAsString(hashes);
        }
        this.merkleRoot = hashes.get(0);
        this.recordsHashListAsString = hashes;
    }

    private List<String> genRecordHashList(List<Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments>> recordList) {
        List<String> hashList = new ArrayList<>();
        int i = 0;
        while (i < recordList.size()) {
            Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments> left = recordList.get(i); //这边还要改
            i++;
            Quintet<PersonalInfo, Transcript, GraduationCert, OutstandingPayments, Payments> right;
            String hashing;
            if (i != recordList.size()) {
                right = recordList.get(i);
                hashing = Hashing.hashContentData(left.add(right).toString().getBytes(), GeneralOperation.getSha_Algo());
            } else {
                hashing = Hashing.hashContentData(left.toString().getBytes(), GeneralOperation.getSha_Algo());
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
            String hashing = Hashing.hashContentData(left.concat(right).getBytes(), GeneralOperation.getSha_Algo());
            hashList.add(hashing);
            i++;
        }
        return hashList;
    }
}
