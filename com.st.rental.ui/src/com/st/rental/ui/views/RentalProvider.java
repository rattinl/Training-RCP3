package com.st.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.st.rental.ui.RentalUIActivator;
import com.st.rental.ui.RentalUIConstants;

public class RentalProvider extends LabelProvider implements ITreeContentProvider,IColorProvider, RentalUIConstants {

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		
	}

}
