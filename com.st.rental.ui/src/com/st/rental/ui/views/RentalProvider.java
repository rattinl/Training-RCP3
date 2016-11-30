package com.st.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

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
	public String getText(Object element) {
		// TODO Auto-generated method stub
		if ( element instanceof RentalAgency )
			return ((RentalAgency) element).getName();
		else
			if ( element instanceof Customer )
				return ((Customer) element).getDisplayName();
		if ( element instanceof RentalObject )
			return ((RentalObject) element).getName();
		return super.getText(element);
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
