package hpl.comp;

import hpl.sys.HPLContext;
import hpl.sys.HPLContextualizer;
import hpl.sys.HPLEnvironment;
import hpl.sys.PainterFrame;
import hpl.values.HPLFunction;
import hpl.values.Painter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carlos on 12/1/15.
 */
public class SVGContext extends HPLContextualizer {
    private int width,height,margin;
    private HashMap<String,String> imgMap;
    private SVGDoc document;
    private SVGNode rootNode;
    private SVGNode painterEl,screenEl,functionEl;

    public SVGContext(HPLEnvironment<Painter> pEnv, PainterFrame f, HPLEnvironment<HPLFunction> fEnv, HPLEnvironment<Double> numEnv) {
        super(pEnv, f, fEnv, numEnv);
        imgMap = new HashMap<>();
        document = new SVGDoc(width + 2 * margin, height + 2 * margin);
        rootNode = document.getRoot();
        functionEl = rootNode.createChild("defs", "imageDefs");
        screenEl = rootNode.createChild("g", "screen");
        String shift = String.format("translate(%d, %d)", margin, margin);
        String enlarge = String.format("scale(%d, %d)", width, height);
        screenEl.setAttribute("transform", shift + " " + enlarge);
        screenEl.setAttribute("width", "100%");
        screenEl.setAttribute("height", "100%");
        screenEl.setAttribute("x", "0");
        screenEl.setAttribute("y", "0");
        SVGNode boundary = screenEl.createChild("rect", "bounding-frame");
        boundary.setAttribute("width", "1.0");
        boundary.setAttribute("height", "1.0");
        boundary.setAttribute("stroke", "blue");
        boundary.setAttribute("stroke-width", "0.015");
        painterEl = screenEl.createChild("g");
    }




}
