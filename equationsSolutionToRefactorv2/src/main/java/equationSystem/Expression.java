package equationSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Expression {

	List<Term> termList;
	
	public Expression() {
		this.termList = new ArrayList<Term>();
	}
	
	boolean empty() {
		return this.termList.size()==0;
	}
	
	public void add(Term term){
		this.termList.add(term.clon());
	}
	
	public void add(Expression expresion) {
		for(Term term : expresion.termList){
			this.add(term.clon());
		}
	}
	
	public void multiply(float value){
		assert !this.empty();
		for(Term term : termList){
			term.multiply(value);
		}
	}

	public void simplify() {
		assert !this.empty();
		Term simplifiedConstant = new Constant(this.getValue());
		this.simplify(simplifiedConstant);

	}

	public void simplify(String name){
		assert this.getNameSet().contains(name);

		Term simplifiedVariable = new Variable(this.getValue(name),name);
		this.simplify(simplifiedVariable);
	}

	private void simplify(Term termSimplified){

		this.removeSimilarTerms(termSimplified);
		this.addTerm(termSimplified);
	}

	private void removeSimilarTerms(Term termSimplified){
		for (Iterator<Term> itTerm = termList.iterator(); itTerm.hasNext();) {
			Term term = itTerm.next();
			if (term.getName() == termSimplified.getName()) {
				itTerm.remove();
			}				
		}
	}

	private void addTerm(Term termSimplified){
		if (termSimplified.getValue()!=0 || 
				(termList.size() == 0 && termSimplified instanceof Constant)) {
			this.add(termSimplified);			
		}
	}

	public float getValue() {
		assert !this.empty();

		Term constantToGetValue = new Constant(0);
		return this.getValue(constantToGetValue);
	}

	public float getValue(String name) {
		assert this.getNameSet().contains(name);

		Term VariableToGetValue = new Variable(0,name);
		return this.getValue(VariableToGetValue);
	}

	private float getValue(Term termSimilarToGetValue) {
		float value = 0;
		for (Term term : termList ) {
			if (term.getName() == termSimilarToGetValue.getName()) {
				value = value + term.getValue();
			}
		}
		return value;
	}
	
	public Set<String> getNameSet() {
		assert !this.empty();
		return new NamesExpresionAnalyzer(termList).getNameSet();
	}
	
	public boolean hasName(String name) {
		assert !this.empty();
		for(Term term : termList){
			if (term.hasName(name)){
				return true;
			}
		}
		return false;
	}
	
	public void apply(String name, float value) {
		Term appliedVariable = new Variable(this.getValue(name)*value,name);
		this.removeSimilarTerms(appliedVariable);
		Term appliedConstant = new Constant(appliedVariable.getValue());
		this.addTerm(appliedConstant);
	}
		
	public boolean equal(Expression expressionToCompare) {

		boolean compareEquivalentExpressionsResult; 
		
		Set<Term> thisValues = new HashSet<Term>();
		Set<Term> comparedValues = new HashSet<Term>();

		thisValues = prepareSetOfSimplifiedTerms (this); 
		comparedValues = prepareSetOfSimplifiedTerms (expressionToCompare); 
				
		compareEquivalentExpressionsResult = 
				(isTermsInAnotherTerms (thisValues, comparedValues) &&
				isTermsInAnotherTerms (comparedValues, thisValues));
		
		return compareEquivalentExpressionsResult;		
	}

	private Set<Term> prepareSetOfSimplifiedTerms (Expression expressionToSimplify) {
		Set<Term> termsValues = new HashSet<Term>();
		
		if (expressionToSimplify.termList.size() != 0) {
			termsValues.add(new Constant(expressionToSimplify.getValue()));
			for (String name : expressionToSimplify.getNameSet()) {
				termsValues.add(new Variable(expressionToSimplify.getValue(name),name));
			}
		}
		return termsValues;
	}

	private boolean isTermsInAnotherTerms (Set<Term> setSource, Set<Term> setSubset ) {
		for (Term termThis : setSource ) {
			for (Term termCompare : setSubset) {
				if (termThis.getName() == termCompare.getName()) {
					if (termThis.getValue() != termCompare.getValue()) {
						return false;
					}
				}
			}
		}		
		return true;
	}
	
	public Expression clon(){
		assert !this.empty();
		Expression expresion = new Expression();
		for(Term term : this.termList){
			expresion.add(term.clon());
		}
		return expresion;
	}
	
	@Override
	public String toString(){
		String result = "";
		for(Term term : this.termList){
			result += " " + term.toString();
		}
		return result.trim();
	}
}
