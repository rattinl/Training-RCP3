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
	private Group grpDatesDeLocation;
	private Label lblDatedu;
	private Label lblDateau;
	
	
	
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
		
		grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		GridData gd_grpDatesDeLocation = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpDatesDeLocation.widthHint = 156;
		gd_grpDatesDeLocation.heightHint = 84;
		grpDatesDeLocation.setLayoutData(gd_grpDatesDeLocation);
		grpDatesDeLocation.setText("Dates de location");
		
		Label lblDu = new Label(grpDatesDeLocation, SWT.NONE);
		lblDu.setText("du :");
		
		 lblDatedu = new Label(grpDatesDeLocation, SWT.NONE);
		 lblDatedu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblDatedu.setText("date-du");
		 
		 Label lblAu = new Label(grpDatesDeLocation, SWT.NONE);
		 lblAu.setText("au : ");
		
		 lblDateau = new Label(grpDatesDeLocation, SWT.NONE);
		 lblDateau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblDateau.setText("date-au");
		
		setRental( RentalCoreActivator.getAgency().getRentals().get(0) );
	}

	
	private void setRental (Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getDisplayName());
		lblDatedu.setText(r.getStartDate().toString());
		lblDateau.setText(r.getEndDate().toString());
	}
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
