package gov.nysenate.openleg.tests;

import gov.nysenate.openleg.Environment;
import gov.nysenate.openleg.model.Bill;
import gov.nysenate.openleg.util.Storage;

import java.io.File;
import java.io.IOException;

import org.junit.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BasicTests
{
	private Environment env = new Environment("/data/openleg/test_new_environment");
	private File sobiDirectory = new File("/home/kevin/openleg/OpenLegislation/src/test/resources/sobi");  // -- /legislation/src/test/resources/sobi??
	private Storage storage = new Storage(env.getStorageDirectory());
	
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
	
	@After
	public void clearSetup()
	{
		
	}
	
	@Test
	public void isBillInitiallyNull()
	{
		Bill bill = (Bill)storage.get("2011/bill/S6696-2011", Bill.class);
    	assertThat(bill, nullValue());
	}
	
	@Test
	public void doesBillExistsAfterProcessing()
	{
	   	File[] initialCommit = TestHelper.getFilesByName(sobiDirectory, "SOBI.D120311.T201049.TXT");
    	TestHelper.processFile(env, initialCommit);
    	Bill bill = (Bill)storage.get("2011/bill/S6696-2011", Bill.class);
    	assertThat(bill, notNullValue());
	}
	
	@Test
	public void isSponsorNameCorrect()
	{
	   	File[] initialCommit = TestHelper.getFilesByName(sobiDirectory, "SOBI.D120311.T201049.TXT");
    	TestHelper.processFile(env, initialCommit);
    	Bill bill = (Bill)storage.get("2011/bill/S6696-2011", Bill.class);
    	String expectedName = "NOZZOLIO";
    	String billSponsorName = bill.getSponsor().getFullname();
    	assertThat(billSponsorName, is(expectedName));

	}
	
	@Test
	public void doesBillTextExist()
	{
		File[] billTextSobi = TestHelper.getFilesByName(sobiDirectory, "SOBI.D120311.T201549.TXT");
		TestHelper.processFile(env, billTextSobi);
    	Bill bill = (Bill)storage.get("2011/bill/S6696-2011", Bill.class);
    	assertThat(bill.getFulltext(), notNullValue());
	}
	
}
