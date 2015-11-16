package hpl.lang;

import java.util.*;
import hpl.sys.*;

public class PIRFunctionDef extends PIRStatement{
	String name;
	ArrayList<String> params;
	ArrayList<ASTExp<PIRExp>> painterList;
	PIRSequence body;

	public PIRFunctionDef(String name, ArrayList<String> ids, PIRSequence body,	ArrayList<ASTExp<PIRExp>> painters){
		this.name = name;
		this.params = ids;
		this.body = body;
		this.painterList = painters;
	}
	
	public String getName() {
		return name;
    }

    public ArrayList<String> getParameters() {
		return parameters;
    }

    public PIRSequence getBody() {
		return body;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws Exception {
		return v.visitPIRFuncDefinition(this, arg);
    }

    public String toString() {
	String ids;
	if (parameters.isEmpty())
	    ids = "";
	else {
	    ids = parameters.get(0);
	    for (int i = 1; i < parameters.size(); i++) {
		String id = parameters.get(i);
		ids += ", " + id;
	    }
	}
	return "def-painter" + name + "["+ painterList + "]"+"(" + ids + ")\n" + body.toString() + "\n";
    }


}