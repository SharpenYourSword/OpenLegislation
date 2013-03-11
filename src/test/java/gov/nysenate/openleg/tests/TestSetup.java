package gov.nysenate.openleg.tests;

import gov.nysenate.openleg.Environment;
import gov.nysenate.openleg.util.Storage;

import java.io.File;
import java.io.IOException;

import org.junit.*;

public abstract class TestSetup
{
	protected static Environment env;
	protected static File sobiDirectory;
	protected static Storage storage;
	
	@BeforeClass
	public static void initalSetup()
	{
		env = new Environment("/data/openleg/test_new_environment");
		sobiDirectory = new File("src/test/resources/sobi");
		storage = new Storage(env.getStorageDirectory());
	}
	
	@Before
	public void setup()
	{
		try {
			env.reset();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}