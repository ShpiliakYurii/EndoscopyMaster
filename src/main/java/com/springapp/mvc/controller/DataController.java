package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.*;
import com.springapp.mvc.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 07.03.2016.
 */
@Controller
@SessionAttributes({"user", "apparatus", "solution", "pacient", "revisionType", "revision"})
public class DataController {

    private RevisionTypeRepository revisionTypeRepository;
    private OrganRepository organRepository;
    private RegionRepository regionRepository;
    private TermRepository termRepository;
    private FeaturesRepository featuresRepository;
    private CharacteristicRepository characteristicRepository;
    private MedicinecardRepository medicinecardRepository;
    private MedicinecardValidator medicinecardValidator;
    private ApparatusRepository apparatusRepository;
    private ManipulationRepository manipulationRepository;
    private ConclusionRepository conclusionRepository;
    private DisinfectantSolutionRepository disinfectantSolutionRepository;
    private RevisionsRepository revisionsRepository;
    private RefferalDictionaryRepository refferalDictionaryRepository;
    private UserRepository userRepository;
    private ApparatusValidator apparatusValidator;
    private RefferalValidator refferalValidator;
    private DisinfectantSolutionValidator disinfectantSolutionValidator;
    private RevisionTypeValidator revisionTypeValidator;
    private OrganValidator organValidator;
    private RegionValidator regionValidator;
    private TermValidator termValidator;
    private FeaturesValidator featuresValidator;
    private CharacteristicValidator characteristicValidator;
    private RecomendationRepository recomendationRepository;
    private ConclusionValidator conclusionValidator;
    private RecomendationValidator recomendationValidator;
    private ManipulationValidator manipulationValidator;
    private OperationRepository operationRepository;
    private OperationValidator operationValidator;
    private DoctorController doctorController;

    @Autowired
    public DataController(RevisionTypeRepository revisionTypeRepository, OrganRepository organRepository,
                          RegionRepository regionRepository, TermRepository termRepository,
                          FeaturesRepository featuresRepository, CharacteristicRepository characteristicRepository,
                          MedicinecardRepository medicinecardRepository, MedicinecardValidator medicinecardValidator,
                          ApparatusRepository apparatusRepository, ManipulationRepository manipulationRepository,
                          ConclusionRepository conclusionRepository, DisinfectantSolutionRepository disinfectantSolutionRepository,
                          RevisionsRepository revisionsRepository, RefferalDictionaryRepository refferalDictionaryRepository,
                          UserRepository userRepository, ApparatusValidator apparatusValidator,
                          RefferalValidator refferalValidator, DisinfectantSolutionValidator disinfectantSolutionValidator,
                          RevisionTypeValidator revisionTypeValidator, OrganValidator organValidator,
                          RegionValidator regionValidator, TermValidator termValidator, FeaturesValidator featuresValidator,
                          CharacteristicValidator characteristicValidator, RecomendationRepository recomendationRepository,
                          ConclusionValidator conclusionValidator, RecomendationValidator recomendationValidator,
                          ManipulationValidator manipulationValidator, OperationRepository operationRepository,
                          OperationValidator operationValidator, DoctorController doctorController) {
        this.revisionTypeRepository = revisionTypeRepository;
        this.organRepository = organRepository;
        this.regionRepository = regionRepository;
        this.termRepository = termRepository;
        this.featuresRepository = featuresRepository;
        this.characteristicRepository = characteristicRepository;
        this.medicinecardRepository = medicinecardRepository;
        this.medicinecardValidator = medicinecardValidator;
        this.apparatusRepository = apparatusRepository;
        this.manipulationRepository = manipulationRepository;
        this.conclusionRepository = conclusionRepository;
        this.disinfectantSolutionRepository = disinfectantSolutionRepository;
        this.revisionsRepository = revisionsRepository;
        this.refferalDictionaryRepository = refferalDictionaryRepository;
        this.userRepository = userRepository;
        this.apparatusValidator = apparatusValidator;
        this.refferalValidator = refferalValidator;
        this.disinfectantSolutionValidator = disinfectantSolutionValidator;
        this.revisionTypeValidator = revisionTypeValidator;
        this.organValidator = organValidator;
        this.regionValidator = regionValidator;
        this.termValidator = termValidator;
        this.featuresValidator = featuresValidator;
        this.characteristicValidator = characteristicValidator;
        this.recomendationRepository = recomendationRepository;
        this.conclusionValidator = conclusionValidator;
        this.recomendationValidator = recomendationValidator;
        this.manipulationValidator = manipulationValidator;
        this.operationRepository = operationRepository;
        this.operationValidator = operationValidator;
        this.doctorController = doctorController;
    }

