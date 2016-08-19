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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user", "apparatus", "solution", "pacient", "revisionType", "revision"})
public class DoctorController {

    private RevisionTypeRepository revisionTypeRepository;
    private OrganRepository organRepository;
    private RegionRepository regionRepository;
    private TermRepository termRepository;
    private FeaturesRepository featuresRepository;
    private CharacteristicRepository characteristicRepository;
    private MedicinecardRepository medicinecardRepository;
    private ApparatusRepository apparatusRepository;
    private ManipulationRepository manipulationRepository;
    private ConclusionRepository conclusionRepository;
    private DisinfectantSolutionRepository disinfectantSolutionRepository;
    private RevisionsRepository revisionsRepository;
    private RefferalDictionaryRepository refferalDictionaryRepository;
    private UserRepository userRepository;
    private OperationRepository operationRepository;
    private RecomendationRepository recomendationRepository;

    @Autowired
    public DoctorController(RevisionTypeRepository revisionTypeRepository, OrganRepository organRepository,
                            RegionRepository regionRepository, TermRepository termRepository,
                            FeaturesRepository featuresRepository, CharacteristicRepository characteristicRepository,
                            MedicinecardRepository medicinecardRepository,
                            ApparatusRepository apparatusRepository, ManipulationRepository manipulationRepository,
                            ConclusionRepository conclusionRepository, DisinfectantSolutionRepository disinfectantSolutionRepository,
                            RevisionsRepository revisionsRepository, RefferalDictionaryRepository refferalDictionaryRepository,
                            UserRepository userRepository, OperationRepository operationRepository,
                            RecomendationRepository recomendationRepository) {
        this.revisionTypeRepository = revisionTypeRepository;
        this.organRepository = organRepository;
        this.regionRepository = regionRepository;
        this.termRepository = termRepository;
        this.featuresRepository = featuresRepository;
        this.characteristicRepository = characteristicRepository;
        this.medicinecardRepository = medicinecardRepository;
        this.apparatusRepository = apparatusRepository;
        this.manipulationRepository = manipulationRepository;
        this.conclusionRepository = conclusionRepository;
        this.disinfectantSolutionRepository = disinfectantSolutionRepository;
        this.revisionsRepository = revisionsRepository;
        this.refferalDictionaryRepository = refferalDictionaryRepository;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
        this.recomendationRepository = recomendationRepository;

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

    @RequestMapping(value = "newRevision", method = RequestMethod.GET)
    public String newRevision(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List<Revisiontype> revisiontypes = this.revisionTypeRepository.getAll();
        model.addAttribute("revisionTypeList", revisiontypes);
        List<Medicinecard> medicinecards = this.medicinecardRepository.getAll();
        model.addAttribute("medicinecards", medicinecards);
        List<Apparatus> apparatuses = this.apparatusRepository.getAll();
        model.addAttribute("apparatuses", apparatuses);
        List<DisinfectantSolution> disinfectantSolutions = this.disinfectantSolutionRepository.getAll();
        model.addAttribute("disinfectantSolutions", disinfectantSolutions);
        List<RefferalDictionary> refferalDictionaries = this.refferalDictionaryRepository.getAll();
        model.addAttribute("refferalDictionaries", refferalDictionaries);
        Object[] objarr = (Object [])this.userRepository.getSettings(((User) httpSession.getAttribute("user")).getId());
        model.addAttribute("defaultApparatus", (Integer)objarr[5]);
        model.addAttribute("defaultSolution", (Integer)objarr[6]);
        httpSession.setAttribute("revision", new Revisions());
        return "newRevision";
    }

    @RequestMapping(value = "revisionDescription", method = RequestMethod.POST)
    public String revisionDescription(Model model, @RequestParam("pacient") Integer pacient,
                                      @RequestParam("revisionType") Integer id, @RequestParam("apparat") Integer apparat,
                                      @RequestParam("solution") Integer solution, @RequestParam("refferal") Integer refferal,
                                      @ModelAttribute("revision") Revisions r, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List<OrganDictionary> organDictionaries = this.organRepository.getById(id);
        List<RegionDictionary> regionDictionaries = this.regionRepository.getAll();
        List<TermDictionary> termDictionaries = this.termRepository.getAll();
        List<FeaturesDictionary> featuresDictionaries = this.featuresRepository.getAll();
        List<CharacteristicDictionary> characteristicDictionaries = this.characteristicRepository.getAll();
        List<ManipulationDictionary> manipulationDictionaries = this.manipulationRepository.getDictionary();
        List<ConclusionDictionary> conclusionDictionaries = this.conclusionRepository.getAll();
        model.addAttribute("organs", organDictionaries);
        model.addAttribute("regions", regionDictionaries);
        model.addAttribute("terms", termDictionaries);
        model.addAttribute("features", featuresDictionaries);
        model.addAttribute("characteristics", characteristicDictionaries);
        model.addAttribute("description", "");
        model.addAttribute("pacient", this.medicinecardRepository.getMedicinecardById(pacient));
        model.addAttribute("manipulationDictionaries", manipulationDictionaries);
        model.addAttribute("conclusionDictionaries", conclusionDictionaries);
        model.addAttribute("operations", this.operationRepository.getAll());
        r.setApparatusId(apparat);
        r.setDisinfectantSolutionId(solution);
        r.setMedicinecardId(pacient);
        r.setRevisiontypeId(id);
        r.setRefferalDictionaryId(refferal);
        httpSession.setAttribute("apparatus", apparat);
        httpSession.setAttribute("solution", solution);
        httpSession.setAttribute("pacient", pacient);
        httpSession.setAttribute("revisionType", id);
        return "revisionDescription";
    }

    @RequestMapping(value = "completeRevision", method = RequestMethod.POST)
    public @ResponseBody String completeRevision(@RequestParam("description") String description, @RequestParam("firstPatalogy") Integer firstPatalogy, @ModelAttribute User user,
                                   @ModelAttribute("revision") Revisions r, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        r.setFirstPatalogy(firstPatalogy);
        r.setDescription(description);
        r.setDoctorId(user.getId());
        r.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
        System.out.println("Doctor: " + user.getPib());
        System.out.println("Apparat №" + r.getApparatusId() + " disinfection solution №" + r.getDisinfectantSolutionId());
        System.out.println("Revision type: " + r.getRevisiontypeId());
        System.out.println("Pacient: " + r.getMedicinecardId());
        System.out.println("Опис обстеження " + description);
        System.out.println("Date: " + r.getDate().toString());
        //save to DB
        return this.revisionsRepository.addRevision(r).toString();
    }


    @RequestMapping(value = "addManipulation", method = RequestMethod.POST)
    public void addManipulation(@RequestParam("index") Integer index, @RequestParam("place") String place,
                                @ModelAttribute("revision") Revisions r, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return;
        }
        Manipulations m = new Manipulations();
        m.setRevisionsId(r.getId());
        m.setPlace(place);
        m.setManipulationDictionaryId(index);
        this.manipulationRepository.addManipulation(m);
    }

