package org.encog.ml.prg.generator;

import java.util.Random;

import org.encog.ml.prg.EncogProgram;
import org.encog.ml.prg.EncogProgramContext;
import org.encog.ml.prg.ProgramNode;
import org.encog.ml.prg.extension.ProgramExtensionTemplate;

public class PrgFullGenerator extends AbstractPrgGenerator {
		
	public PrgFullGenerator(EncogProgramContext theContext, int theMaxDepth) {
		super(theContext, theMaxDepth);
	}

	@Override
	public ProgramNode createNode(Random rnd, EncogProgram program, int depthRemaining) {
				
		if( depthRemaining==0 ) {
			return createLeafNode(rnd, program);
		}
		
		ProgramExtensionTemplate temp = generateRandomOpcode(rnd, getFunctions());
		int childNodeCount = temp.getChildNodeCount();
		
		ProgramNode[] children = new ProgramNode[childNodeCount];
		for(int i=0;i<children.length;i++) {
			children[i] = createNode(rnd, program, depthRemaining-1);
		}
		
		ProgramNode result = new ProgramNode(program, temp, children);
		temp.randomize(rnd, result, getMinConst(), getMaxConst());
		return result;
	}
	
}