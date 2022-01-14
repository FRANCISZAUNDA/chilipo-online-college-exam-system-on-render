package cc.chilipo.mw.chilipo_online_exam_system.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String getUser() {
        return "welcome department";
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public Department createDepartment(@RequestBody Department department) {
        Department object = new Department();
        object.setDept_name(department.getDept_name());
        object.setDean(department.getDean());
        object.setBuilding(department.getBuilding());
        object.setRoom(department.getRoom());

        return departmentRepository.save(object);
    }
}
