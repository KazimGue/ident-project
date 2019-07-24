package project.post_ident.classes;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Repository;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.util.ArrayList;

public class PersonenDatenServiceTest {



    @Mock
    private TempPersonenDatenRepository tempPersonenDatenRepository;

    @InjectMocks
    private PersonenDatenService personenDatenService;

    @Test
    public void einePersonenImRepo(){

        MockitoAnnotations.initMocks(this);

        //Vorbereitung
        TempPersonendaten tempPersonendaten=new TempPersonendaten();
        tempPersonendaten.setPersonID(4L);

        ArrayList<TempPersonendaten> personendatenArrayList=new ArrayList<>();
        personendatenArrayList.add(tempPersonendaten);

        Mockito.when(tempPersonenDatenRepository.findAll()).thenReturn(personendatenArrayList);


        //Ausführung
        Long tempID=personenDatenService.getIDFirstPerson();

        //Auswertung
        Assert.assertEquals(4L,(long)tempID);
    }
}
