package com.st.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.st.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;

	private Map<String, Palette> paletteManager = new HashMap<String, Palette>();

	public Map<String, Palette> getPaletteManager() {
		return paletteManager;
	}

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		readViews();
		readPalettes();
	}

	private void readViews() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			if (e.getName().equals("view"))
				System.out.println("Plugin : " + e.getNamespaceIdentifier() + "\tVue : " + e.getAttribute("name")
						+ "\tImplemented by " + e.getAttribute("class"));
		}

	}

	private void readPalettes() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();

		for (IConfigurationElement e : reg.getConfigurationElementsFor("com.st.rental.ui.palette")) {

			try {
				Palette p = new Palette();

				IColorProvider cp = (IColorProvider) e.createExecutableExtension("class");

				p.setName(e.getAttribute("name"));
				p.setId(e.getAttribute("id"));
				p.setProvider(cp);
				paletteManager.put(p.getId(), p);
				System.out.println("Plugin : " + e.getNamespaceIdentifier() + "\tPalette : " + e.getAttribute("name")
						+ "\tImplemented by " + e.getAttribute("class"));

			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(this.getClass());

		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));

	}

}
