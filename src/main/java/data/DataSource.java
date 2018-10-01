package data;

import java.lang.reflect.Method;

public class DataSource {
	
	private static String dataSourcePath = "src/test/resources/";
	
	public static String buildPathForMethod(Method method, String sourceFileExtension)
	{
		String classFullName = method.getDeclaringClass().getName();
		
		String[] arr = classFullName.split("\\.");
		String packageName = arr[arr.length - 2];
		String className = arr[arr.length - 1];
		
		return dataSourcePath + className + "/" + method.getName() + sourceFileExtension;
	}

}
