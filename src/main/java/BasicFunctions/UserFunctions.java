package BasicFunctions;

import Blockchain.Block;
import Blockchain.Blockchain;
import Blockchain.StudentAcademicInfo;
import ContentData.AcademicTranscript;
import ContentData.GraduationCert;
import ContentData.OutstandingPayments;
import ContentData.PersonalInfo;
import ContentData.TuitionPayments;
import Utils.FilePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.javatuples.Quintet;

public class UserFunctions {

    public static StudentAcademicInfo searchStudAcaInfo(String ID) {
        boolean hasRecord = false;
        if (Blockchain.getChain() != null) {
            List<Block> blocksList = Blockchain.getChain();
            //AtomicBoolean hasRecord = new AtomicBoolean(false);
            for (Block block : blocksList) {
                StudentAcademicInfo record = block.getInfo();
                if (!hasRecord) {
                    hasRecord = studAcaInfoValid(record, ID);
                }
                return record;
            }
            if (!hasRecord) {
                return null;
            }
        }
        return null;
    }

    public static boolean studAcaInfoValid(StudentAcademicInfo studAcaInfo, String ID) {
        if (isValid(studAcaInfo)) {
            List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> studInfoList = studAcaInfo.getRecordList();
            List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> validList = new ArrayList<>();
            for (Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments> objects : studInfoList) {
                if (objects.getValue0().getID().equals(ID)) {
                    validList.add(objects);
                }
            }
            if (validList.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(StudentAcademicInfo studAcaInfo) {
        String mekleRoot = studAcaInfo.getMerkleRoot();
        if (Blockchain.getChain() != null) {
            List<Block> blocksList = Blockchain.getChain();
            List<StudentAcademicInfo> studInfoList = new ArrayList<>();
            for (Block block : blocksList) {
                StudentAcademicInfo blockRecord = block.getInfo();
                if (blockRecord.getMerkleRoot().equals(mekleRoot)) {
                    studInfoList.add(blockRecord);
                }
            }
            if (studInfoList.size() > 0) {
                return true;
            }
        }
        return false;
    }
}
