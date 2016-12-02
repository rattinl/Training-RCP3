package com.st.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.st.rental.ui.RentalUIActivator;
import com.st.rental.ui.RentalUIConstants;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		Display d = Display.getCurrent();
		
		if ( element instanceof RentalAgency )
				return getAColor( RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR) );
		if ( element instanceof Rental )
			return getAColor( RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR) );
			//			return d.getSystemColor(SWT.COLOR_BLUE);
		if ( element instanceof Customer )
			return getAColor( RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR) );
//			return d.getSystemColor(SWT.COLOR_MAGENTA);
		if ( element instanceof RentalObject )
			return getAColor( RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_OBJECT_COLOR) );
//			return d.getSystemColor(SWT.COLOR_DARK_GREEN);
		return null;
	}

	private Color getAColor(String rgbkey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbkey);
		if ( col == null) {
			colorRegistry.put(rgbkey, StringConverter.asRGB(rgbkey));
			col = colorRegistry.get(rgbkey);
		}
		
		return col;
	}
	

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
