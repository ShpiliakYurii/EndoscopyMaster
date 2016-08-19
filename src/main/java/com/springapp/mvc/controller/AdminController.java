package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.ApparatusRepository;
import com.springapp.mvc.repository.DisinfectantSolutionRepository;
import com.springapp.mvc.repository.TableRepository;
import com.springapp.mvc.repository.UserRepository;
import com.springapp.mvc.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Yurii on 16.10.2015.
 */
@Controller
public class AdminController extends TableController {

    private UserRepository userRepository;
    private UserValidator userValidator;
    private DoctorController doctorController;
    private ApparatusRepository apparatusRepository;
    private DisinfectantSolutionRepository disinfectantSolutionRepository;

    private boolean checkUser(User user, String role) {
        if (user != null) {
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getBooks() {
        return "index";
    }

    @Autowired
    public AdminController(UserRepository userRepository, UserValidator userValidator,
                           DoctorController doctorController, TableRepository tableRepository,
                           ApparatusRepository apparatusRepository, DisinfectantSolutionRepository disinfectantSolutionRepository) {
        super(tableRepository);
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.doctorController = doctorController;
        this.apparatusRepository = apparatusRepository;
        this.disinfectantSolutionRepository = disinfectantSolutionRepository;
        initTables();
    }



    @Override
    public void initTables() {
        DisinfectantSolution.Table t = new DisinfectantSolution.Table("apparatus", "Апарати", getTableRepository());
        t.addField(new ConclusionDictionary.Field("id", ConclusionDictionary.Field.TYPES.INT, "Ід", "", true, false));
        t.addField(new ConclusionDictionary.Field("name", ConclusionDictionary.Field.TYPES.VARCHAR, "Назва", "", false, true));
        try {
            t.addBeforeListener(CharacteristicDictionary.Events.class.getMethod("validateApparatus", DisinfectantSolution.Table.class));
        }catch (Exception e){}
        this.getTables().add(t);
        t = new DisinfectantSolution.Table("medicinecard1", "Пацієнти", getTableRepository());
        t.addField(new ConclusionDictionary.Field("id", ConclusionDictionary.Field.TYPES.INT, "Ід", "", true, false));
        t.addField(new ConclusionDictionary.Field("pib", ConclusionDictionary.Field.TYPES.VARCHAR, "ПІБ", "", false, true));
        t.addField(new ConclusionDictionary.Field("adress", ConclusionDictionary.Field.TYPES.VARCHAR, "Адреса", "", false, true));
        t.addField(new ConclusionDictionary.Field("burnDate", ConclusionDictionary.Field.TYPES.DATE, "Дата народження", "", false, true));
        t.addField(new ConclusionDictionary.Field("identifyCode", ConclusionDictionary.Field.TYPES.VARCHAR, "Ідентифікаційний код", "", false, true));
        try {
            t.addBeforeListener(CharacteristicDictionary.Events.class.getMethod("validateMedicinecard", DisinfectantSolution.Table.class));
        }catch (Exception e){}
        this.getTables().add(t);
        t = new DisinfectantSolution.Table("manipulations", "Маніпуляції", getTableRepository());
        ConclusionDictionary.Field f = new ConclusionDictionary.Field("id", ConclusionDictionary.Field.TYPES.INT, "ID", "", true, false);
        t.addField(f);
        f = new ConclusionDictionary.Field("value", ConclusionDictionary.Field.TYPES.VARCHAR, "Значення", "", false, true);
        t.addField(f);
        f = new ConclusionDictionary.Field("manipulation_dictionary_id", ConclusionDictionary.Field.TYPES.FK, "Назва маніпуляції", "", false, true);
        f.setFk("manipulation_dictionary", "id", "name");
        t.addField(f);
        f = new ConclusionDictionary.Field("revisions_id", ConclusionDictionary.Field.TYPES.FK, "Номер обстеження", "", false, true);
        f.setFk("revisions", "id", "id");
        t.addField(f);
        f = new ConclusionDictionary.Field("place", ConclusionDictionary.Field.TYPES.VARCHAR, "Опис", "", false, true);
        t.addField(f);
        this.getTables().add(t);
        t = new DisinfectantSolution.Table("manipulation_dictionary", "Словник маніпуляцій", getTableRepository());
        f = new ConclusionDictionary.Field("id", ConclusionDictionary.Field.TYPES.INT, "ID", "", true, false);
        t.addField(f);
        f = new ConclusionDictionary.Field("name", ConclusionDictionary.Field.TYPES.VARCHAR, "Назва маніпуляції", "", false, true);
        t.addField(f);
        t.setMainTemplate("Виконані маніпуляції", "manipulation_dictionary_id", "2", "id");
        t.setMainTemplateVisible(true);
        t.setMainTemplateCaption("Виконані маніпуляції");

        this.getTables().add(t);
    }



    @RequestMapping(value = "removeUser/{id}", method = RequestMethod.GET)
    public String removeUser(@PathVariable Integer id, Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "admin")) {
            return "index";
        }
        this.userRepository.removeUser(id);
        model.addAttribute("users", userRepository.getAll());
        model.addAttribute("user", new User());
        model.addAttribute("settings", new Settings());
        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
        model.addAttribute("solutions", this.disinfectantSolutionRepository.getAll());
        return "admin";
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, @RequestParam("apparatus") Integer idap,
                          @RequestParam("solution") Integer idS, BindingResult bindingResult,
                          Model model, HttpSession httpSession) {
        if (!checkUser(((User) httpSession.getAttribute("user")), "admin")) {
            return "index";
        }
        this.userValidator.setUserRepository(userRepository);
        this.userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userRepository.getAll());
            return "admin";
        }
        Integer uId = this.userRepository.addUser(user);
        Settings settings = new Settings();
        settings.setUserId(uId);
        settings.setApparatusId(idap);
        settings.setDisinfectantSolutionId(idS);
        this.userRepository.addSettings(settings);
        model.addAttribute("users", userRepository.getAll());
        model.addAttribute("user", new User());
        model.addAttribute("settings", new Settings());
        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
        model.addAttribute("solutions", this.disinfectantSolutionRepository.getAll());
        return "admin";
    }

    @RequestMapping(value = "validate", method = RequestMethod.POST)
    public String validate(@RequestParam("login") String login, @RequestParam("pass") String pass, Model model, HttpSession httpSession) {
        List<User> users = this.userRepository.getAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                if (users.get(i).getPass().equals(pass)) {
                    httpSession.setAttribute("user", users.get(i));
                    if (users.get(i).getRole().equals("admin")) {
                        model.addAttribute("users", userRepository.getAll());
                        model.addAttribute("user", new User());
                        model.addAttribute("settings", new Settings());
                        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
                        model.addAttribute("solutions", this.disinfectantSolutionRepository.getAll());
                        return "admin";
                    }
                    if (users.get(i).getRole().equals("doctor")) {
                        return doctorController.revisionList(model, httpSession);
                    }
                    if (users.get(i).getRole().equals("registry")) {
                        return "registry";
                    }
                }
            }
        }
        return "index";
    }

    @RequestMapping(value = "editUser/{id}", method = RequestMethod.GET)
    public String settings(@PathVariable Integer id, Model model, HttpSession httpSession){
        if (!checkUser(((User) httpSession.getAttribute("user")), "admin")) {
            return "index";
        }
        Object[] objarr = (Object[])this.userRepository.getSettings(id);
        model.addAttribute("uId", objarr[0]);
        model.addAttribute("login", objarr[1]);
        model.addAttribute("pass", objarr[2]);
        model.addAttribute("pib", objarr[3]);
        model.addAttribute("workPlace", objarr[4]);
        model.addAttribute("apparatusId", objarr[5]);
        model.addAttribute("disinfectantSolutionId", objarr[6]);
        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
        model.addAttribute("solutions", this.disinfectantSolutionRepository.getAll());
        return "editUser";
    }

    @RequestMapping(value = "execEditUser", method = RequestMethod.POST)
    public String settings(HttpServletRequest request, Model model, HttpSession httpSession){
        if (!checkUser(((User) httpSession.getAttribute("user")), "admin")) {
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
        model.addAttribute("users", userRepository.getAll());
        model.addAttribute("user", new User());
        model.addAttribute("settings", new Settings());
        model.addAttribute("apparatuses", this.apparatusRepository.getAll());
        model.addAttribute("solutions", this.disinfectantSolutionRepository.getAll());
        return "admin";
    }
}