    @RequestMapping(value = "addOperation", method = RequestMethod.POST)
    public void addOperation(@RequestParam("index") Integer index, @RequestParam("description") String description,
                             @ModelAttribute("revision") Revisions r, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return;
        }
        Operations operations = new Operations();
        operations.setRevisionsId(r.getId());
        operations.setDescription(description);
        operations.setOperationDictionaryId(index);
        this.operationRepository.addOperation(operations);
    }

    @RequestMapping(value = "addConclusion", method = RequestMethod.POST)
    public void addConclusion(@RequestParam("cId") Integer cId, @ModelAttribute("revision") Revisions r,
                              HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return;
        }
        System.out.println("Revision №" + r.getId());
        System.out.println("Заключення №" + cId + " Обстеження №" + r.getId());
        Conclusions c = new Conclusions();
        c.setRevisionsId(r.getId());
        c.setConclusionDictionaryId(cId);
        this.conclusionRepository.addConclusion(c);
    }

    @RequestMapping(value = "revisionList", method = RequestMethod.GET)
    public String revisionList(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List list = this.revisionsRepository.getAll(((User) httpSession.getAttribute("user")).getId());
        for (int i = 0; i < list.size(); i++) {
            Object[] objectarr = (Object[]) list.get(i);
            List manipulations = this.manipulationRepository.
                    getManipulationsForRevision((Integer) objectarr[0]);
            if (manipulations.size() == 0) {
                ((Object[]) list.get(i))[5] = "<br>Маніпуляції відсутні.<br>";
            } else {
                String str = "Маніпуляції:<br>";
                for (int j = 0; j < manipulations.size(); j++) {
                    Object[] m = (Object[]) manipulations.get(j);
                    str += (j + 1) + ". " + m[0] + "; опис: ";
                    if (m[1] != null)
                        str += m[1] + "; ";
                    else
                        str += "-; ";
                    if (m[2] != null) {
                        str += "Результат: " + m[2];
                    } else {
                        str += "Результат відсутній.";
                    }
                    str += "<br>";
                }
                ((Object[]) list.get(i))[5] = str;
            }
            List operations = this.operationRepository.
                    getOperationsForRevision((Integer) objectarr[0]);
            if (operations.size() == 0)
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + "<br>Операції відсутні.";
            else {
                String str = "Операції<br>";
                for (int j = 0; j < operations.size(); j++) {
                    Object[] m = (Object[]) operations.get(j);
                    str += (j + 1) + ". " + m[0] + "; опис: ";
                    if (m[2] != null) {
                        str += m[2] + "; ";
                    } else {
                        str += "-; ";
                    }
                    str += "<br>";
                }
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + str;
            }
            List conclusions = this.conclusionRepository.getConclusionForRevision((Integer) objectarr[0]);
            List recomendations = this.recomendationRepository.getRecomendationsForRevision((Integer) objectarr[0]);
            if (conclusions.size() == 0) {
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + "<br>Заключення відсутнє.<br>Рекомендації відсутні.<br>";
            } else {
                String str1 = "Заключення: ";
                String str2 = "";
                if (recomendations.size() == 0) {
                    str2 = "Рекомендації відсутні<br>";
                } else {
                    str2 = "Рекомендації: ";
                }
                for (int j = 0; j < conclusions.size(); j++) {
                    Object[] c = (Object[]) conclusions.get(j);
                    if (j == 0) {
                        str1 += c[0];
                    } else {
                        str1 += ", " + c[0];

                    }
                }
                for (int j = 0; j < recomendations.size(); j++) {
                    if (j == 0) {
                        str2 += recomendations.get(j);
                    } else {
                        str2 += ", " + recomendations.get(j);
                    }
                }
                str1 += ".";
                str2 += ".";
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + str1 + "<br>" + str2;
            }

        }
        List<Medicinecard> pacients = this.medicinecardRepository.getAll();
        model.addAttribute("revisions", list);
        model.addAttribute("pacients", pacients);
        java.util.Date d = new java.util.Date();
        model.addAttribute("d1", 1);
        model.addAttribute("d2", 1);
        model.addAttribute("rTypes", this.revisionTypeRepository.getAll());
        model.addAttribute("pacient", -1);
        return "revisionList";
    }

    @RequestMapping(value = "filteredRevisionList", method = RequestMethod.POST)
    public String filteredRevisionList(HttpServletRequest request,
                                       Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }

        String fDate = request.getParameter("fDate");
        String fPacient = request.getParameter("fPacient");
        String fRType = request.getParameter("fRType");
        String sql = "";
        if (fDate != null) {
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            Timestamp f = new Timestamp(Date.valueOf(from).getTime());
            Timestamp t = new Timestamp(Date.valueOf(to).getTime());
            sql += "and revisions.date >= '" + f + "' and revisions.date <= '" + t + "' ";
            model.addAttribute("fDate", 1);
            model.addAttribute("d1", f.getTime());
            model.addAttribute("d2", t.getTime());
        }
        if (fPacient != null) {
            Integer pacient = Integer.parseInt(request.getParameter("pacient"));
            sql += "and revisions.medicinecardId = '" + pacient + "' ";
            model.addAttribute("fPacient", 1);
            model.addAttribute("pacient", pacient);
        }
        if (fRType != null) {
            Integer rType = Integer.parseInt(request.getParameter("revisionType"));
            sql += "and revisions.revisiontypeId = '" + rType + "' ";
            model.addAttribute("fRType", 1);
            model.addAttribute("revisionType", rType);
        }
        sql += "ORDER BY revisions.date desc";

        List list = this.revisionsRepository.getAllByDateAndId(sql, ((User) httpSession.getAttribute("user")).getId());
        for (int i = 0; i < list.size(); i++) {
            Object[] objectarr = (Object[]) list.get(i);
            List manipulations = this.manipulationRepository.
                    getManipulationsForRevision((Integer) objectarr[0]);
            if (manipulations.size() == 0) {
                ((Object[]) list.get(i))[5] = "<br>Маніпуляції відсутні.<br>";
            } else {
                String str = "Маніпуляції:<br>";
                for (int j = 0; j < manipulations.size(); j++) {
                    Object[] m = (Object[]) manipulations.get(j);
                    str += (j + 1) + ". " + m[0] + "; опис: ";
                    if (m[1] != null)
                        str += m[1] + "; ";
                    else
                        str += "-; ";
                    if (m[2] != null) {
                        str += "Результат: " + m[2];
                    } else {
                        str += "Результат відсутній.";
                    }
                    str += "<br>";
                }
                ((Object[]) list.get(i))[5] = str;
            }
            List operations = this.operationRepository.
                    getOperationsForRevision((Integer) objectarr[0]);
            if (operations.size() == 0)
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + "<br>Операції відсутні.";
            else {
                String str = "Операції<br>";
                for (int j = 0; j < operations.size(); j++) {
                    Object[] m = (Object[]) operations.get(j);
                    str += (j + 1) + ". " + m[0] + "; опис: ";
                    if (m[2] != null) {
                        str += m[2] + "; ";
                    } else {
                        str += "-; ";
                    }
                    str += "<br>";
                }
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + str;
            }
            List conclusions = this.conclusionRepository.getConclusionForRevision((Integer) objectarr[0]);
            List recomendations = this.recomendationRepository.getRecomendationsForRevision((Integer) objectarr[0]);
            if (conclusions.size() == 0) {
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + "<br>Заключення відсутнє.<br>Рекомендації відсутні.<br>";
            } else {
                String str1 = "Заключення: ";
                String str2 = "";
                if (recomendations.size() == 0) {
                    str2 = "Рекомендації відсутні<br>";
                } else {
                    str2 = "Рекомендації: ";
                }
                for (int j = 0; j < conclusions.size(); j++) {
                    Object[] c = (Object[]) conclusions.get(j);
                    if (j == 0) {
                        str1 += c[0];
                    } else {
                        str1 += ", " + c[0];

                    }
                }
                for (int j = 0; j < recomendations.size(); j++) {
                    if (j == 0) {
                        str2 += recomendations.get(j);
                    } else {
                        str2 += ", " + recomendations.get(j);
                    }
                }
                str1 += ".";
                str2 += ".";
                ((Object[]) list.get(i))[5] = ((Object[]) list.get(i))[5] + str1 + "<br>" + str2;
            }
        }
        List<Medicinecard> pacients = this.medicinecardRepository.getAll();
        model.addAttribute("revisions", list);
        model.addAttribute("pacients", pacients);
        model.addAttribute("rTypes", this.revisionTypeRepository.getAll());
        return "revisionList";
    }


    @RequestMapping(value = "printRevision/{id}", method = RequestMethod.GET)
    public String printRevision(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List list = this.revisionsRepository.getRevision(id, ((User) httpSession.getAttribute("user")).getId());
        if (list.size() != 0) {
            Object[] objarr = (Object[]) list.get(0);
            model.addAttribute("solution", objarr[5].toString());
            model.addAttribute("revisionType", objarr[3].toString());
            model.addAttribute("date", objarr[1].toString());
            model.addAttribute("pacientPIB", objarr[4].toString());
            model.addAttribute("burnDate", objarr[7].toString());
            model.addAttribute("address", objarr[6].toString());
            model.addAttribute("description", objarr[2].toString());
            model.addAttribute("apparatus", objarr[10].toString());
            model.addAttribute("doctor", ((User) httpSession.getAttribute("user")).getPib());
            model.addAttribute("id", id);
            if (Integer.parseInt(objarr[11].toString()) == 0)
                model.addAttribute("firstPatalogy", "Ні");
            else
                model.addAttribute("firstPatalogy", "Так");
            List manipulations = this.manipulationRepository.getManipulationsForRevision(id);
            if (manipulations.size() == 0)
                model.addAttribute("manipulations", "Відсутні");
            else {
                String str = "";
                for (int j = 0; j < manipulations.size(); j++) {
                    Object[] m = (Object[]) manipulations.get(j);
                    str += (j + 1) + ". " + m[0] + "; опис: " + m[1] + "; ";
                    if (m[2] != null) {
                        str += "Результат: " + m[2];
                    } else {
                        str += "Результат відсутній.";
                    }
                    str += "<br>";
                }
                model.addAttribute("manipulations", str);
            }
            List operations = this.operationRepository.getOperationsForRevision(id);
            if (operations.size() == 0)
                model.addAttribute("operations", "Відсутні");
            else {
                String str = "";
                for (int j = 0; j < operations.size(); j++) {
                    Object[] m = (Object[]) operations.get(j);
                    str += (j + 1) + ". " + m[0] + "; опис: " + m[2] + "; ";
                    str += "<br>";
                }
                model.addAttribute("operations", str);
            }
            List conclusions = this.conclusionRepository.getConclusionForRevision(id);
            if (conclusions.size() == 0)
                model.addAttribute("conclusions", "Відсутні");
            else {
                String str = "";
                for (int j = 0; j < conclusions.size(); j++) {
                    Object[] m = (Object[]) conclusions.get(j);
                    str += m[0] + ". ";
                }
                model.addAttribute("conclusions", str);
            }
            List recomendations = this.recomendationRepository.getRecomendationsForRevision(id);
            if (conclusions.size() == 0)
                model.addAttribute("recomendations", "Відсутні");
            else {
                String str = "";
                for (int j = 0; j < recomendations.size(); j++) {
                    str += recomendations.get(j);
                }
                model.addAttribute("recomendations", str);
            }
        }
        return "printRevision";
    }


    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List list = this.revisionsRepository.getRevision(id, ((User) httpSession.getAttribute("user")).getId());
        if (list.size() >= 1) {
            Object[] objectarr = (Object[]) list.get(0);
            model.addAttribute("solution", objectarr[5]);
            List manipulations = this.manipulationRepository.getManipulationsForRevision((Integer) objectarr[0]);
            if (manipulations.size() == 0) {
                ((Object[]) list.get(0))[5] = "Відсутні";
            } else {
                String str = "";
                for (int j = 0; j < manipulations.size(); j++) {
                    Object[] m = (Object[]) manipulations.get(j);
                    str += (j + 1) + ". " + m[0] + "; Ділянка: " + m[1] + "; ";
                    if (m[2] != null) {
                        str += "Результат: " + m[2];
                    } else {
                        str += "Результат відсутній.";
                    }
                    str += "\n";
                }
                ((Object[]) list.get(0))[5] = str;
            }
            List conclusions = this.conclusionRepository.getConclusionForRevision((Integer) objectarr[0]);
            model.addAttribute("manipulations", manipulations);
            model.addAttribute("revisions", list);
            model.addAttribute("conclusions", conclusions);
            model.addAttribute("operations", this.operationRepository.
                    getOperationsForRevision((Integer) objectarr[0]));
            model.addAttribute("apparatus", objectarr[10]);
            List recomendations = this.recomendationRepository.getRecomendationsForRevision(id);
            if (conclusions.size() == 0)
                model.addAttribute("recomendations", "Відсутні");
            else {
                String str = "";
                for (int j = 0; j < recomendations.size(); j++) {
                    str += recomendations.get(j);
                }
                model.addAttribute("recomendations", str);
            }
            if (Integer.parseInt(objectarr[11].toString()) == 0)
                model.addAttribute("firstPatalogy", "Ні");
            else
                model.addAttribute("firstPatalogy", "Так");
        } else {
            return revisionList(model, httpSession);
        }
        return "detail";
    }

    //********************REPORTS**********
    @RequestMapping(value = "monthReport", method = RequestMethod.POST)
    public String monthReport(HttpServletRequest request,
                              Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        Integer year = Integer.parseInt((String) request.getParameter("month").substring(0, 4));
        Integer month = Integer.parseInt((String) request.getParameter("month").substring(5, 7));
        Integer mode = Integer.parseInt(request.getParameter("mode"));
        List<Revisiontype> revisiontypes = this.revisionTypeRepository.getAll();

        switch (mode) {
            case 0:
                if (month > 12 || month < 1) {
                    return "monthReport";
                }
                //revisiontypes.get(i).getId(),((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("month", month);
                model.addAttribute("year", year);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonth(month, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 0);
                return "monthReport";
            case 1:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(12, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 1);
                return "monthReport";
            case 2:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(3, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 2);
                return "monthReport";
            case 3:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(6, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 3);
                return "monthReport";
            case 4:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(9, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 4);
                return "monthReport";
            default:
                return monthReport(model, httpSession);
        }
    }


    @RequestMapping(value = "monthReport", method = RequestMethod.GET)
    public String monthReport(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("mode", 0);
        return "monthReport";
    }

    @RequestMapping(value = "yearReport", method = RequestMethod.GET)
    public String yearReport(Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("mode", 1);
        return "monthReport";
    }

    @RequestMapping(value = "quarter/{q}", method = RequestMethod.GET)
    public String quarter(@PathVariable Integer q, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        model.addAttribute("mode", q + 1);
        return "monthReport";
    }

    @RequestMapping(value = "printMonthReport/{year}/{month}/{mode}", method = RequestMethod.GET)
    public String printMonthReport(@PathVariable Integer year, @PathVariable Integer month,
                                   @PathVariable Integer mode,
                                   Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "doctor")) {
            return "index";
        }
        List<Revisiontype> revisiontypes = this.revisionTypeRepository.getAll();
        //revisiontypes.get(i).getId(),((User) httpSession.getAttribute("user")).getId()));
        switch (mode) {
            case 0:
                if (month > 12 || month < 1) {
                    return "monthReport";
                }
                //revisiontypes.get(i).getId(),((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("month", month);
                model.addAttribute("year", year);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonth(month, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 0);
                break;
            case 1:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(12, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 1);
                break;
            case 2:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(3, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 2);
                break;
            case 3:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(6, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 3);
                break;
            case 4:
                model.addAttribute("year", year);
                model.addAttribute("month", 0);
                model.addAttribute("data", this.revisionsRepository.getRevisionsForMonths(9, year, ((User) httpSession.getAttribute("user")).getId()));
                model.addAttribute("revisiontypes", revisiontypes);
                model.addAttribute("mode", 4);
                break;
            default:
                break;
        }
        return "print";
    }

}


