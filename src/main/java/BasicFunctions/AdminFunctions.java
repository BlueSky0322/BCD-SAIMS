package BasicFunctions;

import Blockchain.Block;
import Blockchain.Blockchain;
import Blockchain.AcademicInfoTranx;
import ContentData.*;
import Utils.FilePaths;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.javatuples.Quintet;

public class AdminFunctions {

    private static String masterFolder = "MasterBinary";
    private static AcademicInfoTranx studAcaInfoTranx = new AcademicInfoTranx();

    public static Quintet generateRecords(String ID) {
        PersonalInfo pi = new PersonalInfo(ID);
        AcademicTranscript at = new AcademicTranscript();
        GraduationCert gc = new GraduationCert();
        OutstandingPayments op = new OutstandingPayments();
        TuitionPayments tp = new TuitionPayments(ID);

        Quintet studAcaInfoQuintet = new Quintet<>(pi, at, gc, op, tp);
        return studAcaInfoQuintet;
    }

    public static void createRecord(AcademicInfoTranx tranxList) {
        AtomicBoolean hasGenesis = new AtomicBoolean(false);
        Blockchain bc = Blockchain.getInstance(FilePaths.getChainFilePath());

        if (!hasGenesis.get()) {
            if (!new File(masterFolder)
                    .exists() || bc.getChain() == null) {
                //creates new genesis block if the blockchain doesn't exist yet
                System.err.println(">> Creating Blockchain genesis block!");
                //studAcainfo = new AcademicInfoTranx();
                //studAcainfo.AddList(studeAcaInfo);
                new File(masterFolder).mkdir();
                bc.genesis();
                hasGenesis.set(true);
            } else {
                hasGenesis.set(true);
            }
        }
        Block previousBlock = bc.getChain().get(bc.getChain().size() - 1);
        Block newBlock = new Block(previousBlock.getBlockHeader().getCurrentHash(), studAcaInfoTranx);
        newBlock.setInfo(tranxList);
        bc.nextBlock(newBlock);
        bc.distribute();

    }
}
