package cc.chilipo.mw.chilipo_online_exam_system.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String getUser() {
        return "welcome course";
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public Course createCourse(@RequestBody Course course) {
        Course object = new Course();
        object.setCourse_name(course.getCourse_name());


        return courseRepository.save(object);
    }
}
