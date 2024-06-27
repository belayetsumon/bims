package itgarden;

import itgarden.repository.homevisit.MotherMasterDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ItgardenbaseApplicationTests {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Test
    public void contextLoads() {
        
        
     // motherMasterDataRepository.
      //        findAllByeligibilityAndMaddressIsNullOrderByIdDesc(Eligibility.Eligible);
    
    
    }

}
