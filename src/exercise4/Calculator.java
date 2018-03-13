package exercise4;

class Calculator {
	public String add(double nr1, double nr2) {
		return String.format("%.2f", (nr1+nr2));
	}
	
	public String subtract(double nr1, double nr2) {
		return String.format("%.2f", (nr1-nr2));
	}
	
	public String multiply(double nr1, double nr2) {
		return String.format("%.2f", (nr1*nr2));
	}
	
	public String divide(double nr1, double nr2) {
		return String.format("%.2f", (nr1/nr2));
	}
}
