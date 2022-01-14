package cc.chilipo.mw.chilipo_online_exam_system.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmins() {
        return "Hello adminn";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Admin createStudent(@RequestBody Admin admin) {
        Admin object = new Admin();
        object.setEmail(admin.getEmail());
        object.setPassword(admin.getPassword());
        object.setFirstname(admin.getFirstname());
        object.setLastname(admin.getLastname());
        object.setUsername(admin.getUsername());
        object.setPhoneNumber(admin.getPhoneNumber());

        return adminRepository.save(object);
    }
}
