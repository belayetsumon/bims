package itgarden;

import itgarden.services.homevisit.M_AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ItgardenbaseApplicationTests {

    @Autowired
    M_AddressService m_AddressService;
    
    @Test
    public void contextLoads() {
        // System.out.println(" previous code Total --------------------" + o_MHealthConditionsRepository.findAll().size());
        //System.out.println(" New code Total --------------------" + m_AddressService.motherbydistrict2(district,addressType).size());

    }
}
