package com.st.rental.ui.cmd.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;
import com.st.rental.ui.RentalUIActivator;
import com.st.rental.ui.RentalUIConstants;

public class CopyCustomerHandlers extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		String textData = "Hello World";
		
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		ISelection currentSelection = HandlerUtil.getCurrentStructuredSelection(event);

		if ( currentSelection instanceof IStructuredSelection ) {
			IStructuredSelection isel = ( IStructuredSelection ) currentSelection;
			Object selected = isel.getFirstElement();
			
			
			// if ( selected instanceof Customer ) 
			Customer cust = Platform.getAdapterManager().getAdapter(selected, Customer.class);
			{
//				textData = ((Customer) selected).getDisplayName();
				textData = cust.getDisplayName();
				String rtfData = "{\\rtf1\\b\\i " + textData + "}";

				TextTransfer textTransfer = TextTransfer.getInstance();
				RTFTransfer rtfTransfer = RTFTransfer.getInstance();
				Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer,ImageTransfer.getInstance()};
				Object[] data = new Object[]{textData, rtfData,RentalUIActivator.getDefault().getImageRegistry().get(RentalUIConstants.IMG_AGENCY).getImageData()};

				clipboard.setContents(data, transfers);
				clipboard.dispose();

			}
				
		}
		
		
		return null;
	}

	
}
