package sppbims;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sppbims.repository.school.DiscontinuityRepository;
import sppbims.services.school.DiscontinuityService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SppbimsApplicationTests {

    @Autowired
    DiscontinuityService discontinuityService;

    @Autowired
    DiscontinuityRepository discontinuityRepository;

    @Test
    // System.out.println("Total..
    public void contextLoads() {

        System.out.println(" total..........." + discontinuityService.allDiscontinuitiesList().size());
    }
}
