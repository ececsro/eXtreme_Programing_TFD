package equationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Expression {

	private List<Term> termList;
	
	public Expression() {
		this.termList = new ArrayList<Term>();
	}
	
	private boolean empty() {
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
	
	public void simplify(String name){
		assert this.getNameSet().contains(name);
		Expression expresion = new Expression();
		float value = 0;
		for(Term term : termList){
			if (term.hasName(name)){
				value += term.getValue();
			} else {
				expresion.add(term.clon());
			}
		}
		if (value!=0){
			expresion.add(new Variable(value, name));
		}
		this.termList = expresion.termList;
	}
	
	public void simplify() {
		assert !this.empty();
		Set<String> nameSet = this.getNameSet();
		Expression expresion = new Expression();
		float value = (float) 0.0;
		for(Term term : termList){
			if (term.hasName(nameSet)){
				expresion.add(term.clon());
			} else {
				value += term.getValue();
			}
		}
		if (value != 0 || expresion.termList.size()==0){
			expresion.add(new Constant(value));
		}
		this.termList = expresion.termList;
	}
	
	public float getValue(String name){
		assert !this.empty();
		for(Term term : termList){
			if (term.hasName(name)){
				return term.getValue();
			}
		}
		return 0;
	}
	
	public float getValue() {
		assert !this.empty();
		Set<String> nameSet = this.getNameSet();
		for(Term term : termList){
			if (!term.hasName(nameSet)){
				return term.getValue();
			}
		}
		return 0;
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
		Expression expresion = new Expression();
		Constant constant = null;
		for(Term term : termList){
			if (term.hasName(name)){
				constant = new Constant(value*term.getValue());
			} else {
				expresion.add(term.clon());
			}
		}
		expresion.termList.add(constant);
		this.termList = expresion.termList;
	}

	public boolean equal(Expression expresion) {
		if (this == expresion)
			return true;
		if (expresion == null)
			return false;
		if (this.termList.size() != expresion.termList.size())
			return false;
		for(int i=0; i<this.termList.size(); i++){
			if (!this.termList.get(i).equal(expresion.termList.get(i)))
				return false;
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
			result += term.toString();
		}
		return result;
	}
	
}
