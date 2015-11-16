import java.util.*;


public class FunctionEnvironment{
	HashMap dictionary;
	FunctionEnvironment parent;
	HashMap<String,Function> functionTable;

	public FunctionEnvironment(){
		dictionary = new HashMap();
		parent = null;
		functionTable = new HashMap<<String,Function>>();
	}

	public FunctionEnvironment(FunctionEnvironment e){
		dictionary = new HashMap();
		parent = e;
		functionTable = new HashMap<<String, Function>>();
	}

	public FunctionEnvironment(String[] ids, int[] values, FunctionEnvironment parent) {
		dictionary = new HashMap();
		this.parent = parent;
		functionTable = new HashMap<String, Function>();
		for (int i = 0; i < ids.length; i++) {
	    	put(ids[i], values[i]);
		}
    }

    public static FunctionEnvironment makeGlobalEnv(){
    	FunctionEnvironment result = new FunctionEnvironment();
    	return result;
    }


}