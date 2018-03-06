package exercise3;

class ClientHandler extends Thread {
	
	public String result = "";
	public String operation = "";
	public String nr1 = "";
	public String nr2 = "";
	
	public ClientHandler(String operation, String nr1, String nr2) {
		this.operation = operation;
		this.nr1 = nr1;
		this.nr2 = nr2;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String res) {
		result = res;
	}
	
	public void run() {
		try {
			System.out.println(getName());
			BasicCalculator bc = new BasicCalculatorImplementation();
			setResult(bc.calculation(operation, nr1, nr2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
