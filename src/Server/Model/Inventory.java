package Server.Model;

import java.util.ArrayList;

/**
 *
 */
public class Inventory {

	private ArrayList <Item> itemList;
	private Order myOrder;

	/**
	 * constructs the inventory
	 * @param itemList itemList to be assigned
	 */
	public Inventory (ArrayList <Item> itemList) {
		this.itemList = itemList;
		myOrder = new Order ();
	}

	/**
	 * gets the itemList
	 * @return itemList
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}

	/**
	 *
	 * @param itemList
	 */
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public Item manageItem (String name){
		Item theItem = decreaseItem (name);
		
		if (theItem != null){
			placeOrder (theItem);
		}
		return theItem;
	}

	/**
	 *
	 * @param theItem
	 */
	public void placeOrder (Item theItem){
		OrderLine ol = theItem.placeOrder();
		if (ol !=null){
			myOrder.addOrderLine(ol);
		}
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	private Item decreaseItem (String name) {
		
		Item theItem = searchForItem (name);
		
		if (theItem == null)
			return null;
		
		if (theItem.decreaseItemQuantity() == true){
			
			return theItem;
		}
		return null;
		
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public int getItemQuantity (String name){
		Item theItem = searchForItem (name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public Item searchForItem (String name) {
		for (Item i: itemList) {
			if (i.getItemName().equals(name))
				return i;
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public String toString () {
		String str = "";
		for (Item i: itemList) {
			str += i;
		}
		return str;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Item searchForItem(int id) {
		// TODO Auto-generated method stub
		for (Item i: itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public String printOrder() {
		// TODO Auto-generated method stub
		return myOrder.toString();
	}

}
