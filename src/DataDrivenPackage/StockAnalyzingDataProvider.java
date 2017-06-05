package DataDrivenPackage;

import org.testng.annotations.DataProvider;


public class StockAnalyzingDataProvider {

	@DataProvider(name="stockdata" )
	public static Object[][] getStockData(){
		return new Object[][]{	
			{"MSFT","Microsoft Corporation",67.84,67.66,48.035 },
			{"GOOG","Alphabet Inc.",863.75,863.45,663.284}
			
		};
	}
}
