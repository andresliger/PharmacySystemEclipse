/**
 * 
 */
package main;

/**
 * @author andre
 *
 */
public class PharmacyItem {
	
	private String nameMedicine;
	private Integer itemNumber;
	private String typeMedicine;
	private String providerName;
	private String address;
	
	public String getNameMedicine() {
		return nameMedicine;
	}
	public void setNameMedicine(String nameMedicine) {
		this.nameMedicine = nameMedicine;
	}
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getTypeMedicine() {
		return typeMedicine;
	}
	public void setTypeMedicine(String typeMedicine) {
		this.typeMedicine = typeMedicine;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String getFullDescriptionPayment() {
		return "Pedido por: "+itemNumber+" unidades del "+typeMedicine+" "+nameMedicine+"\n"+
			   "Para la farmacia " + providerName+ " situada en: \n"+address;
		
	}
	
	

}
