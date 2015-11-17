package hpl.sys;

import hpl.values.HPLFunction;
import hpl.values.Painter;
import java.util.ArrayList;

public class HPLContextualizer implements HPLContext {
	private PainterFrame currentFrame;
	private HPLFunction currentFunction;
    private HPLEnvironment<Painter> painterEnv;
    private HPLEnvironment<Double> numericalEnv;
    private HPLEnvironment<HPLFunction> functionEnv;
   

    //Initialize new Concrete HPLContext
    public HPLContextualizer(HPLEnvironment env, PainterFrame f , HPLFunction func){
        this.currentFrame = f;
        this.currentFunction = func;
        this.functionEnv = env;
    }

    /**
     * Resolve a frame relative to the current frame (coordinate system), and
     * make the result be the new current frame in a newly created context.
     * @param f The frame to be derived from the current frame.
     * @return a newly created context that has identical components as this one,
     * except that its current frame is the effective frame denoted by the given
     * one.
     */
    public HPLContext composeFrame(PainterFrame f){
    	return new HPLContextualizer(this.painterEnv,f,this.currentFunction);
    }

    /**
     * Create a new context in which the function environment is extended with
     * new bindings.
     * @param fParams The names to be bound in the new function frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new function environment,
     * but leaving all the other components of the context unchanged.
     */
    public HPLContext extendF(ArrayList<String> fParams, ArrayList<HPLFunction> args){
        return new HPLContextualizer(new HPLEnvironment(this.functionEnv,fParams,args),this.currentFrame,this.currentFunction);
    }

    /**
     * Create a new context in which the numerical environment is extended with
     * new bindings.
     * @param nParams The names to be bound in the new numerical environment
     * frame.
     * @param vals The corresponding values for the names
     * @return A newly created context containing the new numerical environment,
     * but leaving all the other components of the context unchanged.
     */
    public HPLContext extendN(ArrayList<String> nParams, ArrayList<Double> vals){
        return new HPLContextualizer(new HPLEnvironment(this.numericalEnv,nParams,vals),this.currentFrame,this.currentFunction);
    }

    /**
     * Create a new context in which the painter environment is extended with
     * new bindings.
     * @param pParams The names to be bound in the new painter environment frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new painter environment,
     * but leaving all the other components of the context unchanged.
     */
    public HPLContext extendP(ArrayList<String> pParams, ArrayList<Painter> args){
        return new HPLContextualizer(new HPLEnvironment(this.painterEnv,pParams,args),this.currentFrame,this.currentFunction);
    }

    /**
     * Lookup a reference to a HPL function.
     * @param name The identifier of the HPL function
     * @return The HPL function associated with the given name in this context
     * @throws HPLException if the name is not bound to a painter in this
     * context
     */
    public HPLFunction getF(String name) throws HPLException {
    	try{
            return functionEnv.get(name);
        }    	
    	catch(HPLException hple){
    		throw new HPLException("name unbound to a painter in this context",hple);
    	}

    }

    /**
     *
     * @return The (resultant) frame associated with this context.
     */
    public PainterFrame getFrame(){
    	return currentFrame;
    }

    /**
     * Lookup a reference to a number
     * @param name The identifier of the Double
     * @return The number associated with the given name in this context
     * @throws HPLException if the name is not bound to a number in this
     * context
     */
    public Double getN(String name) throws HPLException{
        try{
            return numericalEnv.get(name);
        }
        catch(HPLException hple){
            throw new HPLException("name unbound to a painter in this context",hple);
        }
    }

    /**
     *
     * @return The numerical environment associated with this context.
     */
    public HPLEnvironment<Double> getNumEnv(){
        return numericalEnv;
    }

    /**
     * Lookup a reference to a painter
     * @param name The identifier of the painter
     * @return The painter associated with the given name in this context
     * @throws HPLException if the name is not bound to a painter in this
     * context
     */
    public Painter getP(String name) throws HPLException{
        try{
            return painterEnv.get(name);
        }
        catch(HPLException hple){
            throw new HPLException("name unbound to a painter in this context",hple);
        }
    }

    /**
     * Store a binding for the given name to the given HPL function.
     * @param name The identifier of the binding
     * @param p The HPL function to be associated with the name
     */
    public void putF(String name, HPLFunction p){
        functionEnv.put(name,p);
    }

    /**
     * Store a binding for the given name to the given number.
     * @param name The identifier of the binding
     * @param n The numerical value to be associated with the name
     */
    public void putN(String name, Double n){
        numericalEnv.put(name,n);
    }

    /**
     * Store a binding for the given name to the given painter.
     * @param name The identifier of the binding
     * @param p The painter value to be associated with the name
     */
    public void putP(String name, Painter p){
        painterEnv.put(name,p);
    }
    
    
}

