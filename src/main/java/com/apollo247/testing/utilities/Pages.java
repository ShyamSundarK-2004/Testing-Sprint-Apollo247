package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.apollo247.testing.pages.ApolloProductsPage;
import com.apollo247.testing.pages.BuyMedicineCartPage;
import com.apollo247.testing.pages.BuyMedicinePage;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.InvalidSearchPage;
import com.apollo247.testing.pages.VoliniPage;

public class Pages {

    public DashboardPage dashboardPage;
    public ApolloProductsPage apolloproductsPage;
    public BuyMedicineCartPage buyMedicineCartPage;
    public BuyMedicinePage buyMedicinePage;
    public VoliniPage voliniPage;
    public InvalidSearchPage invalidSearchPage;

    public Pages(WebDriver driver) {

        dashboardPage = new DashboardPage(driver);
        apolloproductsPage = new ApolloProductsPage(driver);
        buyMedicineCartPage = new BuyMedicineCartPage(driver);
        buyMedicinePage = new BuyMedicinePage(driver);
        voliniPage = new VoliniPage(driver);
        invalidSearchPage = new InvalidSearchPage(driver);
        
        PageFactory.initElements(driver, dashboardPage);
        PageFactory.initElements(driver, apolloproductsPage);
        PageFactory.initElements(driver, buyMedicineCartPage);
        PageFactory.initElements(driver, buyMedicinePage);
        PageFactory.initElements(driver, voliniPage);
        PageFactory.initElements(driver, invalidSearchPage);

    }
}