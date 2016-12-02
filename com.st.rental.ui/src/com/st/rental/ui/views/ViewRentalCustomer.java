package com.st.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;

public class ViewRentalCustomer extends ViewPart implements ISelectionListener {

	public static final String ID = "com.st.rental.ui.views.ViewRentalCustomer"; //$NON-NLS-1$
	private Text surname;
	private Text name;
	private Text address;
	private String surnameS;
	private String nameS;
	private String addressS;

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	
	public ViewRentalCustomer() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(null);
		Composite container = new Composite(parent, SWT.NONE);
		container.setBounds(5, 5, 584, 248);
		container.setLayout(null);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 70, 20);
		lblNewLabel.setText("Identity");
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(10, 37, 360, 107);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("Display Name");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("Name : ");
		
		name = new Text(composite, SWT.BORDER);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setText("Surname : ");
		
		surname = new Text(composite, SWT.BORDER);
		
		Label lblNewLabel_4 = new Label(container, SWT.NONE);
		lblNewLabel_4.setBounds(10, 164, 70, 20);
		lblNewLabel_4.setText("Address");
		
		address = new Text(container, SWT.BORDER);
		address.setBounds(10, 190, 360, 46);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	private void setCustomer( Customer cust ) {
		if (cust == null)
		{
			resetCustomer();
			return;
		}
		name.setText(cust.getFirstName());
		surname.setText(cust.getLastName());
		address.setText(cust.getAddress().getStreetName() +"\n "
				+ cust.getAddress().getZipCode() + "  "
				+ cust.getAddress().getCity() );
	}


	private void resetCustomer() {
		name.setText(" ");
		surname.setText(" ");
		address.setText(" ");
	}
				
	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if (selection.isEmpty())
			return;
		
		// Manage selection here...
		if ( selection instanceof IStructuredSelection ) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			
/*
			if ( selected instanceof Customer ) {
				resetCustomer();				
				setCustomer( (Customer) selected );
			}
*/
			resetCustomer();				
			setCustomer( Platform.getAdapterManager().getAdapter(selected, Customer.class) );
			
			
		}
		
	}
}
