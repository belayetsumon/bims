/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.ImageTypeEnum;
import itgarden.model.observation.MotherImage;
import itgarden.model.observation.O_MAddmission;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.Child_imageRepository;
import itgarden.repository.observation.MotherImageRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.services.StorageProperties;
import jakarta.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
@RequestMapping("/image")
public class MotherImageController {

    @Autowired
    StorageProperties properties;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @Autowired
    MotherImageRepository motherImageRepository;

    @Autowired
    Child_imageRepository child_imageRepository;

    @RequestMapping("/newmother")
    public String newmother(Model model) {
        // model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        model.addAttribute("list", o_MAddmissionRepository.findBymotherImageIsNullOrderByIdDesc());

        return "homevisit/observation/imageupload/newmother";
    }

    @RequestMapping("/motherlist")
    public String page(Model model) {
        // model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        model.addAttribute("list", motherImageRepository.findAll());
        return "homevisit/observation/imageupload/mothersearch";
    }

    @RequestMapping("/index/{m_id}")
    public String index(Model model, @PathVariable Long m_id) {

        model.addAttribute("minfo", m_id);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        model.addAttribute("mimage", motherImageRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("cimage", child_imageRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/imageupload/index";
    }

    @RequestMapping("/create/{m_id}")
    public String mAdd(Model model, @PathVariable Long m_id, MotherImage motherImage) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        motherImage.setMotherMasterCode(motherMasterData);

        O_MAddmission oMAddmission = o_MAddmissionRepository.findByMotherMasterCode(motherMasterData);
        oMAddmission.setId(oMAddmission.getId());
        motherImage.setAddmission(oMAddmission);

        model.addAttribute("imageType", ImageTypeEnum.values());
        return "homevisit/observation/imageupload/addmotherimg";
    }

    @RequestMapping("/edit/{id}")
    public String mEdit(Model model, @PathVariable Long id, MotherImage motherImage) {
        model.addAttribute("o_MAddmission", motherImageRepository.findById(id).orElse(null));
        model.addAttribute("imageType", ImageTypeEnum.values());
        return "homevisit/observation/admission/addmotherimg";
    }

    @RequestMapping("/save/{m_id}")
    public String mSave(Model model, @PathVariable Long m_id, @Valid MotherImage motherImage,
            BindingResult bindingResult, @RequestParam("picName") MultipartFile filee, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            motherImage.setMotherMasterCode(motherMasterData);

            O_MAddmission oMAddmission = o_MAddmissionRepository.findByMotherMasterCode(motherMasterData);
            oMAddmission.setId(oMAddmission.getId());
            motherImage.setAddmission(oMAddmission);
            model.addAttribute("imageType", ImageTypeEnum.values());

            return "homevisit/observation/imageupload/addmotherimg";
        }

        if (filee.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "homevisit/observation/imageupload/addmotherimg";
        }

        if (filee.getSize() > 150000) {
            model.addAttribute("message", "File size is learge. Please upload 150X150 px.file");
            return "homevisit/observation/imageupload/addmotherimg";
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
                File dir = new File(properties.getRootPath() + File.separator + "bims_repo");
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
                motherImage.setImageName(filee.getOriginalFilename());
                motherImageRepository.save(motherImage);

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
    public String delete(Model model, @PathVariable Long id, MotherImage motherImage, RedirectAttributes redirectAttrs) {

        motherImage = motherImageRepository.findById(id).orElse(null);

        File file = new File(properties.getRootPath() + File.separator + "bims_repo" + File.separator + motherImage.getImageName());
        file.delete();
        //redirectAttrs.addAttribute("m_id", motherImage.motherMasterCode.getId());
        motherImageRepository.deleteById(id);
        redirectAttrs.addAttribute("m_id", motherImage.motherMasterCode.getId());
        return "redirect:/image/index/{m_id}";
    }
}