    private boolean checkUser(User user, String role) {
        if(user != null){
            List<User> users = this.userRepository.getAll();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getLogin().equals(user.getLogin())) {
                    if (users.get(i).getPass().equals(user.getPass())) {
                        if (user.getRole().equals(role))
                            return true;
                    }
                }
            }
        }
        return false;
    }


    //**************************************** INSERT RECORD
    @RequestMapping(value = "addNewMedicinecard", method = RequestMethod.GET)
    public String addNewMedicinecard(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("medicinecards", this.medicinecardRepository.getAll());
        model.addAttribute("medicinecard", new Medicinecard());
        return "addNewMedicinecard";
    }

    @RequestMapping(value = "addNewMedicinecard", method = RequestMethod.POST)
    public String addNewMedicinecard(@ModelAttribute("medicinecard") Medicinecard medicinecard, BindingResult bindingResult,
                                     HttpSession httpSession, Model model) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.medicinecardValidator.setMedicinecardRepository(this.medicinecardRepository);
        this.medicinecardValidator.validate(medicinecard, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("medicinecards", this.medicinecardRepository.getAll());
            return "addNewMedicinecard";
        }
        this.medicinecardRepository.addMedicinecard(medicinecard);
        return addNewMedicinecard(model, httpSession);
    }

    @RequestMapping(value = "addNewApparatus", method = RequestMethod.GET)
    public String addNewApparatus(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("apparatus", new Apparatus());
        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
        return "addNewApparatus";
    }

    @RequestMapping(value = "addNewApparatus", method = RequestMethod.POST)
    public String addNewApparatus(@ModelAttribute("apparatus") Apparatus apparatus, Model model,
                                  HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.apparatusValidator.setApparatusRepository(this.apparatusRepository);
        this.apparatusValidator.validate(apparatus, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addNewApparatus";
        }
        this.apparatusRepository.addApparatus(apparatus);
        return addNewApparatus(model, httpSession);
    }

    @RequestMapping(value = "addNewRefferal", method = RequestMethod.GET)
    public String addNewRefferal(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("refferal", new RefferalDictionary());
        model.addAttribute("refferals", this.refferalDictionaryRepository.getAll());
        return "addNewRefferal";
    }

    @RequestMapping(value = "addNewRefferal", method = RequestMethod.POST)
    public String addNewRefferal(@ModelAttribute("refferal") RefferalDictionary refferalDictionary,
                                 Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.refferalValidator.setRefferalDictionaryRepository(refferalDictionaryRepository);
        this.refferalValidator.validate(refferalDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addNewRefferal";
        }
        this.refferalDictionaryRepository.addNewRefferal(refferalDictionary);
        return addNewRefferal(model, httpSession);
    }

    @RequestMapping(value = "addNewDisinfectantSolution", method = RequestMethod.GET)
    public String addNewDisinfectantSolution(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("disinfectantSolution", new DisinfectantSolution());
        model.addAttribute("disinfectantSolutions", this.disinfectantSolutionRepository.getAll());
        return "addNewDisinfectantSolution";
    }


    @RequestMapping(value = "addNewDisinfectantSolution", method = RequestMethod.POST)
    public String addNewDisinfectantSolution(@ModelAttribute("disinfectantSolution") DisinfectantSolution disinfectantSolution,
                                             Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.disinfectantSolutionValidator.setDisinfectantSolutionRepository(disinfectantSolutionRepository);
        this.disinfectantSolutionValidator.validate(disinfectantSolution, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addNewDisinfectantSolution";
        }
        this.disinfectantSolutionRepository.addDisinfectantSolution(disinfectantSolution);
        return addNewDisinfectantSolution(model, httpSession);
    }

    @RequestMapping(value = "addNewRevisionType", method = RequestMethod.GET)
    public String addNewRevisionType(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("revisionType", new Revisiontype());
        model.addAttribute("revisionTypes", this.revisionTypeRepository.getAll());
        model.addAttribute("organs", this.organRepository.getAll());
        return "addNewRevisionType";
    }

    @RequestMapping(value = "addNewRevisionType", method = RequestMethod.POST)
    public String addNewRevisionType(@ModelAttribute("revisionType") Revisiontype revisiontype, Model model,
                                     HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.revisionTypeValidator.setRevisionTypeRepository(revisionTypeRepository);
        this.revisionTypeValidator.validate(revisiontype, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("revisionType", new Revisiontype());
            model.addAttribute("revisionTypes", this.revisionTypeRepository.getAll());
            model.addAttribute("organs", this.organRepository.getAll());
            return "addNewRevisionType";
        }
        this.revisionTypeRepository.addRevisionType(revisiontype);
        return addNewRevisionType(model, httpSession);
    }

    @RequestMapping(value = "addNewOrgan", method = RequestMethod.GET)
    public String addNewOrgan(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("revisionTypes", this.revisionTypeRepository.getAll());
        List<OrganDictionary> organs = unique(this.organRepository.getAll());
        model.addAttribute("uniqueOrgans", organs);
        model.addAttribute("organs", this.organRepository.getAll());
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("newOrgan", new OrganDictionary());
        return "organs";
    }

    @RequestMapping(value = "addNewOrgan", method = RequestMethod.POST)
    public String addNewOrgan(@ModelAttribute("newOrgan") OrganDictionary newOrgan, Model model, HttpSession httpSession,
                              BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.organValidator.setOrganRepository(organRepository);
        this.organValidator.validate(newOrgan, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("revisionTypes", this.revisionTypeRepository.getAll());
            model.addAttribute("organs", this.organRepository.getAll());
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("newOrgan", new OrganDictionary());
            return "organs";
        }
        this.organRepository.addNewOrgan(newOrgan);
        return addNewOrgan(model, httpSession);
    }

    @RequestMapping(value = "addNewRegion", method = RequestMethod.GET)
    public String addNewRegion(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("organs", unique(this.organRepository.getAll()));
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("terms", this.termRepository.getAll());
        model.addAttribute("newRegion", new RegionDictionary());
        return "regions";
    }

    @RequestMapping(value = "addNewRegion", method = RequestMethod.POST)
    public String addNewRegion(@ModelAttribute("newRegion") RegionDictionary newRegion, Model model,
                               HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.regionValidator.setRegionRepository(regionRepository);
        this.regionValidator.validate(newRegion, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("organs", unique(this.organRepository.getAll()));
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("terms", this.termRepository.getAll());
            model.addAttribute("newRegion", new RegionDictionary());
            return "regions";
        }
        this.regionRepository.addNewRegion(newRegion);
        return addNewRegion(model, httpSession);
    }

    @RequestMapping(value = "addNewTerm", method = RequestMethod.GET)
    public String addNewTerm(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("terms", this.termRepository.getAll());
        model.addAttribute("features", this.featuresRepository.getAll());
        model.addAttribute("organs", this.organRepository.getAll());
        model.addAttribute("newTerm", new TermDictionary());
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        return "terms";
    }

    @RequestMapping(value = "addNewTerm", method = RequestMethod.POST)
    public String addNewTerm(@ModelAttribute("newTerm") TermDictionary newTerm, Model model,
                             HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.termValidator.setTermRepository(termRepository);
        this.termValidator.validate(newTerm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("terms", this.termRepository.getAll());
            model.addAttribute("features", this.featuresRepository.getAll());
            model.addAttribute("organs", this.organRepository.getAll());
            model.addAttribute("newTerm", new TermDictionary());
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            return "terms";
        }
        this.termRepository.addNewTerm(newTerm);
        return addNewTerm(model, httpSession);
    }

    @RequestMapping(value = "addNewFeature", method = RequestMethod.GET)
    public String addNewFeature(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("terms", this.termRepository.getAll());
        model.addAttribute("features", this.featuresRepository.getAll());
        model.addAttribute("characteristics", this.characteristicRepository.getAll());
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("organs", this.organRepository.getAll());
        model.addAttribute("newFeature", new FeaturesDictionary());
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        return "features";
    }

    @RequestMapping(value = "addNewFeature", method = RequestMethod.POST)
    public String addNewFeature(@ModelAttribute("newFeature") FeaturesDictionary newFeature, Model model,
                                HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.featuresValidator.setFeaturesRepository(featuresRepository);
        this.featuresValidator.validate(newFeature, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("terms", this.termRepository.getAll());
            model.addAttribute("features", this.featuresRepository.getAll());
            model.addAttribute("characteristics", this.characteristicRepository.getAll());
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("organs", this.organRepository.getAll());
            model.addAttribute("newFeature", new FeaturesDictionary());
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            return "features";
        }
        this.featuresRepository.addNewFeature(newFeature);
        return addNewFeature(model, httpSession);
    }

    @RequestMapping(value = "addNewCharacteristic", method = RequestMethod.GET)
    public String addNewCharacteristic(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("terms", this.termRepository.getAll());
        model.addAttribute("features", this.featuresRepository.getAll());
        model.addAttribute("characteristics", this.characteristicRepository.getAll());
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("organs", this.organRepository.getAll());
        model.addAttribute("newCharacteristic", new CharacteristicDictionary());
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        return "characteristics";
    }

    @RequestMapping(value = "addNewCharacteristic", method = RequestMethod.POST)
    public String addNewCharacteristic(@ModelAttribute("newCharacteristic") CharacteristicDictionary characteristicDictionary,
                                       Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.characteristicValidator.setCharacteristicRepository(characteristicRepository);
        this.characteristicValidator.validate(characteristicDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("terms", this.termRepository.getAll());
            model.addAttribute("features", this.featuresRepository.getAll());
            model.addAttribute("characteristics", this.characteristicRepository.getAll());
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("organs", this.organRepository.getAll());
            model.addAttribute("newCharacteristic", new CharacteristicDictionary());
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            return "characteristics";
        }
        this.characteristicRepository.addNewCharacteristic(characteristicDictionary);
        return addNewCharacteristic(model, httpSession);
    }

    @RequestMapping(value = "addNewConlusion", method = RequestMethod.GET)
    public String addNewConclusion(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        model.addAttribute("recomendations", this.recomendationRepository.getAllRecomendations());
        model.addAttribute("recomendationsDictionary", this.recomendationRepository.getAll());
        model.addAttribute("newConclusion", new ConclusionDictionary());
        model.addAttribute("newRecomendation", new Recomendations());
        return "addNewConclusion";
    }

    @RequestMapping(value = "addNewConclusion", method = RequestMethod.POST)
    public String addNewConclusion(@ModelAttribute("newConclusion") ConclusionDictionary conclusionDictionary, Model model,
                                   HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.conclusionValidator.setConclusionRepository(conclusionRepository);
        this.conclusionValidator.validate(conclusionDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            model.addAttribute("recomendations", this.recomendationRepository.getAllRecomendations());
            model.addAttribute("recomendationsDictionary", this.recomendationRepository.getAll());
            return "addNewConclusion";
        }
        this.conclusionRepository.save(conclusionDictionary);
        return addNewConclusion(model, httpSession);
    }

    @RequestMapping(value = "addNewRecomendation", method = RequestMethod.GET)
    public String addNewRecomendation(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("recomendationDictionary", this.recomendationRepository.getAll());
        model.addAttribute("newRecomendation", new RecomendationDictionary());
        return "addNewRecomendation";
    }

    @RequestMapping(value = "addNewRecomendation", method = RequestMethod.POST)
    public String addNewRecomendation(@ModelAttribute("newRecomendation") RecomendationDictionary recomendationDictionary,
                                      Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.recomendationValidator.setRecomendationDictionary(recomendationRepository);
        this.recomendationValidator.validate(recomendationDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("recomendationDictionary", this.recomendationRepository.getAll());
            return "addNewRecomendation";
        }
        this.recomendationRepository.addNewRecomendation(recomendationDictionary);
        return addNewRecomendation(model, httpSession);
    }

    @RequestMapping(value = "addNewManipulation", method = RequestMethod.GET)
    public String addNewManipulation(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("manipulations", this.manipulationRepository.getDictionary());
        model.addAttribute("newManipulation", new ManipulationDictionary());
        return "addNewManipulation";
    }

    @RequestMapping(value = "addNewManipulation", method = RequestMethod.POST)
    public String addNewManipulation(@ModelAttribute("newManipulation") ManipulationDictionary manipulationDictionary, Model model,
                                     HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.manipulationValidator.setManipulationRepository(manipulationRepository);
        this.manipulationValidator.validate(manipulationDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("manipulations", this.manipulationRepository.getDictionary());
            return "addNewManipulation";
        }
        this.manipulationRepository.save(manipulationDictionary);
        return addNewManipulation(model, httpSession);
    }

    @RequestMapping(value = "addNewOperation", method = RequestMethod.GET)
    public String addNewOperation(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("operations", this.operationRepository.getAll());
        model.addAttribute("newOperation", new OperationDictionary());
        return "addNewOperation";
    }

    @RequestMapping(value = "addNewOperation", method = RequestMethod.POST)
    public String addNewOperation(@ModelAttribute("newOperation") OperationDictionary operationDictionary, Model model,
                                  HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.operationValidator.setOperationRepository(operationRepository);
        this.operationValidator.validate(operationDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("operations", this.operationRepository.getAll());
            return "addNewOperation";
        }
        this.operationRepository.save(operationDictionary);
        return addNewOperation(model, httpSession);
    }


    //********************************DELETE RECORD
    @RequestMapping(value = "deleteRevisionType/{id}", method = RequestMethod.GET)
    public String deleteRevisionType(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.revisionTypeRepository.deleteRevisionTypeById(id);
        return addNewRevisionType(model, httpSession);
    }

    @RequestMapping(value = "deleteOrgan/{id}", method = RequestMethod.GET)
    public String deleteOrgan(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.organRepository.deleteOrganById(id);
        return addNewOrgan(model, httpSession);
    }

    @RequestMapping(value = "deleteRegion/{id}", method = RequestMethod.GET)
    public String deleteRegion(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.regionRepository.deleteRegionById(id);
        return addNewRegion(model, httpSession);
    }

    @RequestMapping(value = "deleteTerm/{id}", method = RequestMethod.GET)
    public String deleteTerm(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.termRepository.deleteTermById(id);
        return addNewTerm(model, httpSession);
    }

    @RequestMapping(value = "deleteFeature/{id}", method = RequestMethod.GET)
    public String deleteFeature(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.featuresRepository.deleteFeatureById(id);
        return addNewFeature(model, httpSession);
    }

    @RequestMapping(value = "deleteCharacteristic/{id}", method = RequestMethod.GET)
    public String deleteCharacteristic(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.characteristicRepository.deleteCharacteristicById(id);
        return addNewCharacteristic(model, httpSession);
    }

    @RequestMapping(value = "deleteApparatus/{id}", method = RequestMethod.GET)
    public String deleteApparatus(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.revisionsRepository.getCountByApparatusId(id) == 0) {
            this.apparatusRepository.deleteApparatus(id);
        }
        return addNewApparatus(model, httpSession);
    }

    @RequestMapping(value = "deleteDisinfectantSolution/{id}", method = RequestMethod.GET)
    public String deleteDisinfectantSolution(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.revisionsRepository.getCountByDisinfectantSolutionId(id) == 0) {
            this.disinfectantSolutionRepository.delete(id);
        }
        return addNewDisinfectantSolution(model, httpSession);
    }

    @RequestMapping(value = "deleteRefferal/{id}", method = RequestMethod.GET)
    public String deleteRefferal(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.revisionsRepository.getCountRefferalId(id) == 0) {
            this.refferalDictionaryRepository.delete(id);
        }
        return addNewRefferal(model, httpSession);
    }

    @RequestMapping(value = "deleteConclusion/{id}", method = RequestMethod.GET)
    public String deleteConclusion(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.conclusionRepository.getCountConclusions(id) == 0) {
            this.conclusionRepository.delete(id);
        }
        return addNewConclusion(model, httpSession);
    }

    @RequestMapping(value = "deleteMedicinecard/{id}", method = RequestMethod.GET)
    public String deleteMedicinecard(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.revisionsRepository.getCountMedicinecard(id) == 0) {
            this.medicinecardRepository.delete(id);
        }
        return addNewMedicinecard(model, httpSession);
    }

    @RequestMapping(value = "deleteRecomendation/{id}", method = RequestMethod.GET)
    public String deleteRecomendation(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.recomendationRepository.getCountRecomendation(id) == 0) {
            this.recomendationRepository.delete(id);
        }
        return addNewRecomendation(model, httpSession);
    }

    @RequestMapping(value = "deleteRecomendations/{id}", method = RequestMethod.GET)
    public String deleteRecomendations(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.recomendationRepository.deleteFromRecomendations(id);
        return addNewRecomendation(model, httpSession);
    }

    @RequestMapping(value = "deleteManipulation/{id}", method = RequestMethod.GET)
    public String deleteManipulation(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.manipulationRepository.getCountManipulation(id) == 0) {
            this.manipulationRepository.delete(id);
        }
        return addNewManipulation(model, httpSession);
    }

    @RequestMapping(value = "deleteOperation/{id}", method = RequestMethod.GET)
    public String deleteOperation(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        if (this.operationRepository.getCountOperationById(id) == 0)
            this.operationRepository.delete(id);
        return addNewOperation(model, httpSession);
    }


    @RequestMapping(value = "deleteManipulations/{id}/{rId}", method = RequestMethod.GET)
    public String deleteManipulations(@PathVariable Integer id, @PathVariable Integer rId,
                                      Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.manipulationRepository.deleteFromManipulations(id);
        return doctorController.detail(rId, model, httpSession);
    }

    @RequestMapping(value = "deleteConclusions/{id}/{rId}", method = RequestMethod.GET)
    public String deleteConclusions(@PathVariable Integer id, @PathVariable Integer rId,
                                    Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.conclusionRepository.deleteFromConclusions(id);
        return doctorController.detail(rId, model, httpSession);
    }

    @RequestMapping(value = "deleteOperations/{id}/{rId}", method = RequestMethod.GET)
    public String deleteOperations(@PathVariable Integer id, @PathVariable Integer rId,
                                   Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.operationRepository.deleteFromOperations(id);
        return doctorController.detail(rId, model, httpSession);
    }


    //************************************* UPDATE RECORD

    @RequestMapping(value = "editRevisionType/{id}", method = RequestMethod.GET)
    public String editRevisionType(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        Revisiontype revisiontype = this.revisionTypeRepository.getRevisionTypeById(id);
        if (revisiontype != null) {
            model.addAttribute("revisionType", revisiontype);
        } else {
            return addNewRevisionType(model, httpSession);
        }
        return "revisionType";
    }

    @RequestMapping(value = "execEditRevisionType", method = RequestMethod.POST)
    public String execEditRevisionType(@ModelAttribute("revisionType") Revisiontype revisiontype, Model model,
                                       HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.revisionTypeRepository.update(revisiontype);
        return addNewRevisionType(model, httpSession);
    }

    @RequestMapping(value = "editOrgan/{id}", method = RequestMethod.GET)
    public String editOrgan(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("organ", this.organRepository.getOrganById(id));
        model.addAttribute("revisionTypes", this.revisionTypeRepository.getAll());
        return "organ";
    }

    @RequestMapping(value = "execEditOrgan", method = RequestMethod.POST)
    public String execEditOrgan(@ModelAttribute("organ") OrganDictionary organDictionary, Model model,
                                HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.organValidator.setOrganRepository(this.organRepository);
        this.organValidator.validate(organDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("organ", this.organRepository.getOrganById(organDictionary.getId()));
            model.addAttribute("revisionTypes", this.revisionTypeRepository.getAll());
            return "organ";
        }
        this.organRepository.update(organDictionary);
        return addNewOrgan(model, httpSession);
    }

    @RequestMapping(value = "editRegion/{id}", method = RequestMethod.GET)
    public String editRegion(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("organs", unique(this.organRepository.getAll()));
        model.addAttribute("region", this.regionRepository.getRegionById(id));
        return "region";
    }

    @RequestMapping(value = "execEditRegion", method = RequestMethod.POST)
    public String execEditRegion(@ModelAttribute("region") RegionDictionary regionDictionary, Model model,
                                 HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.regionValidator.setRegionRepository(this.regionRepository);
        this.regionValidator.validate(regionDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("organs", unique(this.organRepository.getAll()));
            model.addAttribute("region", this.regionRepository.getRegionById(regionDictionary.getIdRegion()));
            return "region";
        }
        this.regionRepository.update(regionDictionary);
        return addNewRegion(model, httpSession);
    }

    @RequestMapping(value = "editTerm/{id}", method = RequestMethod.GET)
    public String editTerm(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        model.addAttribute("organs", unique(this.organRepository.getAll()));
        model.addAttribute("term", this.termRepository.getTermById(id));
        return "term";
    }

    @RequestMapping(value = "execEditTerm", method = RequestMethod.POST)
    public String execEditTerm(@ModelAttribute("term") TermDictionary termDictionary, Model model,
                               HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.termValidator.setTermRepository(termRepository);
        this.termValidator.validate(termDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            model.addAttribute("organs", unique(this.organRepository.getAll()));
            model.addAttribute("term", this.termRepository.getTermById(termDictionary.getIdTerm()));
            return "term";
        }
        this.termRepository.update(termDictionary);
        return addNewTerm(model, httpSession);
    }

    @RequestMapping(value = "editFeature/{id}", method = RequestMethod.GET)
    public String editFeature(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("organs", unique(this.organRepository.getAll()));
        model.addAttribute("terms", this.termRepository.getAll());
        model.addAttribute("feature", this.featuresRepository.getFeatureById(id));
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        return "feature";
    }

    @RequestMapping(value = "execEditFeature", method = RequestMethod.POST)
    public String execEditFeature(@ModelAttribute("feature") FeaturesDictionary featuresDictionary, Model model,
                                  HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.featuresValidator.setFeaturesRepository(featuresRepository);
        this.featuresValidator.validate(featuresDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("organs", unique(this.organRepository.getAll()));
            model.addAttribute("terms", this.termRepository.getAll());
            model.addAttribute("feature", this.featuresRepository.getFeatureById(featuresDictionary.getIdFeatures()));
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            return "feature";
        }
        this.featuresRepository.update(featuresDictionary);
        return addNewFeature(model, httpSession);
    }

    @RequestMapping(value = "editCharacteristic/{id}", method = RequestMethod.GET)
    public String editCharacteristic(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("organs", unique(this.organRepository.getAll()));
        model.addAttribute("regions", this.regionRepository.getAll());
        model.addAttribute("terms", this.termRepository.getAll());
        model.addAttribute("features", this.featuresRepository.getAll());
        model.addAttribute("characteristic", this.characteristicRepository.getCharacteristicById(id));
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        return "characteristic";
    }

    @RequestMapping(value = "execEditCharacteristic", method = RequestMethod.POST)
    public String execEditCharacteristic(@ModelAttribute("characteristic") CharacteristicDictionary characteristicDictionary,
                                         Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.characteristicValidator.setCharacteristicRepository(characteristicRepository);
        this.characteristicValidator.validate(characteristicDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("organs", unique(this.organRepository.getAll()));
            model.addAttribute("regions", this.regionRepository.getAll());
            model.addAttribute("terms", this.termRepository.getAll());
            model.addAttribute("features", this.featuresRepository.getAll());
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            return "characteristic";
        }
        this.characteristicRepository.update(characteristicDictionary);
        return addNewCharacteristic(model, httpSession);
    }

    @RequestMapping(value = "editApparatus/{id}", method = RequestMethod.GET)
    public String editApparatus(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("apparatus", this.apparatusRepository.getApparatusById(id));
        return "apparatus";
    }

    @RequestMapping(value = "execEditApparatus", method = RequestMethod.POST)
    public String execEditApparatus(@ModelAttribute("apparatus") Apparatus apparatus, Model model,
                                    HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.apparatusValidator.setApparatusRepository(apparatusRepository);
        this.apparatusValidator.validate(apparatus, bindingResult);
        if (bindingResult.hasErrors()) {
            return "apparatus";
        }
        this.apparatusRepository.update(apparatus);
        return addNewApparatus(model, httpSession);
    }

    @RequestMapping(value = "editDisinfectantSolution/{id}", method = RequestMethod.GET)
    public String editDisinfectantSolution(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("disinfectantSolution", this.disinfectantSolutionRepository.getDisinfectantSolutionById(id));
        return "disinfectantSolution";
    }

    @RequestMapping(value = "execEditDisinfectantSolution", method = RequestMethod.POST)
    public String execEditDisinfectantSolution(@ModelAttribute("disinfectantSolution") DisinfectantSolution disinfectantSolution,
                                               Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.disinfectantSolutionValidator.setDisinfectantSolutionRepository(disinfectantSolutionRepository);
        this.disinfectantSolutionValidator.validate(disinfectantSolution, bindingResult);
        if (bindingResult.hasErrors()) {
            return "disinfectantSolution";
        }
        this.disinfectantSolutionRepository.update(disinfectantSolution);
        return addNewDisinfectantSolution(model, httpSession);
    }

    @RequestMapping(value = "editRefferal/{id}", method = RequestMethod.GET)
    public String editRefferal(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("refferal", this.refferalDictionaryRepository.getRefferalById(id));
        return "refferal";
    }

    @RequestMapping(value = "execEditRefferal", method = RequestMethod.POST)
    public String execEditRefferal(@ModelAttribute("refferal") RefferalDictionary refferalDictionary, Model model,
                                   HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.refferalValidator.setRefferalDictionaryRepository(refferalDictionaryRepository);
        this.refferalValidator.validate(refferalDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            return "refferal";
        }
        this.refferalDictionaryRepository.update(refferalDictionary);
        return addNewRefferal(model, httpSession);
    }

    @RequestMapping(value = "editConclusion/{id}", method = RequestMethod.GET)
    public String editConclusion(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("conclusion", this.conclusionRepository.getConclusionById(id));
        model.addAttribute("recomendation", this.recomendationRepository.getAll());
        return "conclusion";
    }

    @RequestMapping(value = "execEditConclusion", method = RequestMethod.POST)
    public String execEditConclusion(@ModelAttribute("conclusion") ConclusionDictionary conclusionDictionary, Model model,
                                     HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.conclusionValidator.setConclusionRepository(conclusionRepository);
        this.conclusionValidator.validate(conclusionDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("recomendation", this.recomendationRepository.getAll());
            return "conclusion";
        }
        this.conclusionRepository.update(conclusionDictionary);
        return addNewConclusion(model, httpSession);
    }

    @RequestMapping(value = "editMedicinecard/{id}", method = RequestMethod.GET)
    public String editMedicinecard(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("medicinecard", this.medicinecardRepository.getMedicinecardById1(id));
        return "medicinecard";
    }

    @RequestMapping(value = "execEditMedicinecard", method = RequestMethod.POST)
    public String execEditMedicinecard(@ModelAttribute("medicinecard") Medicinecard medicinecard, Model model,
                                       HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        /*this.medicinecardValidator.setMedicinecardRepository(medicinecardRepository);
        this.medicinecardValidator.validate(medicinecard, bindingResult);
        if(bindingResult.hasErrors()){
            return "medicinecard";
        }*/
        this.medicinecardRepository.update(medicinecard);
        return addNewMedicinecard(model, httpSession);
    }

    @RequestMapping(value = "editRecomendation/{id}", method = RequestMethod.GET)
    public String editRecomendation(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("recomendation", this.recomendationRepository.getRecomendationById(id));
        model.addAttribute("recomendations", this.recomendationRepository.getRecomendations(id));
        model.addAttribute("conclusions", this.conclusionRepository.getAll());
        return "recomendation";
    }


    @RequestMapping(value = "execEditRecomendation", method = RequestMethod.POST)
    public String execEditRecomendation(@ModelAttribute("recomendation") RecomendationDictionary recomendationDictionary,
                                        Model model, HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.recomendationValidator.setRecomendationDictionary(recomendationRepository);
        this.recomendationValidator.validate(recomendationDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("recomendations", this.recomendationRepository.getRecomendations(recomendationDictionary.getId()));
            model.addAttribute("conclusions", this.conclusionRepository.getAll());
            return "recomendation";
        }
        this.recomendationRepository.update(recomendationDictionary);
        return addNewRecomendation(model, httpSession);
    }

    @RequestMapping(value = "editManipulation/{id}", method = RequestMethod.GET)
    public String editManipulation(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("manipulation", this.manipulationRepository.getManipulationById(id));
        return "manipulation";
    }

    @RequestMapping(value = "execEditManipulation", method = RequestMethod.POST)
    public String execEditManipulation(@ModelAttribute("manipulation") ManipulationDictionary manipulationDictionary, Model model,
                                       HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.manipulationValidator.setManipulationRepository(manipulationRepository);
        this.manipulationValidator.validate(manipulationDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            return "manipulation";
        }
        this.manipulationRepository.update(manipulationDictionary);
        return addNewManipulation(model, httpSession);
    }

    @RequestMapping(value = "editOperation/{id}", method = RequestMethod.GET)
    public String editOperation(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("operation", this.operationRepository.getOperationById(id));
        return "operation";
    }

    @RequestMapping(value = "execEditOperation", method = RequestMethod.POST)
    public String execEditOperation(@ModelAttribute("operation") OperationDictionary operationDictionary, Model model,
                                    HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.operationValidator.setOperationRepository(operationRepository);
        this.operationValidator.validate(operationDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            return "operation";
        }
        this.operationRepository.update(operationDictionary);
        return addNewOperation(model, httpSession);
    }

    @RequestMapping(value = "execEditManipulations", method = RequestMethod.POST)
    public String execEditManipulations(HttpServletRequest request, Model model,
                                        HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        String mDescription = request.getParameter("mDescription");
        String value = request.getParameter("value");
        this.manipulationRepository.update(id, mDescription, value);
        return doctorController.detail(Integer.parseInt(request.getParameter("idRevision")), model, httpSession);
    }

    @RequestMapping(value = "execEditOperations", method = RequestMethod.POST)
    public String execEditOperations(HttpServletRequest request, Model model,
                                     HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        String oDescription = request.getParameter("oDescription");
        this.operationRepository.update(id, oDescription);
        return doctorController.detail(Integer.parseInt(request.getParameter("idRevision")), model, httpSession);
    }

    //*************************************

    @RequestMapping(value = "bindOrganToRType/rType/{rType}/organ/{organ}", method = RequestMethod.GET)
    public String bindOrganToRType(Model model, HttpSession httpSession, @PathVariable Integer rType,
                                   @PathVariable Integer organ) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.organRepository.setNewRecord(organ, rType);
        return addNewRevisionType(model, httpSession);
    }

    @RequestMapping(value = "bindRecomendationToConclusion", method = RequestMethod.POST)
    public String bindRecomendationToConclusion(@ModelAttribute("newRecomendation") Recomendations recomendations, Model model,
                                                HttpSession httpSession, BindingResult bindingResult) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.recomendationRepository.setNewRecord(recomendations);
        return addNewConclusion(model, httpSession);
    }

    @RequestMapping(value = "takeManipulations/{mode}", method = RequestMethod.GET)
    public String takeManipulations(@PathVariable Integer mode, Model model,
                                    HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List<Manipulations> manipulations;
        if (mode == 0) {
            manipulations = this.manipulationRepository.getFullManipulations(((User) httpSession.getAttribute("user")).getId());
            model.addAttribute("modeText", 1);
            model.addAttribute("mode", 1);
        } else {
            manipulations = this.manipulationRepository.getManipulationsWithoutValue(((User) httpSession.getAttribute("user")).getId());
            model.addAttribute("modeText", 1);
            model.addAttribute("mode", 0);
        }
        model.addAttribute("list", manipulations);
        return "manipulations";
    }

    @RequestMapping(value = "updateManipulations", method = RequestMethod.POST)
    public String updateManipulations(@RequestParam("id") Integer id,
                                      @RequestParam("place") String place,
                                      @RequestParam("value") String value,
                                      Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.manipulationRepository.update(id, place, value);
        return takeManipulations(0, model, httpSession);
    }

    @RequestMapping(value = "deleteManipulations/{id}", method = RequestMethod.GET)
    public String deleteManipulations(@PathVariable("id") String id, Model model,
                                      HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.manipulationRepository.deleteFromManipulations(Integer.parseInt(id));
        return takeManipulations(0, model, httpSession);
    }

    @RequestMapping(value = "updateRevision", method = RequestMethod.POST)
    public String updateRevision(@RequestParam("id") Integer id,
                                 @RequestParam("description") String description,
                                 Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        this.revisionsRepository.update(id, description);
        return doctorController.detail(id, model, httpSession);
    }


    private List<OrganDictionary> unique(List<OrganDictionary> organs) {
        ArrayList<OrganDictionary> list = new ArrayList<OrganDictionary>();
        for (int i = 0; i < organs.size(); i++) {
            if (contains(list, organs.get(i)))
                list.add(organs.get(i));
        }
        return (List<OrganDictionary>) list;
    }

    private boolean contains(ArrayList<OrganDictionary> list, OrganDictionary organDictionary) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(organDictionary.getName()))
                return false;
        }
        return true;
    }


    @RequestMapping(value = "settings", method = RequestMethod.GET)
    public String settings(Model model, HttpSession httpSession){
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        Object[] objarr = (Object[])this.userRepository.getSettings(((User) httpSession.getAttribute("user")).getId());
        model.addAttribute("uId", objarr[0]);
        model.addAttribute("login", objarr[1]);
        model.addAttribute("pass", objarr[2]);
        model.addAttribute("pib", objarr[3]);
        model.addAttribute("workPlace", objarr[4]);
        model.addAttribute("apparatusId", objarr[5]);
        model.addAttribute("disinfectantSolutionId", objarr[6]);
        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
        model.addAttribute("solutions", this.disinfectantSolutionRepository.getAll());
        return "settings";
    }

    @RequestMapping(value = "settings", method = RequestMethod.POST)
    public String settings(HttpServletRequest request, Model model, HttpSession httpSession){
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        String sql = "Update user set ";
        Integer uId = Integer.parseInt(request.getParameter("uId"));
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String pib = request.getParameter("pib");
        String workPlace = request.getParameter("workPlace");
        sql += "pib = '" + pib + "', work_place = '" + workPlace+"'";
        if(oldPass != null && newPass != null){
            String pass = (String)((Object[])this.userRepository.getSettings(uId))[2];
            if(oldPass.equals(pass))
                sql += ", pass = '" + newPass + "' ";
        }
        this.userRepository.update(sql + " where id = " + uId);
        Integer apparatusId = Integer.parseInt(request.getParameter("apparatusId"));
        Integer disinfectantSolutionId = Integer.parseInt(request.getParameter("disinfectantSolutionId"));
        sql = "update settings set apparatus_id = '" + apparatusId + "', disinfectant_solution_id = '" + disinfectantSolutionId+"'";
        this.userRepository.update(sql + " where user_id = '" + uId+"'");
        return settings(model, httpSession);
    }


}

