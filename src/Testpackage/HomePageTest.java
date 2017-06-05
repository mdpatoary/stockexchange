package Testpackage;

import java.text.DecimalFormat;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.intl.NumberFormat;

import MainPackage.BaseTest;


public class HomePageTest extends BaseTest{
	

	double currentStockprice;
	double yearlyHigh;
	double yearlyLow;

	@Test(dataProvider="stockdata",dataProviderClass=DataDrivenPackage.StockAnalyzingDataProvider.class)
	public void analyzeStockQuote(String stockSymbol,String companyName,
			 double actualStockPrice,double actualYearlyHigh,double actualYearlyLow ) {

		getHomePageController().searchCompany(stockSymbol);
		getHomePageController().clickSearchBtn();
		Assert.assertTrue(getHomePageController().getCompanyLabel(companyName).contains(companyName));

		currentStockprice = Double.parseDouble(getHomePageController().getStockPrice().replace("$", "")); //check current price
		System.out.println("Price :"+currentStockprice);
		Assert.assertEquals(actualStockPrice,currentStockprice );
		Reporter.log("Current Stock Price of "+companyName+" is "+currentStockprice+"\n");

		yearlyHigh = Double.parseDouble(getHomePageController().return52WeeksYearlyHigh().replace("$ ", ""));//check yearly high
		Assert.assertEquals(actualYearlyHigh, yearlyHigh);
		System.out.println("High : "+actualYearlyHigh);

		Reporter.log("52 Weeks High of "+companyName+" is "+yearlyHigh+"\n");

		yearlyLow = Double.parseDouble(getHomePageController().return52WeeksYearlyLow().replace("$ ", "")); // check yearly low
		Assert.assertEquals(actualYearlyLow, yearlyLow);
		Reporter.log("52 Weeks Low of "+companyName+" is "+yearlyLow+"\n");
	}


	@Test(dependsOnMethods="analyzeStockQuote")
	public void compareWithHighAndLow(){
		DecimalFormat formatter = new DecimalFormat("#0.00");
		boolean lowerThanHigh = true;
		boolean higherThanLow = true;
		double differenceWithYearlyHigh = currentStockprice - yearlyHigh;
		if(differenceWithYearlyHigh<0){
			differenceWithYearlyHigh= -1 *differenceWithYearlyHigh;
			lowerThanHigh = false;
		}

		double percentageDifferenceWithYearlyHigh = (differenceWithYearlyHigh/currentStockprice)*100;
		String formattedDiff1 = formatter.format(percentageDifferenceWithYearlyHigh);
		if(percentageDifferenceWithYearlyHigh<=10 ){
			if(lowerThanHigh ){
				Reporter.log("“Today’s price of "+currentStockprice
						+ " is "
						+ formattedDiff1
						+ "% lower than the 52 week high");
			}
			else{
				Reporter.log("“Today’s price of "+currentStockprice
						+ " is "
						+ formattedDiff1
						+ "% higher than the 52 week high");
			}
		}



		double differenceWithYearlyLow = currentStockprice - yearlyLow;
		if(differenceWithYearlyLow<0){
			differenceWithYearlyLow= -1 *differenceWithYearlyLow;
			higherThanLow = false;
		}
		double percentageDifferenceWithYearlyLow = (differenceWithYearlyLow/currentStockprice)*100;

		String formattedDiff2 = formatter.format(percentageDifferenceWithYearlyLow);
		System.out.println(percentageDifferenceWithYearlyLow);
		if(percentageDifferenceWithYearlyLow<=10 ){
			if(higherThanLow ){
				Reporter.log("“Today’s price of "+currentStockprice
						+ " is "
						+ formattedDiff2
						+ "% higher than the 52 week low.");
			}
			else{
				Reporter.log("“Today’s price of "+currentStockprice
						+ " is"
						+ formattedDiff2
						+ "% lower than the 52 week low.");
			}
		}
	}
}
