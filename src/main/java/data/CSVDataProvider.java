package data;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.csvreader.CsvReader;

import utils.FBUtilConstants;

public class CSVDataProvider {
	
	private static String dataSourceFileExtension = ".csv";
	
	@DataProvider(name = "testData")
	public static Object[][]init(Method method)
	{
		Object[][]allResults = csvHashMapConverter(method);
		ArrayList<Object>enabledData = new ArrayList<Object>();
		
		for(int i = 0; i < allResults.length; i++)
		{
			HashMap<String,String>dataSet = (HashMap<String,String>)allResults[i][0];
			if(dataSet.get(FBUtilConstants.DATA_SET_ENABLED).equalsIgnoreCase("TRUE"))
			{
				enabledData.add(allResults[i][0]);
			}
		}
		Object[][] result = new Object[enabledData.size()][1];
		for(int i = 0; i < enabledData.size(); i++)
		{
			result[i][0] = enabledData.get(i);
		}
		return result;
	}
	
	protected static Object[][]csvHashMapConverter(Method method)
	{
		String filePath = DataSource.buildPathForMethod(method, dataSourceFileExtension);
		CsvReader reader = null;
		Object[][] object = null;
		
		List<HashMap<String,String>>hashMapList = new ArrayList<HashMap<String,String>>();
		
		try 
		{
			FileInputStream inputStream = new FileInputStream(filePath);
			InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8");
			reader = new CsvReader(inputReader);
			
			String[] headers = null;
			String[] values = null;
			
			if(reader.readHeaders())
			{
				headers = reader.getHeaders();
			}
			while(reader.readRecord())
			{
				Map<String,String> testData = new HashMap<String,String>();
				values = reader.getValues();
				
				if(headers.length != values.length)
				{
					System.out.println("Error: CSV file format is incorrect!");
				}
				
				int i = 0;
				for(String header: headers)
				{
					testData.put(header, values[i]);
					i++;
				}
				hashMapList.add((HashMap<String,String>)testData);
			}
			reader.close();
			int lstCnt = hashMapList.size();
			if(lstCnt == 0)
			{
				System.out.println("Error, CSV file: " + filePath + " is empty or was not formatted correctly!");
			}
			object = new Object[lstCnt][];
			for(int i = 0; i < lstCnt; i++)
			{
				object[i] = new Object[] {(HashMap<String,String>)hashMapList.get(i)};
			}
			}
			catch(Exception e)
			{
				System.out.println("File not found: " + filePath);
			}
			return object;
	}
}
