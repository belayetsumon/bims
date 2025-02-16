/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.District;
import sppbims.model.homevisit.Thana;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface ThanaRepository extends JpaRepository<Thana, Long> {

    List<Thana> findByDistrict(District district);
}
