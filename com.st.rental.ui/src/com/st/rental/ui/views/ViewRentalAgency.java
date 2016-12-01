package com.st.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.st.rental.core.RentalCoreActivator;
import com.st.rental.ui.RentalUIActivator;

public class ViewRentalAgency extends ViewPart implements IPropertyChangeListener {

	private TreeViewer tv;
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}
	
	public ViewRentalAgency() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		tv = new TreeViewer(parent);
		
		RentalProvider rp = new RentalProvider(); 
		
		tv.setContentProvider( rp );
		
		tv.setLabelProvider( rp );
		
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		
		tv.setInput( agencies );
		
		tv.expandAll();
		
		getSite().setSelectionProvider(tv);
		
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, tv);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
	}

}
