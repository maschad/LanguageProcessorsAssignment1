package src.hpl.lang;

public class PIRSubFrameExp extends PIRFrameExp {
 	ASTExp<AIRExp> oxExp, oyExp;
    ASTExp<AIRExp> uxExp, uyExp;
    ASTExp<AIRExp> vxExp, vyExp;


    public PIRSubFrameExp(ASTExp<AIRExp> ox, ASTExp<AIRExp> oy, ASTExp<AIRExp> ux ASTExp<AIRExp> vy) {
        this.oxExp = ox;
        this.oyExp = oy;
        this.uxExp = ux;
        this.uyExp = 0;
        this.vxExp = 0;
        this.vyExp = vy;
    }
    
}
