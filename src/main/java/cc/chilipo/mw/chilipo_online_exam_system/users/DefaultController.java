package cc.chilipo.mw.chilipo_online_exam_system.users;

import cc.chilipo.mw.chilipo_online_exam_system.users.DefaultRepository;
import cc.chilipo.mw.chilipo_online_exam_system.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DefaultController {

      @Autowired
      private DefaultRepository defaultRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUser() {
        return "welcome here";
    }

  // @RequestMapping(value = "/users", method = RequestMethod.POST)
   /* public User createUser(@RequestBody User user) {
        //User object = new User();
        object.setEmail(user.getEmail());
        object.setPassword(user.getPassword());
        object.setFirstname(user.getFirstname());
        object.setLastname(user.getLastname());
        object.setUsername(user.getUsername());
        object.setPhoneNumber(user.getPhoneNumber());

        return defaultRepository.save(object);
    }*/
}
