package itgarden;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.services.cmc.R_FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ItgardenbaseApplicationTests {

    @Autowired
    R_FoodService r_FoodService;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Test
    // System.out.println("Total..
    public void contextLoads() {
         MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(487l);
      // System.out.println("Total admited..........." + m_Child_infoRepository.findByMotherMasterCode(motherMasterData).size());//        System.out.println("not elejable Total..........." + eligibilityStudentService.notEligibleChildIdList().size());
//        System.out.println(" school admited  Total..........." + s_RegularAdmissionClassService.S_regular_AddmitedChildIdList().size());
//        System.out.println(" Discontitnue Total..........." + discontinuityService.discontinuityChildIdList().size());
//        System.out.println("Total..........." + m_Child_infoService.findNotSchoolAdmitedChild().size());
//    

    }
}
