/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Md Belayet Hossin
 */
public class GolbalModel {
    
    @Value("app.user.root")
  public  String userRepository  ;
}
