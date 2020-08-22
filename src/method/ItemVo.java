package method;

public class ItemVo {
	String cafe, coffee;
	int price, price_dc;
	
	double DC_RATE = 0.1;
	
//	public String getCafe() {
//		return cafe;
//	}
//	public void setCafe(String cafe) {
//		this.cafe = cafe;
//	}
	public String getCoffee() {
		return coffee;
	}
	public void setCoffee(String menu) {
		this.coffee = menu;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getDCPrice() {
		price_dc = price - (int)(DC_RATE*price);
		return price_dc;
	}
}
