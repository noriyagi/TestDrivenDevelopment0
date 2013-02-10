package TDD;

class Money implements Expression {
	protected int amount;
	protected String currency;
	
	Money(int amount, String currency){
		this.amount = amount;
		this.currency = currency;
	}
	
	static Money dollar(int amount){
		return new Money(amount, "USD");
	}
	
	static Money franc(int amount){
		return new Money(amount, "CHF");
	}
	
	public Expression times(int multiplier){
		return new Money(amount * multiplier, currency);
	}
	
	String currency(){
		return currency;
	}
	
	public boolean equals(Object object){
		Money money = (Money) object;
		// 自分自身の通貨とmoneyオブジェクトの通貨とを比較する。
		return amount == money.amount
				&& currency().equals(money.currency());
	}
	
	public String toString(Object object){
		return amount + " " + currency;
	}
	
	public Expression plus(Expression addend){
		// return new Money(amount + addend.amount, currency);
		return new Sum(this, addend);
	}
	
	public Money reduce(Bank bank, String to){
		int rate = bank.rate(currency, to);
		return new Money(amount/rate, to);
	}

	
}
