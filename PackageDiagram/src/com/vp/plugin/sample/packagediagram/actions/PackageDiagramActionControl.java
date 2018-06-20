package com.vp.plugin.sample.packagediagram.actions;

import java.awt.Color;
import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IPackageDiagramUIModel;
import com.vp.plugin.diagram.connector.IDependencyUIModel;
import com.vp.plugin.diagram.shape.IPackageUIModel;
import com.vp.plugin.model.IDependency;
import com.vp.plugin.model.IPackage;
import com.vp.plugin.model.factory.IModelElementFactory;

public class PackageDiagramActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// create blank class diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IPackageDiagramUIModel diagram = (IPackageDiagramUIModel) diagramManager.createDiagram(DiagramManager.DIAGRAM_TYPE_PACKAGE_DIAGRAM);
		
		// create ShoppingCart package model
		IPackage shoppingCartPackage = IModelElementFactory.instance().createPackage();
		shoppingCartPackage.setName("ShoppingCart");
		// create base package shape
		IPackageUIModel shoppingCartPackageShape = (IPackageUIModel) diagramManager.createDiagramElement(diagram, shoppingCartPackage);
		shoppingCartPackageShape.setBounds(415, 263, 140, 100);
		
		// changing the background color of the package
		shoppingCartPackageShape.getFillColor().setColor1(new Color(255, 255, 128));
		// changing the line color of the package
		shoppingCartPackageShape.getLineModel().setColor(Color.MAGENTA);
		// changing the font color of the package
		shoppingCartPackageShape.getElementFont().setColor(Color.BLUE);
				
		// set to automatic calculate the initial caption position
		shoppingCartPackageShape.setRequestResetCaption(true);
				
		// create WebShop package model
		IPackage webShopPackage = IModelElementFactory.instance().createPackage();
		webShopPackage.setName("WebShop");
		IPackageUIModel webShopPackageShape = (IPackageUIModel) diagramManager.createDiagramElement(diagram, webShopPackage);
		webShopPackageShape.setBounds(689, 263, 140, 100);
		webShopPackageShape.setRequestResetCaption(true);

		// create Auxiliary package model
		IPackage auxiliaryPackage = IModelElementFactory.instance().createPackage();
		auxiliaryPackage.setName("Auxiliary");
		IPackageUIModel auxiliaryPackageShape = (IPackageUIModel) diagramManager.createDiagramElement(diagram, auxiliaryPackage);
		auxiliaryPackageShape.setBounds(148, 163, 140, 100);
		auxiliaryPackageShape.setRequestResetCaption(true);
		
		// create Types package model
		IPackage typesPackage = IModelElementFactory.instance().createPackage();
		typesPackage.setName("Types");
		IPackageUIModel typesPackageShape = (IPackageUIModel) diagramManager.createDiagramElement(diagram, typesPackage);
		typesPackageShape.setBounds(148, 340, 140, 100);
		typesPackageShape.setRequestResetCaption(true);
		
		// create PaymentGateway package model
		IPackage paymentGatewayPackage = IModelElementFactory.instance().createPackage();
		paymentGatewayPackage.setName("PaymentGateway");
		IPackageUIModel paymentGatewayPackageShape = (IPackageUIModel) diagramManager.createDiagramElement(diagram, paymentGatewayPackage);
		paymentGatewayPackageShape.setBounds(415, 426, 140, 100);
		paymentGatewayPackageShape.setRequestResetCaption(true);
		
		// create dependency between Type and ShoppingCart
		IDependency importTypeShoppingCart = IModelElementFactory.instance().createDependency();
		// set the dependency as "import" type
		importTypeShoppingCart.addStereotype("import");
		// specify the from & to element
		importTypeShoppingCart.setFrom(shoppingCartPackage);
		importTypeShoppingCart.setTo(typesPackage);
		// create dependency connector
		IDependencyUIModel importTypeShoppingCartConnector = (IDependencyUIModel) diagramManager.createConnector(diagram, importTypeShoppingCart, shoppingCartPackageShape, typesPackageShape, null);
		// set to automatic calculate the initial caption position
		importTypeShoppingCartConnector.setRequestResetCaption(true);
		
		// create dependency between ShoppingCart and WebShop
		IDependency importShoppingCartWebShop = IModelElementFactory.instance().createDependency();
		importShoppingCartWebShop.addStereotype("import");
		importShoppingCartWebShop.setFrom(webShopPackage);
		importShoppingCartWebShop.setTo(shoppingCartPackage);
		IDependencyUIModel importShoppingCartWebShopConnector = (IDependencyUIModel) diagramManager.createConnector(diagram, importShoppingCartWebShop, webShopPackageShape, shoppingCartPackageShape, null);
		importShoppingCartWebShopConnector.setRequestResetCaption(true);
		
		// create dependency between Auxiliaty and ShoppingCart
		IDependency accessAuxiliaryShoppingCart = IModelElementFactory.instance().createDependency();
		accessAuxiliaryShoppingCart.addStereotype("access");
		accessAuxiliaryShoppingCart.setFrom(shoppingCartPackage);
		accessAuxiliaryShoppingCart.setTo(auxiliaryPackage);
		IDependencyUIModel accessAuxiliaryShoppingCartConnector = (IDependencyUIModel) diagramManager.createConnector(diagram, accessAuxiliaryShoppingCart, shoppingCartPackageShape, auxiliaryPackageShape, null);
		accessAuxiliaryShoppingCartConnector.setRequestResetCaption(true);
		
		// create dependency between PaymentGAteway and WebShop
		IDependency mergePaymentGatewayWebShop = IModelElementFactory.instance().createDependency();
		mergePaymentGatewayWebShop.addStereotype("merge");
		mergePaymentGatewayWebShop.setFrom(shoppingCartPackage);
		mergePaymentGatewayWebShop.setTo(auxiliaryPackage);
		// define turning points for the merge dependency
		Point[] points = new Point[] {new Point(555, 480), new Point(760, 480), new Point(760, 363)};
		IDependencyUIModel mergePaymentGatewayWebShopConnector = (IDependencyUIModel) diagramManager.createConnector(diagram, mergePaymentGatewayWebShop, paymentGatewayPackageShape, webShopPackageShape, points);
		mergePaymentGatewayWebShopConnector.setRequestResetCaption(true);

		// show up the diagram		
		diagramManager.openDiagram(diagram);
	}

	@Override
	public void update(VPAction arg0) {
		
	}

}
