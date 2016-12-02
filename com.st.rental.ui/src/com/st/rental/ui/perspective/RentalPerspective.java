package com.st.rental.ui.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;

public class RentalPerspective implements IPerspectiveFactory {

	public static final String ID = "com.st.rental.ui.perspectiveRental";
	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.st.rental.ui.viewRental", IPageLayout.RIGHT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.st.rental.ui.viewRentalAgency", IPageLayout.LEFT, 0.5f, "com.st.rental.ui.viewRental");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
