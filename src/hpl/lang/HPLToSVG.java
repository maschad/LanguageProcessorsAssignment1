package hpl.lang;

import com.sun.xml.internal.bind.v2.TODO;
import hpl.comp.CompilerResult;
import hpl.comp.HPLCompiler;
import hpl.comp.SVGContext;
import hpl.comp.SVGDoc;
import hpl.sys.HPLComp;
import hpl.sys.HPLContext;
import hpl.sys.HPLException;

import java.util.ArrayList;

/**
 * Created by carlos on 11/22/15.
 */

public class HPLToSVG implements HPLVisitor<SVGContext,SVGDoc>,HPLCompiler{

    @Override
    public CompilerResult translate(PIRProgram prog) {
        return null;
    }

    @Override
    public String getTargetExtension() {
        return null;
    }

    @Override
    public SVGDoc visitPIRProgram(PIRProgram program, SVGContext arg) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRSequence(PIRSequence seq, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRAssignment(PIRAssignment assignment, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRPaintStmt(PIRPaintStmt paintStmt, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRWaitStmt(PIRWaitStmt waitStmt, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRImagePainter(PIRImagePainter exp, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRFunCall(PIRFunCall funCall, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitPIRFuncDefinition(PIRFunctionDef funcDefn, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitVar(ASTVar<PIRExp> var, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitUnaryExp(ASTUnaryExp<PIRExp> exp, SVGContext state) throws HPLException {
        return null;
    }

    @Override
    public SVGDoc visitBinaryExp(ASTBinaryExp<PIRExp> exp, SVGContext state) throws HPLException {
        return null;
    }
}