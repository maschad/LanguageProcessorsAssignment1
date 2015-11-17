package hpl.lang;

import java.util.*;
import hpl.sys.*;

public class PIRFunctionDef extends PIRStatement{
	String name;
	ArrayList<String> params;
	ArrayList<String> painterList;
	PIRSequence body;

	public PIRFunctionDef(String name, ArrayList<String> ids, PIRSequence body,	ArrayList<String> painters){
		this.name = name;
		this.params = ids;
		this.body = body;
		this.painterList = painters;
	}
	
	public String getName() {
		return name;
    }

    public ArrayList<String> getArithParams() {
		return params;
    }

    public ArrayList<String> getPainterParams(){
    	return painterList;
    }


    public PIRSequence getBody() {
		return body;
    }

    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
		return v.visitPIRFuncDefinition(this, state);
    }

    public String toString() {
	String ids;
	if (params.isEmpty())
	    ids = "";
	else {
	    ids = params.get(0);
	    for (int i = 1; i < params.size(); i++) {
		String id = params.get(i);
		ids += ", " + id;
	    }
	}
	return "def-painter" + name + "["+ painterList + "]"+"(" + ids + ")\n" + body.toString() + "\n";
    }


}