package com.st.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.st.rental.core.RentalCoreActivator;

public class ViewRentalAgency extends ViewPart {

	public ViewRentalAgency() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		TreeViewer tv = new TreeViewer(parent);
		
		RentalProvider rp = new RentalProvider(); 
		
		tv.setContentProvider( rp );
		
		tv.setLabelProvider( rp );
		
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		
		tv.setInput( agencies );
		
		tv.expandAll();
		
		getSite().setSelectionProvider(tv);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
