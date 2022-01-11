package cc.chilipo.mw.chilipo_online_exam_system.users;

import cc.chilipo.mw.chilipo_online_exam_system.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultRepository extends JpaRepository<User, Long> {

}
