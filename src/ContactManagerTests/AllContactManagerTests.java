package ContactManagerTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ContactManagerTest.class, ContactTest.class,
		DataManagerTests.class, MeetingTest.class, PastMeetingTest.class })
public class AllContactManagerTests {

}
