package com.dam.salesianostriana.di.trianadvisorv1.test;

import com.dam.salesianostriana.di.trianadvisorv1.actitities.LoginActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class prueba_1_trianadvisor extends ActivityInstrumentationTestCase2<LoginActivity> {
  	private Solo solo;
  	
  	public prueba_1_trianadvisor() {
		super(LoginActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Take screenshot
        solo.takeScreenshot();
        //Wait for activity: 'com.dam.salesianostriana.di.trianadvisorv1.actitities.LoginActivity'
		solo.waitForActivity(com.dam.salesianostriana.di.trianadvisorv1.actitities.LoginActivity.class, 2000);
        //Click on Mesón Los Alcores Restaurantes 954270661 Calle Farmaceutico Murillo Herrera
		solo.clickInRecyclerView(1, 0);
        //Wait for activity: 'com.dam.salesianostriana.di.trianadvisorv1.actitities.ScrollingActivity'
		assertTrue("com.dam.salesianostriana.di.trianadvisorv1.actitities.ScrollingActivity is not found!", solo.waitForActivity(com.dam.salesianostriana.di.trianadvisorv1.actitities.ScrollingActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.dam.salesianostriana.di.trianadvisorv1.R.id.fab));
        //Wait for activity: 'com.dam.salesianostriana.di.trianadvisorv1.actitities.EnviarcomentariosActivity'
		assertTrue("com.dam.salesianostriana.di.trianadvisorv1.actitities.EnviarcomentariosActivity is not found!", solo.waitForActivity(com.dam.salesianostriana.di.trianadvisorv1.actitities.EnviarcomentariosActivity.class));
        //Press menu back key
		solo.goBack();
        //Press menu back key
		solo.goBack();
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on Ir de tapas FrameLayout
		solo.clickInRecyclerView(2, 0);
        //Wait for activity: 'com.dam.salesianostriana.di.trianadvisorv1.actitities.MapsActivity'
		assertTrue("com.dam.salesianostriana.di.trianadvisorv1.actitities.MapsActivity is not found!", solo.waitForActivity(com.dam.salesianostriana.di.trianadvisorv1.actitities.MapsActivity.class));
        //Set default small timeout to 14688 milliseconds
		Timeout.setSmallTimeout(14688);
        //Press menu back key
		solo.goBack();
	}
}
