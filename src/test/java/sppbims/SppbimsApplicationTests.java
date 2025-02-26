package sppbims;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sppbims.services.observation.O_MAddmissionService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SppbimsApplicationTests {

    @Autowired
   O_MAddmissionService o_MAddmissionService;

    @Test
    // System.out.println("Total..
    public void contextLoads() {

       // System.out.println(" total ..........." + o_MAddmissionService.findMotherImageNull().size());
        //System.out.println(" total..........." + o_ChildAdmissionService.admited_Child_By_Mother(20l).size());

    }
}
