package itgarden;

import itgarden.services.psychology.R_PsychologyMotherService;
import itgarden.services.training.R_Life_Skill_TrainningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ItgardenbaseApplicationTests {

    @Autowired
    R_PsychologyMotherService r_PsychologyMotherService;

    @Autowired
    R_Life_Skill_TrainningService r_Life_Skill_TrainningService;

    @Test
    public void contextLoads() {
  // System.out.println("result Total --------------------" + r_Life_Skill_TrainningService.livelihood_training_report_by_mother("20230911B0200"));

    }
}
