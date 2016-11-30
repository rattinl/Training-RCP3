package com.st.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.st.rental.ui.RentalUIActivator;
import com.st.rental.ui.RentalUIConstants;

public class RentalProvider extends LabelProvider implements ITreeContentProvider,IColorProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		
		if ( inputElement instanceof Collection )
			return ((Collection) inputElement).toArray();
		
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if ( parentElement instanceof RentalAgency ) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] {  new Node (Node.CUSTOMERS, a),
							new Node (Node.LOCATIONS, a),
							new Node (Node.OBJETS_À_LOUER, a) };
		}
		if ( parentElement instanceof Node ) {
			return ((Node) parentElement).getChildren();
		}
		//			return ((RentalAgency) parentElement).getCustomers().toArray() ;
		
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Color getForeground(Object element) {
		Display d = Display.getCurrent();
		
		if ( element instanceof RentalAgency )
			return d.getSystemColor(SWT.COLOR_BLUE);
		if ( element instanceof Customer )
			return d.getSystemColor(SWT.COLOR_MAGENTA);
		if ( element instanceof RentalObject )
			return d.getSystemColor(SWT.COLOR_DARK_GREEN);
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if ( element instanceof RentalAgency )
			return ((RentalAgency) element).getName();
		else
			if ( element instanceof Customer )
				return ((Customer) element).getDisplayName();
		if ( element instanceof RentalObject )
			return ((RentalObject) element).getName();
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		RentalUIActivator ruia = RentalUIActivator.getDefault();

		if ( element instanceof RentalAgency ) 
			return ruia.getImageRegistry().get(RentalUIConstants.IMG_AGENCY);
	    else
			if ( element instanceof Customer )
				return ruia.getImageRegistry().get(RentalUIConstants.IMG_CUSTOMER);
		if ( element instanceof RentalObject )
			return ruia.getImageRegistry().get(RentalUIConstants.IMG_RENTAL_OBJECT);
		return super.getImage(element); 
	}
	
	public class Node {
		public static final String OBJETS_À_LOUER = "Objets à louer";
		public static final String LOCATIONS = "Locations";
		public static final String CUSTOMERS = "Customers";
		private String name;
		private RentalAgency agency;
		
		public Node(String name, RentalAgency agency) {
			super();
			this.name = name;
			this.agency = agency;
		}
		
		public Object[] getChildren(  ) {
			if ( name == CUSTOMERS )
				return ((RentalAgency) agency).getCustomers().toArray();
			if ( name == LOCATIONS )
				return ((RentalAgency) agency).getRentals().toArray();
			if ( name == OBJETS_À_LOUER )
				return ((RentalAgency) agency).getObjectsToRent().toArray();
			
			return null;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}

}
