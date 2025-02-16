package sppbims;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.M_Child_infoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SppbimsApplicationTests {

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Test
    // System.out.println("Total..
    public void contextLoads() {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(480l);

        System.out.println(" total ..........." + m_Child_infoRepository.findByMotherMasterCode(motherMasterData).size());
        //System.out.println(" total..........." + o_ChildAdmissionService.admited_Child_By_Mother(20l).size());

    }
}
