package application.model;

public class Sales {
	String dailySales;
	String dailyTaxes;
	String weeklySales;
	String weeklyTaxes;
	
	public Sales(String dailySales, String dailyTaxes, String weeklySales, String weeklyTaxes) {
		this.dailySales = dailySales;
		this.dailyTaxes = dailyTaxes;
		this.weeklySales = weeklySales;
		this.weeklyTaxes = weeklyTaxes;
	}
	
	public String getDailySales() {
		return dailySales;
	}
	
	public void setDailySales(String dailySales) {
		this.dailySales = dailySales;
	}
	
	public String getDailyTaxes() {
		return dailyTaxes;
	}
	
	public void setDailyTaxes(String dailyTaxes) {
		this.dailyTaxes = dailyTaxes;
	}
	
	public String getWeeklySales() {
		return weeklySales;
	}
	
	public void setWeeklySales(String weeklySales) {
		this.weeklySales = weeklySales;
	}
	
	public String getWeeklyTaxes() {
		return weeklyTaxes;
	}
	
	public void setWeeklyTaxes(String weeklyTaxes) {
		this.weeklyTaxes = weeklyTaxes;
	}
}
