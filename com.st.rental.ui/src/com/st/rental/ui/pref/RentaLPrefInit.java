package com.st.rental.ui.pref;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.st.rental.ui.RentalUIActivator;
import com.st.rental.ui.RentalUIConstants;

public class RentaLPrefInit extends AbstractPreferenceInitializer implements RentalUIConstants  {

	public RentaLPrefInit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore s = RentalUIActivator.getDefault().getPreferenceStore();
		s.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(200,4,10)));
		s.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(10,40,102)));
		s.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(new RGB(40,4,10)));
		
		s.setDefault(PREF_PALETTE, "com.st.rental.ui.palette.default" );

	}

}
