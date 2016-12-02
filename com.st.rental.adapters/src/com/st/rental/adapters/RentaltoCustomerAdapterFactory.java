package com.st.rental.adapters;

import org.eclipse.core.runtime.IAdapterFactory;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class RentaltoCustomerAdapterFactory implements IAdapterFactory {

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		
		if ((adaptableObject instanceof Rental) && (adapterType == Customer.class))
			return (T) ((Rental)adaptableObject).getCustomer();

		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { Customer.class };
	}

}
