package cc.chilipo.mw.chilipo_online_exam_system.lectures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {

    @Autowired
    private LectureRepository LectureRepository;

    @RequestMapping(value = "/lectures", method = RequestMethod.GET)
    public String getLectures() {
        return "Hello lectures";
    }

    @RequestMapping(value = "/lectures", method = RequestMethod.POST)
    public Lecture createLecture(@RequestBody Lecture lecture) {
        Lecture object = new Lecture();
        object.setEmail(lecture.getEmail());
        object.setPassword(lecture.getPassword());
        object.setFirstname(lecture.getFirstname());
        object.setLastname(lecture.getLastname());
        object.setUsername(lecture.getUsername());
        object.setPhoneNumber(lecture.getPhoneNumber());

        return LectureRepository.save(object);
    }
}
