package itgarden;

import itgarden.services.homevisit.MotherMasterDataServices;
import itgarden.services.literacy.LiteracyNumeracyService;
import itgarden.services.observation.O_MAddmissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ItgardenbaseApplicationTests {

    @Autowired
    LiteracyNumeracyService literacyNumeracyService;

    @Autowired
    MotherMasterDataServices motherMasterDataServices;

    @Autowired
    O_MAddmissionService addmissionService;

    @Test
    public void contextLoads() {

        //System.out.println("" + addmissionService.addmissionMotherIdList().size());
        System.out.println(" Id for digital literacy Service " + literacyNumeracyService.getMotherMasterDataDTOs().size());
        //System.out.println("Mother Id List" + motherMasterDataServices.motherMasterDataIdList().size());

    }

}
