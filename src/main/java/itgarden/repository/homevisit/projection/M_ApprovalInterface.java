/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author User
 */
public interface M_ApprovalInterface {

    public String getApproveDate();

    public String getApproveBy();

   
    @Value("#{target.homeVisitDate}")
     public String getHomeVisitDate();

}
