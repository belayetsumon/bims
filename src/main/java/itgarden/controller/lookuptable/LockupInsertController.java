/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Aid_Type;
import itgarden.model.homevisit.District;
import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.EthinicIdentity;
import itgarden.model.homevisit.HusbandsStatus;
import itgarden.model.homevisit.Income_Generating;
import itgarden.model.homevisit.MaritalStatus;
import itgarden.model.homevisit.Occupation;
import itgarden.model.homevisit.Reasons;
import itgarden.model.homevisit.Relations;
import itgarden.model.homevisit.Religion;
import itgarden.model.homevisit.Thana;
import itgarden.model.rehabilitations.DegenerativeDiseases;
import itgarden.model.rehabilitations.Diagonosis;
import itgarden.model.rehabilitations.JointMobility;
import itgarden.model.rehabilitations.Mucsuloskeletal;
import itgarden.model.rehabilitations.SessionType;
import itgarden.model.rehabilitations.Tenderness;
import itgarden.repository.homevisit.Aid_TypeRepository;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.EthinicIdentityRepository;
import itgarden.repository.homevisit.HusbandsStatusRepository;
import itgarden.repository.homevisit.Income_GeneratingRepository;
import itgarden.repository.homevisit.MaritalStatusRepository;
import itgarden.repository.homevisit.OccupationRepository;
import itgarden.repository.homevisit.ReasonsRepository;
import itgarden.repository.homevisit.RelationsRepository;
import itgarden.repository.homevisit.ReligionRepository;
import itgarden.repository.homevisit.ThanaRepository;
import itgarden.repository.rehabilitations.DegenerativeDiseasesRepository;
import itgarden.repository.rehabilitations.DiagonosisRepository;
import itgarden.repository.rehabilitations.JointMobilityRepository;
import itgarden.repository.rehabilitations.MucsuloskeletalRepository;
import itgarden.repository.rehabilitations.SessionTypeRepository;
import itgarden.repository.rehabilitations.TendernessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/lookupInsert")
public class LockupInsertController {

    @Autowired
    ReasonsRepository reasonsRepository;

    @Autowired
    ReligionRepository religionRepository;

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Autowired
    HusbandsStatusRepository husbandsStatusRepository;

    @Autowired
    RelationsRepository relationsRepository;

    @Autowired
    EthinicIdentityRepository ethinicIdentityRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    EducationTypeRepository educationTypeRepository;

    @Autowired
    OccupationRepository occupationRepository;

    @Autowired
    ThanaRepository thanaRepository;
    @Autowired
    Income_GeneratingRepository income_GeneratingRepository;
    @Autowired
    Aid_TypeRepository aid_TypeRepository;

    /**
     * ************* Rehabilitations *******************************
     */
    /**
     * ************ OT **********************
     */
    @Autowired
    SessionTypeRepository sessionTypeRepository;

    @Autowired
    DiagonosisRepository diagonosisRepository;

    /**
     * ********* PT **************
     */
    @Autowired
    JointMobilityRepository jointMobilityRepository;

    @Autowired
    MucsuloskeletalRepository mucsuloskeletalRepository;

    @Autowired
    DegenerativeDiseasesRepository degenerativeDiseasesRepository;

    @Autowired
    TendernessRepository tendernessRepository;

