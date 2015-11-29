package org.collegeboard.qotdplus;

import org.testng.annotations.Test;

import java.util.logging.Level;


public class SampleTest extends BaseTest {

//    @Test(groups= { "college-android"})
//    public void testSwipeHorizontal() throws InterruptedException {
//        try {
//            samplePage().swipingHorizontal();
////			loginPage().loginWithValidCredentialsForAndroid("iosautotest+2@gmail.com", "Testing1");
////			overviewPage().CheckAnypopus();
////			overviewPage().navigateToOverviewTab();
////			MintLogger.info("Logginng  from sign up page");
////			overviewPage().navigateToAccounts();
////			Assert.assertTrue(accountsPage().validateAllAccountsPageSections());
////			accountsPage().navigateToAllAccounts();
//        } catch (Exception e) {
//            MintLogger.log(Level.SEVERE, "Exceptions happen!!");
//            e.printStackTrace();
//            logFailure(e.getMessage());
//        }
//    }
	
	@Test(groups= { "college-android"})
	public void testNavigateToMenu() throws InterruptedException {
        try {
            samplePage().navigateToMenu();

        } catch (Exception e) {
            MintLogger.log(Level.SEVERE, "Exceptions happen!!");
            e.printStackTrace();
            logFailure(e.getMessage());
        }
    }

        @Test(groups= { "college-android", "ios", "smoke"})
        public void testNclickToSignIn() throws InterruptedException {
            try {
                samplePage().ClickToSignin();
                Thread.sleep(20000);
//			loginPage().loginWithValidCredentialsForAndroid("iosautotest+2@gmail.com", "Testing1");
//			overviewPage().CheckAnypopus();
//			overviewPage().navigateToOverviewTab();
//			MintLogger.info("Logginng  from sign up page");
//			overviewPage().navigateToAccounts();
//			Assert.assertTrue(accountsPage().validateAllAccountsPageSections());
//			accountsPage().navigateToAllAccounts();
            } catch (Exception e) {
                MintLogger.log(Level.SEVERE, "Exceptions happen!!");
                e.printStackTrace();
                logFailure(e.getMessage());
            }

        }
            @Test(groups= { "college-android"})
            public void testNsignIN() throws InterruptedException {
                try{
                    samplePage().Signin();
//			loginPage().loginWithValidCredentialsForAndroid("iosautotest+2@gmail.com", "Testing1");
//			overviewPage().CheckAnypopus();
//			overviewPage().navigateToOverviewTab();
//			MintLogger.info("Logginng  from sign up page");
//			overviewPage().navigateToAccounts();
//			Assert.assertTrue(accountsPage().validateAllAccountsPageSections());
//			accountsPage().navigateToAllAccounts();
                }
                catch(Exception e){
                    MintLogger.log(Level.SEVERE,"Exceptions happen!!");
                    e.printStackTrace();
                    logFailure(e.getMessage());
                }
	}




	
//
	
}