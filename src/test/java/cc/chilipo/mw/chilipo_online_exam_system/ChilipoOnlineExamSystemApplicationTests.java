package cc.chilipo.mw.chilipo_online_exam_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "management.metrics.export.wavefront.enabled=false")
@SpringBootTest
class ChilipoOnlineExamSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
