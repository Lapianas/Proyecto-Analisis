package com.unrc.app;
import java.util.*;


import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.OwnersRealEstates;
import com.unrc.app.models.RealEstate;

@SuppressWarnings("unused")
public class ABMOwner {
	
	public boolean findOwner(String dni){
		return (Owner.first("owner_dni = ?", dni) != null);
	}
	
	public void Alta(String owname,String owneighborhood,String owstreet,String owmail,String owlocality,String owdni){
		if (findOwner(owname)){
			System.out.println("duenio ya existente");
		}
		else{
			Owner OW = Owner.create("owner_name", owname, "owner_mail", owmail,"owner_neighborhood", owneighborhood, "owner_street", owstreet, "owner_dni", owdni );
			OW.saveIt();
			Locality l = Locality.first("name = ? ", owlocality);
			if (l == null) {
				Locality local = new Locality();
				local.set("name", owlocality);
				local.saveIt();
				local.add(OW);
			} else {
				l.add(OW);
			}

		}

	}
	

	public void Baja(String dni){
		Owner owner = Owner.first("owner_dni = ?", dni);
		int id = owner.getInteger("id");
		Building building = Building.first("owner_id = ?", id);
		if (building == null){
			OwnersRealEstates.delete("owner_id = ?", id);
			Owner.delete("owner_dni = ?", dni);
		}
	}

	public void ModWebOw(String dni, String web){
		Owner OW = new Owner();
		OW = Owner.first("owner_dni = ?", dni);
		OW.set("web", web);
		OW.saveIt();
	}

	public void ModMailOw(String dni, String mail){
		Owner OW = new Owner();
		OW = Owner.first("owner_dni = ?", dni);
		OW.set("owner_mail", mail);
		OW.saveIt();
	}

}