    @RequestMapping("/index")
    public String index(Model model) {

        reasonsRepository.save(new Reasons(1L, "Death"));

        religionRepository.save(new Religion(1l, "Islam"));
        religionRepository.save(new Religion(2l, "Hindu"));

        maritalStatusRepository.save(new MaritalStatus(1L, "Single "));
        maritalStatusRepository.save(new MaritalStatus(2L, "married "));
        maritalStatusRepository.save(new MaritalStatus(3L, "Divorce "));
        maritalStatusRepository.save(new MaritalStatus(4L, "widow "));

        husbandsStatusRepository.save(new HusbandsStatus(1L, " Death"));
        husbandsStatusRepository.save(new HusbandsStatus(2L, " Lost"));

        relationsRepository.save(new Relations(1L, "Father"));
        relationsRepository.save(new Relations(2L, "Bother"));

        ethinicIdentityRepository.save(new EthinicIdentity(1l, " Chakma"));
        ethinicIdentityRepository.save(new EthinicIdentity(2l, " Marma"));
        ethinicIdentityRepository.save(new EthinicIdentity(3l, " Tripura"));
        ethinicIdentityRepository.save(new EthinicIdentity(4l, " Santal"));
        ethinicIdentityRepository.save(new EthinicIdentity(5l, " Manipuri"));

        educationLevelRepository.save(new EducationLevel(1l, "Label-1"));
        educationLevelRepository.save(new EducationLevel(2l, "Label-2"));
        educationLevelRepository.save(new EducationLevel(3l, "Label-3"));
        educationLevelRepository.save(new EducationLevel(4l, "Label-4"));
        educationLevelRepository.save(new EducationLevel(5l, "Label-5"));

        educationTypeRepository.save(new EducationType(1L, "Bangla"));
        educationTypeRepository.save(new EducationType(2L, "English"));
        educationTypeRepository.save(new EducationType(3L, "Madrasha"));

        occupationRepository.save(new Occupation(1l, "Farmer"));
        occupationRepository.save(new Occupation(2l, "Fisherman"));

        thanaRepository.save(new Thana(1l, District.Gazipur, "Kaliakair"));
        thanaRepository.save(new Thana(2l, District.Gazipur, "Kapasia "));
        thanaRepository.save(new Thana(3l, District.Gazipur, "Sreepur "));
        thanaRepository.save(new Thana(4l, District.Gazipur, "Kaliganj"));

        income_GeneratingRepository.save(new Income_Generating(1l, "Daily work"));
        aid_TypeRepository.save(new Aid_Type(1l, " Cloth"));
        aid_TypeRepository.save(new Aid_Type(2l, " Food"));
        aid_TypeRepository.save(new Aid_Type(3l, "Education"));

        /*   Rehabilitaions    */
        sessionTypeRepository.save(new SessionType(1l, "Session -1"));
        sessionTypeRepository.save(new SessionType(2l, "Session -2"));
        sessionTypeRepository.save(new SessionType(3l, "Session -3"));

        diagonosisRepository.save(new Diagonosis(1l, " Diagonosis -1"));
        diagonosisRepository.save(new Diagonosis(2l, " Diagonosis -2"));
        diagonosisRepository.save(new Diagonosis(3l, " Diagonosis -3"));

        jointMobilityRepository.save(new JointMobility(1l, " joint Mobility-1"));
        jointMobilityRepository.save(new JointMobility(2l, " joint Mobility-2"));
        jointMobilityRepository.save(new JointMobility(3l, " joint Mobility-3"));

        mucsuloskeletalRepository.save(new Mucsuloskeletal(1l, " Mucsuloskeletal-1"));
        mucsuloskeletalRepository.save(new Mucsuloskeletal(2l, " Mucsuloskeletal-2"));
        mucsuloskeletalRepository.save(new Mucsuloskeletal(3l, " Mucsuloskeletal-3"));

        tendernessRepository.save(new Tenderness(1l, " Tenderness-1"));
        tendernessRepository.save(new Tenderness(2l, " Tenderness-2"));
        tendernessRepository.save(new Tenderness(3l, " Tenderness-3"));
        tendernessRepository.save(new Tenderness(4l, " Tenderness-4"));

        degenerativeDiseasesRepository.save(new DegenerativeDiseases(1l, "Degenerative Diseases - 1 "));
        degenerativeDiseasesRepository.save(new DegenerativeDiseases(2l, "Degenerative Diseases - 2 "));
        degenerativeDiseasesRepository.save(new DegenerativeDiseases(3l, "Degenerative Diseases - 3 "));
        degenerativeDiseasesRepository.save(new DegenerativeDiseases(4l, "Degenerative Diseases - 4 "));
        degenerativeDiseasesRepository.save(new DegenerativeDiseases(5l, "Degenerative Diseases - 5 "));

        return "homevisit/lookup/lookupinsert";
    }

}
