package project.post_ident.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.util.ArrayList;

@Service
public class PersonenDatenService {

    @Autowired
    private TempPersonenDatenRepository tempPersonenDatenRepository;

    public Long getIDFirstPerson(){
        //Finde ID der Person in Datenbank
        ArrayList<TempPersonendaten> tempPersonendatenList;
        //Repository.findAll Ergebnis wird in eine ArrayList gecastet
        tempPersonendatenList= (ArrayList<TempPersonendaten>) tempPersonenDatenRepository.findAll();
        Long tempID=tempPersonendatenList.get(0).getPersonID();
        return tempID;
    }

}
