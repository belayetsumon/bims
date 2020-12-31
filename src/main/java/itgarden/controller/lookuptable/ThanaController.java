/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.District;
import itgarden.model.homevisit.Thana;
import itgarden.repository.homevisit.ThanaRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
  @RequestMapping("/thana")
public class ThanaController {
    
    
    @Autowired
    ThanaRepository thanaRepository;
    
    @RequestMapping("/index")
    public String index(Model model,Thana thana ) {
      model.addAttribute("list",thanaRepository.findAll() );
      model.addAttribute("district",District.values() );
       
        return "homevisit/lookup/thana";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Thana thana ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",thanaRepository.findAll() );
         return "homevisit/lookup/thana";
        }
        
        thanaRepository.save(thana);

        return "redirect:/thana/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Thana thana, Model model) {
       model.addAttribute("thana",   thanaRepository.findOne(id));
        model.addAttribute("list",  thanaRepository.findAll());
        model.addAttribute("district",District.values() );
         return "/homevisit/lookup/thana";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Thana thana) {        
     thanaRepository.delete(id);
          return "redirect:/thana/index";
    } 
    
    
     @GetMapping(value = "/thana/{district}")
     @ResponseBody
    public  List<Thana> thanaselect(@PathVariable District district, Thana thana, Model model) {
        List<Thana> thanalist =   thanaRepository.findByDistrict(district);
          return thanalist;
    }
    

}
