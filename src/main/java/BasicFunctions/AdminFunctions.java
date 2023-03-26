package BasicFunctions;

import Blockchain.Block;
import Blockchain.Blockchain;
import Blockchain.StudentAcademicInfo;
import ContentData.*;
import Utils.FilePaths;
import java.io.File;
import java.util.List;
import org.javatuples.Quintet;

public class AdminFunctions {

    public static PersonalInfo pi;
    public static AcademicTranscript at;
    public static GraduationCert gc;
    public static OutstandingPayments op;
    public static TuitionPayments tp;

    public static void createRecord(PersonalInfo pi, AcademicTranscript at, GraduationCert gc, OutstandingPayments op, TuitionPayments tp) {
        //File f = new File(FilePaths.getChainFilePath());
        StudentAcademicInfo studAcainfo = new StudentAcademicInfo();
        AdminFunctions.pi = pi;
        AdminFunctions.at = at;
        AdminFunctions.gc = gc;
        AdminFunctions.op = op;
        AdminFunctions.tp = tp;
        Quintet studeAcaInfo = new Quintet<>(pi, at, gc, op, tp);

        if (studAcainfo.checkAdd(studeAcaInfo)) {
            Blockchain bc = Blockchain.getInstance(FilePaths.getChainFilePath());
            if (bc.getChain() == null) {
                //creates new genesis block if the blockchain doesn't exist yet
                System.err.println(">> Creating Blockchain genesis block!");
                bc.genesis(studAcainfo);
            } else {
                studAcainfo = new StudentAcademicInfo();
                studAcainfo.AddList(studeAcaInfo);
                Block previousBlock = bc.getChain().get(bc.getChain().size() - 1);
                Block newBlock = new Block(previousBlock.getBlockHeader().getCurrentHash(), studAcainfo);
                bc.nextBlock(newBlock);
                bc.distribute();
            }
        }

//        List<Block> bc = Blockchain.getChain();
//        Block lastBlock = bc.get(bc.size() - 1);
//        Block leftOut = new Block(lastBlock.getBlockHeader().getCurrentHash(), studAcainfo);
//        Blockchain.nextBlock(leftOut);
    }
}
