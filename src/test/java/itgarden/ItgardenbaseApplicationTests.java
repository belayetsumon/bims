package itgarden;

import itgarden.repository.clinic.C_SpecialCareRepository;
import itgarden.services.clinic.C_Child_Health_AwarenessService;
import itgarden.services.cmc.R_FoodService;
import itgarden.services.leave.LeaveMotherService;
import itgarden.services.observation.O_ChildAdmissionService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ItgardenbaseApplicationTests {

    @Autowired
C_Child_Health_AwarenessService c_Child_Health_AwarenessService;

    @Test
    // System.out.println("Total..
    public void contextLoads() {

    

       // System.out.println(" total 2..........." + c_Child_Health_AwarenessService.getAllChildHealthAwarenessData_report().size());
        // System.out.println(" unauthorize MotherList ..........." + admitedMotherList.size());
        
    }
}
