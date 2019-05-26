/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.Child_image;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.Child_imageRepository;
import itgarden.repository.observation.MotherImageRepository;
import itgarden.services.StorageProperties;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/childimage")
public class ChildImageController {

    @Autowired
    StorageProperties properties;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    MotherImageRepository motherImageRepository;

    @Autowired
    Child_imageRepository child_imageRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/create/{m_id}")
    public String mAdd(Model model, @PathVariable Long m_id, Child_image child_image) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        child_image.setMotherMasterCode(motherMasterData);
        model.addAttribute("childList", m_Child_infoRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/imageupload/addchildimg";
    }

    @RequestMapping("/edit/{id}")
    public String mEdit(Model model, @PathVariable Long id, Child_image child_image) {
        model.addAttribute("o_MAddmission", motherImageRepository.findOne(id));
        return "homevisit/observation/admission/addmotherimg";
    }

    @RequestMapping("/save/{m_id}")
    public String mSave(Model model, @PathVariable Long m_id, @Valid Child_image child_image,
            BindingResult bindingResult, @RequestParam("picName") MultipartFile filee, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            child_image.setMotherMasterCode(motherMasterData);
            model.addAttribute("childList", m_Child_infoRepository.findBymotherMasterCode(motherMasterData));
            return "homevisit/observation/imageupload/addchildimg";
        }

        if (filee.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "homevisit/observation/imageupload/addchildimg";
        }

        if (filee.getSize() > 150000) {
            model.addAttribute("message", "file is big");
            return "homevisit/observation/imageupload/addchildimg";
        }

//           if (file.getContentType()!=".png" || file.getContentType()!=".jpg" || file.getContentType()!=".jpeg") {
//           model.addAttribute("message", "Please upload png or jpg file");
//            return "homevisit/observation/admission/addmotherimg";
//         }
//         
        if (!filee.isEmpty()) {
            try {
                byte[] bytes = filee.getBytes();

                // Creating the directory to store file
                File dir = new File(properties.getRootPath() + File.separator + "bims_repo/childimage");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + filee.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
//				logger.info("Server File Location="
//						+ serverFile.getAbsolutePath());
                //  return "You successfully uploaded file=" + filee.getOriginalFilename();

                model.addAttribute("message", "You successfully uploaded");

                // model.addAttribute("o_MAddmission", motherMasterDataRepository.findOne(m_id));
                child_image.setImageName(filee.getOriginalFilename());
                child_imageRepository.save(child_image);

                redirectAttributes.addFlashAttribute("message", "Sucess");

                return "redirect:/image/index/{m_id}";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", filee.getOriginalFilename() + " => " + e.getMessage());
                return "redirect:/image/index/{m_id}";

            }
        } else {
            redirectAttributes.addFlashAttribute("message", "File empty");
            return "redirect:/image/index/{m_id}";
        }

    }

    @Transactional
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Child_image child_image, RedirectAttributes redirectAttrs) {

        child_image = child_imageRepository.findOne(id);

        File file = new File(properties.getRootPath() + File.separator + "bims_repo/childimage" + File.separator + child_image.getImageName());
        file.delete();
        redirectAttrs.addAttribute("m_id", child_image.motherMasterCode.getId());
        child_imageRepository.delete(id);

        return "redirect:/image/index/{m_id}";
    }
}
