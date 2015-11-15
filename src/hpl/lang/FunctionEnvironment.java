import java.util.*;


public class FunctionEnvironment{
	HashMap dictionary;
	FunctionEnvironment parent;
	HashMap<String,Function> functionTable;

	public FunctionEnvironment(){
		dictionary = new HashMap();
	}
}