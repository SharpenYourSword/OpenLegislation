package gov.nysenate.openleg.tests;

import gov.nysenate.openleg.Environment;
import gov.nysenate.openleg.model.Bill;
import gov.nysenate.openleg.model.Meeting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import gov.nysenate.openleg.util.Storage;

public class TestHelper 
{
	public static void processFile(Environment env, File[] testFiles)
	{
		try {
			env.stageFiles(testFiles);
			env.collateFiles(FileUtils.listFiles(env.getStagingDirectory(), null, true));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		env.ingestFiles(FileUtils.listFiles(env.getWorkingDirectory(), null, true));
	}
	public static void processFileC(Environment env, File testFiles)
	{
		try {
			env.stageFiles(testFiles);
			env.collateFiles(FileUtils.listFiles(env.getStagingDirectory(), null, true));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		env.ingestFiles(FileUtils.listFiles(env.getWorkingDirectory(), null, true));
	}


	public static File[] getFilesByName(File directory, String...names)
	{
		return getFilesByName(directory, Arrays.asList(names)).toArray(new File[]{});
	}

	public static Collection<File> getFilesByName(File directory, Collection<String> names)
	{
		Collection<File> files = new ArrayList<File>();
		for (String name : names) {
			File file = new File(directory, name);
			files.add(file);
		}
		return files;
	}
	public static Collection<File> getFilesByNameCollection(File directory, String...names)
	{
		return getFilesByName(directory, Arrays.asList(names));
	}

	public static Bill getBill(Storage storage, String billKey)
	{
		Bill bill = (Bill)storage.get(billKey, Bill.class);
		return bill;
	}

	public static Meeting getMeeting(Storage storage, String meetingKey)
	{
		Meeting meeting = (Meeting)storage.get(meetingKey, Meeting.class);
		return meeting;
	}

	public static Bill getBillByName(List<Bill> bills, String billName)
	{
		Bill bill = null;
		for(Bill eachBill: bills){
			if(eachBill.getSenateBillNo().equalsIgnoreCase(billName)){
				bill = eachBill;
			}
		}
		return bill;
	}
}
