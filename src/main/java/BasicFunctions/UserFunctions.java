package BasicFunctions;

import Blockchain.Block;
import Blockchain.Blockchain;
import Blockchain.AcademicInfoTranx;
import ContentData.AcademicTranscript;
import ContentData.GraduationCert;
import ContentData.OutstandingPayments;
import ContentData.PersonalInfo;
import ContentData.TuitionPayments;
import Utils.FilePaths;
import java.util.List;
import org.javatuples.Quintet;

public class UserFunctions {

    public static AcademicInfoTranx searchStudAcaInfo(String ID) {
        Blockchain bc = Blockchain.getInstance(FilePaths.getChainFilePath());
        //boolean hasRecord = false;
        if (bc.getChain() != null) {
            List<Block> blocksList = bc.getChain();
            boolean genesisBlockSkipped = false;
            for (Block block : blocksList) {
                if (!genesisBlockSkipped) { // skip the first block
                    genesisBlockSkipped = true; // set flag to true
                    continue;
                }
                AcademicInfoTranx record = block.getInfo();
//                if (isCorrect(record, bc)) {
                List<Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments>> studInfoList = record.getRecordList();
                for (Quintet<PersonalInfo, AcademicTranscript, GraduationCert, OutstandingPayments, TuitionPayments> objects : studInfoList) {
                    if (objects.getValue0().getID().equals(ID)) {
                        return record; // return the first instance of the record with matching ID
                    }
                }
            }
        }
        return null;
    }
}
