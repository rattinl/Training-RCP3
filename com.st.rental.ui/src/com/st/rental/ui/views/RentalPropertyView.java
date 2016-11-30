package com.st.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.st.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel, customerLabel;
	
	
	
	public RentalPropertyView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout( new GridLayout(1,false) );
		
		Group infoGroup = new Group(parent, SWT.BORDER);
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE );
		GridData gd= new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		Label l = new Label(infoGroup, SWT.NONE);
		l.setText("Loué à: ");
		customerLabel = new Label(infoGroup, SWT.NONE);
		
		setRental( RentalCoreActivator.getAgency().getRentals().get(0) );
	}

	
	private void setRental (Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getDisplayName());
	}
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
