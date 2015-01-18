package essentialThaumaturgy.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ThaumcraftHelper {
	
	public static Object getField(String clazzName, String fieldName, Object instance)
	{
		try
		{
			Class tcClass = Class.forName(clazzName);
			Field declared = tcClass.getDeclaredField(fieldName);
			boolean accessed = declared.isAccessible();
			declared.setAccessible(true);
			Object retObj = declared.get(instance);
			declared.setAccessible(accessed);
			return retObj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object invokeVirtualMethod(String clazzName, String methodName, Object instance, Object... invokable)
	{
		try
		{
			Class tcClass = Class.forName(clazzName);
			Class[] classes = new Class[invokable.length];
			for(int i = 0; i < invokable.length; ++i)
			{
				classes[i] = invokable[i].getClass();
			}
			Method invoked = tcClass.getDeclaredMethod(methodName, classes);
			boolean accessed = invoked.isAccessible();
			invoked.setAccessible(true);
			Object retObj = invoked.invoke(instance, invokable);
			invoked.setAccessible(accessed);
			return retObj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
