import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Team_5_AnswersTest {

	@Test
	void testQ1() {
//		fail("Not yet implemented");
		
		String fileName = "AAPL-2017.csv";
		
		Team_5_Answers Q1Test = new Team_5_Answers(fileName);
		String Q1Actual = Q1Test.Q1();
		
		String[] parse = Q1Actual.split(",");
		
		assertEquals("177.199997", parse[0]);
		assertEquals("114.760002", parse[1]);
		assertEquals("0.676912344621514", parse[2]);
		
	}

	@Test
	void testQ2() {
		fail("Not yet implemented");
	}

	@Test
	void testQ3() {
		fail("Not yet implemented");
	}

	@Test
	void testQ4() {
		fail("Not yet implemented");
	}

	@Test
	void testQ5() {
		fail("Not yet implemented");
	}

	@Test
	void testQ6() {
		fail("Not yet implemented");
	}

	@Test
	void testQ7() {
		fail("Not yet implemented");
	}

	@Test
	void testQ8() {
		fail("Not yet implemented");
	}

}
