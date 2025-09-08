package pages;

import factory.DriverFactory;
import utils.ElementUtils;

public class DashBoardPage {
	
	ElementUtils util = new ElementUtils(DriverFactory.getDriver());

	  public String getTitle() {
		 
		  return util.getTitle();
	  }


}
